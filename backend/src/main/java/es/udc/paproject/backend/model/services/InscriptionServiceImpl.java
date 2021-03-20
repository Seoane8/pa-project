package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class InscriptionServiceImpl  implements InscriptionService{

    @Autowired
    private SportTestDao sportTestDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InscriptionDao inscriptionDao;




    @Override
    public Block<Inscription> findMyInscriptions(Long userId, int page, int size) {
        Slice<Inscription> slice = inscriptionDao.findByUserIdOrderByReservationDateDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    @Override
    public void scoreTest(Long inscriptionId, Long userId, int score)
            throws InstanceNotFoundException, DateExpiredException,
            InscriptionAlreadyScoredException, SportTestNotStartedYetException {

        Optional<Inscription> inscription = inscriptionDao.findById(inscriptionId);

        if (inscription.isEmpty()) {
            throw new InstanceNotFoundException("entities.inscription", inscriptionId);
        }

        User user = inscription.get().getUser();

        if (userId != user.getId()) {
            throw new InstanceNotFoundException("entities.user", userId);
        }

        Optional<SportTest> sportTest = Optional.ofNullable(inscription.get().getSportTest());

        if (LocalDateTime.now().isAfter(sportTest.get().getDate().plusDays(15))) {
            throw new DateExpiredException(sportTest.get().getId());
        }

        if (inscription.get().getScore() != -1) {
            throw new InscriptionAlreadyScoredException(inscriptionId);
        }

        if (LocalDateTime.now().isBefore(sportTest.get().getDate())) {
            throw new SportTestNotStartedYetException(sportTest.get().getId());
        }

        inscription.get().setScore(score);
        sportTest.get().actualizeScore(score);

    }

    @Override
    public Inscription inscribe(Long sportTestId, Long userId, String cardNumber)
            throws InstanceNotFoundException, PermissionException, AlreadyInscribedException,
            NoMoreInscriptionsAllowedException, InscriptionDateExpiredException {

        Optional<SportTest> sportTest = sportTestDao.findById(sportTestId);
        Optional<User> user = userDao.findById(userId);

        if (sportTest.isEmpty()) {
            throw new InstanceNotFoundException("entities.sportTest", sportTestId);
        }

        if (user.isEmpty()) {
            throw new InstanceNotFoundException("entities.user", userId);
        }

        if (inscriptionDao.existsByUserIdAndSportTestId(userId, sportTestId)){
            throw new AlreadyInscribedException(sportTestId, user.get().getUserName());
        }

        if (LocalDateTime.now().plusHours(24).isAfter(sportTest.get().getDate())) {
            throw new InscriptionDateExpiredException(sportTestId, sportTest.get().getDate());
        }

        int dorsal = sportTest.get().addParticipant();

        if (dorsal == -1) {
            throw new NoMoreInscriptionsAllowedException(sportTestId);
        }

        Inscription inscription = new Inscription(user.get(), sportTest.get(), cardNumber,
                LocalDateTime.now(), sportTest.get().getPrice(), dorsal);

        return inscriptionDao.save(inscription);
    }

    @Override
    public int collectDorsal(Long sportTestId, Long inscriptionId, String cardNumber)
            throws InstanceNotFoundException, PermissionException, NotAllowedYetException,
            InscriptionNotAssociatedException, AlreadyCollectedException, IncorrectCardNumberException {
        Optional<SportTest> sportTest = sportTestDao.findById(sportTestId);
        Optional<Inscription> inscription = inscriptionDao.findById(inscriptionId);

        if (sportTest.isEmpty()) {
            throw new InstanceNotFoundException("entities.sportTest", sportTestId);
        }

        if (inscription.isEmpty()) {
            throw new InstanceNotFoundException("entities.inscription", inscriptionId);
        }

        if (!sportTestId.equals(inscription.get().getSportTest().getId())) {
            throw new InscriptionNotAssociatedException(inscriptionId, sportTestId);
        }

        if (sportTest.get().getDate().minusHours(12).isAfter(LocalDateTime.now())) {
            throw new NotAllowedYetException(inscriptionId, sportTest.get().getDate());
        }

        if (inscription.get().isDorsalCollected()) {
            throw new AlreadyCollectedException(sportTestId, inscription.get().getUser().getUserName());
        }

        if (!cardNumber.equals(inscription.get().getCardNumber())) {
            throw new IncorrectCardNumberException(inscriptionId, inscription.get().getUser().getUserName());
        }

        inscription.get().setDorsalCollected(true);
        return inscription.get().getDorsal();

    }
}
