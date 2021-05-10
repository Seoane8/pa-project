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