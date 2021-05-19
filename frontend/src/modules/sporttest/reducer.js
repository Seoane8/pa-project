import {combineReducers} from 'redux'

import * as actionTypes from './actionTypes'

const initialState = {
    provinces: null,
    sportTestTypes: null,
    sportTestSearch: null,
    sportTest: null,
    inscriptionsSearch: null,
    inscription: null

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
    switch (action.type){
        case actionTypes.FIND_SPORTTEST_BY_ID_COMPLETED:
            return action.sportTest;
        case actionTypes.CLEAR_SPORTTEST:
            return initialState.sportTest;
        default:
            return state;
    }
}

const inscriptionsSearch = (state = initialState.inscriptionsSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_INSCRIPTIONS_COMPLETED:
            return action.inscriptionsSearch;

        case actionTypes.CLEAR_INSCRIPTIONS_SEARCH:
            return initialState.inscriptionsSearch;

        default:
            return state;

    }

}

const inscription = (state = initialState.inscription, action) => {

    switch (action.type) {

        case actionTypes.FIND_INSCRIPTION_COMPLETED:
            return action.inscription;

        case actionTypes.CLEAR_INSCRIPTION:
            return initialState.inscription;

        default:
            return state;

    }

}

const reducer = combineReducers({
    provinces,
    sportTestTypes,
    sportTestSearch,
    sportTest,
    inscriptionsSearch,
    inscription
})

export default reducer