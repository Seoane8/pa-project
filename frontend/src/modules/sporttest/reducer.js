import {combineReducers} from 'redux'

import * as actionTypes from './actionTypes'

const initialState = {
    provinces: null,
    sportTestTypes: null,
    sportTestSearch: null
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

const reducer = combineReducers({
    provinces,
    sportTestTypes,
    sportTestSearch
})

export default reducer