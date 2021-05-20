import * as actionTypes from './actionTypes'
import backend from '../../backend'

const inscribeCompleted = result => ({
  type: actionTypes.INSCRIBE_COMPLETED,
  result
})

export const inscribe = (sportTestId, cardNumber, onSuccess, onErrors) => dispatch =>
  backend.inscriptionService.inscribe(
    {sportTestId, cardNumber},
    result => {
      dispatch(inscribeCompleted(result))
      onSuccess()
    },
    onErrors
  )


const clearInscriptionsSearch = () => ({
    type: actionTypes.CLEAR_INSCRIPTIONS_SEARCH
});

const findInscriptionsCompleted = inscriptionsSearch => ({
    type: actionTypes.FIND_INSCRIPTIONS_COMPLETED,
    inscriptionsSearch
});

export const findInscriptions = criteria => dispatch => {

    dispatch(clearInscriptionsSearch());
    backend.inscriptionService.findInscriptions(criteria,
        result => dispatch(findInscriptionsCompleted({criteria, result})));
}

export const previousFindInscriptionsResultPage = criteria =>
    findInscriptions({page: criteria.page-1});

export const nextFindInscriptionsResultPage = criteria =>
    findInscriptions({page: criteria.page+1});

export const clearInscription = () => ({
    type: actionTypes.CLEAR_INSCRIPTION
});

const findInscriptionCompleted = inscription => ({
    type: actionTypes.FIND_INSCRIPTION_COMPLETED,
    inscription
});

export const findInscription = inscriptionId => dispatch => {
    backend.inscriptionService.findInscription(inscriptionId, inscription => {
        dispatch(findInscriptionCompleted(inscription));
    });
}

export const updateStore = (id, score) => ({
    type: actionTypes.UPDATE_SCORE,
    data: {id, score}
})

export const score = (id, score, onSuccess, onErrors) => dispatch => {

    backend.inscriptionService.scoreTest(
        {id, score},
        () => {
            dispatch(updateStore(id, score))
            onSuccess()
        },
        onErrors
    )
}