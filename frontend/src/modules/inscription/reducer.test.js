import deepFreeze from 'deep-freeze'

import reducer from './reducer'
import * as actions from './actions'

describe('Inscription reducer', () => {
  const initialState = {
    lastInscribeInfo: null,
    inscriptionsSearch: null
  }

  test('when called with undefined state, return initialState', () => {
    const action = {
      type: 'DO_NOTHING'
    }

    const state = reducer(undefined, action)
    
    expect(state).toEqual(initialState)
  })

  test('INSCRIBE_COMPLETED', () => {
    const inscriptionResult = {
      id: 1,
      dorsal: 1
    }

    const state = initialState
    const action = actions.inscribeCompleted(inscriptionResult)

    deepFreeze(state)
    const newState = reducer(state, action)

    expect(newState.inscriptionsSearch).toEqual(initialState.inscriptionsSearch)
    expect(newState.lastInscribeInfo).toEqual(inscriptionResult)
  })
})