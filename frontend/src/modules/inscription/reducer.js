import {combineReducers} from 'redux'

import * as actionTypes from './actionTypes'

const initialState = {
    lastInscribeInfo: null
}

const lastInscribeInfo = (state = initialState.lastInscribeInfo, action) => {
    switch (action.type) {

        case actionTypes.INSCRIBE_COMPLETED:
            return action.result
        
        default:
            return state

    }
}

const reducer = combineReducers({
    lastInscribeInfo
})

export default reducer