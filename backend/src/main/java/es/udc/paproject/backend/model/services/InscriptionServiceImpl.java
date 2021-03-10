package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public void scoreTest(Long inscriptionId, Long userId, int score)
            throws InstanceNotFoundException, DateExpiredException,
            InscriptionAlreadyScoredException, SportTestNotStartedYetException {

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
            throw new InstanceNotFoundException("entities.user", sportTestId);
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
        return 0;
    }
}
