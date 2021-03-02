package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;

public interface InscriptionService {

    Inscription inscribe(Long sportTestId, Long userId, String cardNumber)
        throws InstanceNotFoundException, PermissionException, AlreadyInscribedException,
            NoMoreInscriptionsAllowedException, InscriptionDateExpiredException;

    int collectDorsal(Long userId, Long sportTestId, Long inscriptionId, String cardNumber)
        throws InstanceNotFoundException, PermissionException, NotAllowedYetException,
            InscriptionNotAssociatedException, AlreadyCollectedException, IncorrectCardNumberException;

}
