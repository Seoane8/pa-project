package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;

import java.util.List;

public interface InscriptionService {
    List<Inscription> findMyInscriptions(Long userId) throws InstanceNotFoundException, PermissionException;

    void scoreTest(Long inscriptionId) throws InstanceNotFoundException, DateExpiredException,
            InscriptionAlreadyScoredException, SportTestNotStartedYetException;

    Inscription inscribe(Long sportTestId, Long userId, String cardNumber)
        throws InstanceNotFoundException, PermissionException, AlreadyInscribedException,
            NoMoreInscriptionsAllowedException, InscriptionDateExpiredException;

    int collectDorsal(Long sportTestId, Long inscriptionId, String cardNumber)
        throws InstanceNotFoundException, PermissionException, NotAllowedYetException,
            InscriptionNotAssociatedException, AlreadyCollectedException, IncorrectCardNumberException;

}
