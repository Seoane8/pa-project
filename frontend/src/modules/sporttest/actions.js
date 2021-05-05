import * as actionTypes from './actionTypes'
import * as selectors from './selectors'
import backend from '../../backend'

const findSportTestsCompleted = sportTestSearch => ({
    type: actionTypes.FIND_SPORTTESTS_COMPLETED,
    sportTestSearch
})

export const findSportTests = criteria => dispatch => {

    dispatch(clearSportTestSearch())
    backend.sportTestService.findSportTests(criteria,
        result => dispatch(findSportTestsCompleted({criteria, result})))

}

export const previousFindSportTestsResultPage = criteria =>
    findSportTests({...criteria, page: criteria.page-1})

export const nextFindSportTestsResultPage = criteria =>
    findSportTests({...criteria, page: criteria.page+1})

const clearSportTestSearch = () => ({
    type: actionTypes.CLEAR_SPORTTEST_SEARCH
})

const findAllProvincesCompleted = provinces => ({
    type: actionTypes.FIND_ALL_PROVINCES_COMPLETED,
    provinces
})

export const findAllProvinces = () => (dispatch, getState) => {
    const provinces = selectors.getProvinces(getState())

    if (provinces) return

    backend.sportTestService.findAllProvinces(
        provinces => dispatch(findAllProvincesCompleted(provinces))
    )
}

const findAllSportTestTypesCompleted = sportTestTypes => ({
    type: actionTypes.FIND_ALL_SPORTTESTTYPES_COMPLETED,
    sportTestTypes
})

export const findAllSportTestTypes = () => (dispatch, getState) => {
    const sportTestTypes = selectors.getSportTestTypes(getState())

    if (sportTestTypes) return

    backend.sportTestService.findAllSportTestTypes(
        sportTestTypes => dispatch(findAllSportTestTypesCompleted(sportTestTypes))
    )
}

const findSportTestByIdCompleted = sportTest => ({
    type: actionTypes.FIND_SPORTTEST_BY_ID_COMPLETED,
    sportTest
});

export const findSportTestById = id => dispatch => {
    backend.sportTestService.findSportTestById(id,
        sportTest => dispatch(findSportTestByIdCompleted(sportTest)));
}

export const clearSportTest = () => ({
    type: actionTypes.CLEAR_SPORTTEST
});