package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;

import java.util.List;

public interface InscriptionService {
    Block<Inscription> findMyInscriptions(Long userId, int page, int size);

    void scoreTest(Long inscriptionId, Long userId, int score) throws InstanceNotFoundException, DateExpiredException,
            InscriptionAlreadyScoredException, SportTestNotStartedYetException;

    Inscription inscribe(Long sportTestId, Long userId, String cardNumber)
        throws InstanceNotFoundException, AlreadyInscribedException, NoMoreInscriptionsAllowedException,
            InscriptionDateExpiredException;

    int collectDorsal(Long sportTestId, Long inscriptionId, String cardNumber)
            throws InstanceNotFoundException, NotAllowedYetException, InscriptionNotAssociatedException,
            AlreadyCollectedException, IncorrectCardNumberException, CollectDorsalDateExpiredException;

}
