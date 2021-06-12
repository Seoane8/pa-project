import configureMockStore from 'redux-mock-store'
import thunk from "redux-thunk"

import backend from '../../backend'
import * as actions from './actions'
import * as actionTypes from './actionTypes'

const mockStore = configureMockStore([thunk])

describe('Inscribe action', () => {
  const sportTestId = 1
  const cardNumber = '1234567890123456'
  const onSuccess = jest.fn()
  const onErrors = jest.fn()

  const dispatchInscribeAction = () => {
    const action = actions.inscribe(sportTestId, cardNumber, onSuccess, onErrors)
    const store = mockStore({})
    store.dispatch(action)

    return store
  }
  
  afterEach(() => {
    backend.inscriptionService.inscribe.mockRestore()
    onSuccess.mockRestore()
    onErrors.mockRestore()
  })

  test('call backend service correctly', () => {
    const backendInscribeSpy = jest
      .spyOn(backend.inscriptionService, 'inscribe')
      .mockImplementation((_inscribeParams, _onSuccess, _onErrors) => null)

    dispatchInscribeAction()

    expect(backendInscribeSpy.mock.calls[0][0])
      .toEqual({ sportTestId, cardNumber })
  })

  test('when the request is successful, dispatch action to store', () => {
    const inscribeResult = {
      id: 1,
      dorsal: 1
    }
    const inscribeCompletedAction = {
      type: actionTypes.INSCRIBE_COMPLETED,
      result: inscribeResult
    }

    jest.spyOn(backend.inscriptionService, 'inscribe')
      .mockImplementation((_inscribeParams, onSuccess, _onErrors) => 
        onSuccess(inscribeResult)
      )
    
    const expectedActions = [inscribeCompletedAction]
    const store = dispatchInscribeAction()

    expect(store.getActions()).toEqual(expectedActions)
    expect(onSuccess).toHaveBeenCalledTimes(1)
    expect(onErrors).not.toHaveBeenCalled()
  })

  test('when the request fails, call error callback', () => {
    const backendErrors = {globalError: 'Some error has ocurred'}

    jest.spyOn(backend.inscriptionService, 'inscribe')
      .mockImplementation((_inscribeParams, _onSuccess, onErrors) => 
        onErrors(backendErrors)
      )
    
    const expectedActions = []
    const store = dispatchInscribeAction()

    expect(store.getActions()).toEqual(expectedActions)
    expect(onSuccess).not.toHaveBeenCalled()
    expect(onErrors).toHaveBeenCalledTimes(1)
    expect(onErrors.mock.calls[0][0]).toEqual(backendErrors)
  })
})