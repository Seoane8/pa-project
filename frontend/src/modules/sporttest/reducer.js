import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    sportTestSearch: null
};

const sportTestSearch = (state = initialState.sportTestSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_SPORTTESTS_COMPLETED:
            return action.sportTestSearch;

        case actionTypes.CLEAR_SPORTTEST_SEARCH:
            return initialState.sportTestSearch;

        default:
            return state;

    }

}

const reducer = combineReducers({
    sportTestSearch
});

export default reducer;