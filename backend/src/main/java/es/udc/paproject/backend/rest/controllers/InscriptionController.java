package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.InscriptionConversor.toInscriptionSummaryDto;

import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.CollectDorsalParamsDto;
import es.udc.paproject.backend.rest.dtos.InscribeParamsDto;
import es.udc.paproject.backend.rest.dtos.InscriptionSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {

    private final static String ALREADY_INSCRIBED_EXCEPTION_CODE = "project.exceptions.AlreadyInscribedException";
    private final static String NO_MORE_INSCRIPTION_ALLOWED_EXCEPTION_CODE = "project.exceptions.NoMoreInscriptionsAllowedException";
    private final static String INSCRIPTION_DATE_EXPIRED_EXCEPTION_CODE = "project.exceptions.InscriptionDateExpiredException";
    private final static String NOT_ALLOWED_YET_EXCEPTION_CODE = "project.exceptions.NotAllowedYetException";
    private final static String ALREADY_COLLECTED_EXCEPTION_CODE = "project.exceptions.AlreadyCollectedException";
    private final static String INSCRIPTION_NOT_ASSOCIATED_EXCEPTION_CODE = "project.exceptions.InscriptionNotAssociatedException";
    private final static String INCORRECT_CARD_NUMBER_EXCEPTION_CODE = "project.exceptions.IncorrectCardNumberException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private InscriptionService inscriptionService;

    @ExceptionHandler(AlreadyInscribedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorsDto handleAlreadyInscribedException(AlreadyInscribedException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(ALREADY_INSCRIBED_EXCEPTION_CODE,
                new Object[] {exception.getUserName()}, ALREADY_INSCRIBED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(NoMoreInscriptionsAllowedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorsDto handleNoMoreInscriptionsAllowedException(NoMoreInscriptionsAllowedException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(NO_MORE_INSCRIPTION_ALLOWED_EXCEPTION_CODE, null,
                NO_MORE_INSCRIPTION_ALLOWED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(InscriptionDateExpiredException.class)
    @ResponseStatus(HttpStatus.GONE)
    @ResponseBody
    public ErrorsDto handleInscriptionDateExpiredException(InscriptionDateExpiredException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INSCRIPTION_DATE_EXPIRED_EXCEPTION_CODE, null,
                INSCRIPTION_DATE_EXPIRED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(NotAllowedYetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorsDto handleNotAllowedYetException(NotAllowedYetException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(NOT_ALLOWED_YET_EXCEPTION_CODE,
                new Object[] {exception.getDate().toString()}, NOT_ALLOWED_YET_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(AlreadyCollectedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorsDto handleAlreadyCollectedException(AlreadyCollectedException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(ALREADY_COLLECTED_EXCEPTION_CODE,
                new Object[] {exception.getUserName()}, ALREADY_COLLECTED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(InscriptionNotAssociatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleInscriptionNotAssociatedException(InscriptionNotAssociatedException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INSCRIPTION_NOT_ASSOCIATED_EXCEPTION_CODE, null,
                INSCRIPTION_NOT_ASSOCIATED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(IncorrectCardNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleIncorrectCardNumberException(IncorrectCardNumberException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORRECT_CARD_NUMBER_EXCEPTION_CODE, null,
                INCORRECT_CARD_NUMBER_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

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
