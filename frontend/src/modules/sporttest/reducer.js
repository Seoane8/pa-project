import { combineReducers } from 'redux'

import * as actionTypes from './actionTypes'

const initialState = {
  provinces: null,
  sportTestTypes: null,
  sportTestSearch: null,
  sportTest: null

}

const provinces = (state = initialState.provinces, action) => {
  switch (action.type) {
    case actionTypes.FIND_ALL_PROVINCES_COMPLETED:
      return action.provinces

    default:
      return state
  }
}

const sportTestTypes = (state = initialState.sportTestTypes, action) => {
  switch (action.type) {
    case actionTypes.FIND_ALL_SPORTTESTTYPES_COMPLETED:
      return action.sportTestTypes

    default:
      return state
  }
}

const sportTestSearch = (state = initialState.sportTestSearch, action) => {
  switch (action.type) {
    case actionTypes.FIND_SPORTTESTS_COMPLETED:
      return action.sportTestSearch

    case actionTypes.CLEAR_SPORTTEST_SEARCH:
      return initialState.sportTestSearch

    default:
      return state
  }
}

const sportTest = (state = initialState.sportTest, action) => {
  switch (action.type) {
    case actionTypes.FIND_SPORTTEST_BY_ID_COMPLETED:
      return action.sportTest
    case actionTypes.CLEAR_SPORTTEST:
      return initialState.sportTest
    default:
      return state
  }
}

const reducer = combineReducers({
  provinces,
  sportTestTypes,
  sportTestSearch,
  sportTest
})

export default reducer
