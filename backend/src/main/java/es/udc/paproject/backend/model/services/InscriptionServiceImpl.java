package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional
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
            throws InstanceNotFoundException, PermissionException, DateExpiredException,
            InscriptionAlreadyScoredException, SportTestNotStartedYetException {

        Optional<Inscription> inscription = inscriptionDao.findById(inscriptionId);

        if (inscription.isEmpty()) {
            throw new InstanceNotFoundException("entities.inscription", inscriptionId);
        }

        User user = inscription.get().getUser();

        if (!userId.equals(user.getId())) {
            throw new PermissionException();
        }

        SportTest sportTest = inscription.get().getSportTest();

        if (LocalDateTime.now().isAfter(sportTest.getDate().plusDays(15))) {
            throw new DateExpiredException(sportTest.getId());
        }

        if (inscription.get().getScore() != -1) {
            throw new InscriptionAlreadyScoredException(inscriptionId);
        }

        if (LocalDateTime.now().isBefore(sportTest.getDate())) {
            throw new SportTestNotStartedYetException(sportTest.getId());
        }

        inscription.get().setScore(score);
        sportTest.actualizeScore(score);

    }

    @Override
    public Inscription inscribe(Long sportTestId, Long userId, String cardNumber)
        throws InstanceNotFoundException, AlreadyInscribedException,
            NoMoreInscriptionsAllowedException, InscriptionDateExpiredException {

        Optional<SportTest> sportTest = sportTestDao.findById(sportTestId);
        Optional<User> user = userDao.findById(userId);

        if (sportTest.isEmpty()) {
            throw new InstanceNotFoundException("entities.sportTest", sportTestId);
        }

        if (user.isEmpty()) {
            throw new InstanceNotFoundException("entities.user", userId);
        }

        Optional<Inscription> previousInscription = inscriptionDao.findByUserIdAndSportTestId(userId, sportTestId);

        if (previousInscription.isPresent()){
            throw new AlreadyInscribedException(sportTestId, previousInscription.get().getId());
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
            throws InstanceNotFoundException, NotAllowedYetException, InscriptionNotAssociatedException,
            AlreadyCollectedException, IncorrectCardNumberException, CollectDorsalDateExpiredException {
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

        if (sportTest.get().getDate().isBefore(LocalDateTime.now())) {
            throw new CollectDorsalDateExpiredException(inscriptionId, sportTest.get().getDate());
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
