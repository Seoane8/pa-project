import * as actionTypes from './actionTypes';
import backend from '../../backend';

const findSportTestsCompleted = sportTestSearch => ({
    type: actionTypes.FIND_SPORTTESTS_COMPLETED,
    sportTestSearch
});

export const findSportTests = criteria => dispatch => {

    dispatch(clearSportTestSearch());
    backend.sportTestService.findSportTests(criteria,
        result => dispatch(findSportTestsCompleted({criteria, result})));

}

export const previousFindSportTestsResultPage = criteria =>
    findSportTests({...criteria, page: criteria.page-1});

export const nextFindSportTestsResultPage = criteria =>
    findSportTests({...criteria, page: criteria.page+1});

const clearSportTestSearch = () => ({
    type: actionTypes.CLEAR_SPORTTEST_SEARCH
});