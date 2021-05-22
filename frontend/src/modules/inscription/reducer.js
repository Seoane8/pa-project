import { combineReducers } from 'redux'

import * as actionTypes from './actionTypes'

const initialState = {
  lastInscribeInfo: null,
  inscriptionsSearch: null,
  inscription: null
}

const lastInscribeInfo = (state = initialState.lastInscribeInfo, action) => {
  switch (action.type) {
    case actionTypes.INSCRIBE_COMPLETED:
      return action.result

    default:
      return state
  }
}

const inscriptionsSearch = (state = initialState.inscriptionsSearch, action) => {
  switch (action.type) {
    case actionTypes.FIND_INSCRIPTIONS_COMPLETED:
      return action.inscriptionsSearch

    case actionTypes.CLEAR_INSCRIPTIONS_SEARCH:
      return initialState.inscriptionsSearch

    case actionTypes.UPDATE_SCORE: {
      const inscriptions = state?.result.items.map(
        ins => ins.id === action.data.id
          ? { ...ins, score: action.data.score, ratingEnabled: false }
          : ins
      )
      if (!inscriptions) return state
      const newResult = { ...state.result, items: inscriptions }
      return { ...state, result: newResult }
    }

    default:
      return state
  }
}

const inscription = (state = initialState.inscription, action) => {
  switch (action.type) {
    case actionTypes.FIND_INSCRIPTION_COMPLETED:
      return action.inscription

    case actionTypes.CLEAR_INSCRIPTION:
      return initialState.inscription

    default:
      return state
  }
}

const reducer = combineReducers({
  inscriptionsSearch,
  inscription,
  lastInscribeInfo
})

export default reducer
