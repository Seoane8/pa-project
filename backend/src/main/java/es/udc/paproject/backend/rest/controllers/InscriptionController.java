package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.InscriptionConversor.toInscriptionSummaryDto;

import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.rest.dtos.CollectDorsalParamsDto;
import es.udc.paproject.backend.rest.dtos.InscribeParamsDto;
import es.udc.paproject.backend.rest.dtos.InscriptionSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;


    @PostMapping("")
    public InscriptionSummaryDto inscribe(@RequestAttribute Long userId,
                                          @Validated @RequestBody InscribeParamsDto params)
        throws AlreadyInscribedException, InscriptionDateExpiredException,
            NoMoreInscriptionsAllowedException, InstanceNotFoundException {

        return toInscriptionSummaryDto(inscriptionService.inscribe( params.getSportTestId(),
                                                                    userId,
                                                                    params.getCardNumber()));
    }

    @PostMapping("/{inscriptionId}/collect")
    public int collectDorsal(@PathVariable Long inscriptionId, @Validated @RequestBody CollectDorsalParamsDto params)
        throws NotAllowedYetException, AlreadyCollectedException, IncorrectCardNumberException,
            InstanceNotFoundException, InscriptionNotAssociatedException {

        return inscriptionService.collectDorsal(params.getSportTestId(), inscriptionId, params.getCardNumber());
    }

}
