import deepFreeze from 'deep-freeze'

import reducer from './reducer';
import * as actions from './actions';

describe('SportTest reducer', () => {
    const initialState = {
        provinces: null,
        sportTestTypes: null,
        sportTestSearch: null,
        sportTest: null
    }
    const sportTestSearch = {
        criteria: {
            startDate: '2020-01-13',
            finishDate: '',
            provinceId: 1,
            typeId: 1,
            page: 0
        },
        result: {
            items: [
                {
                    id: 1,
                    name: 'Prueba 1',
                    provinceId: 1,
                    sportTestType: 1,
                    date: 1593590400000,
                    rating: 0
                }
            ],
            existMoreItems: false
        }
    }
    const completedState = {
        provinces: [{id: 1, name: 'A Coruña'}],
        sportTestTypes: [{id: 1, name: 'Running'}],
        sportTestSearch: null,
        sportTest: null
    }
    const clearState = {
        provinces: [{id: 1, name: 'A Coruña'}],
        sportTestTypes: [{id: 1, name: 'Running'}],
        sportTestSearch: sportTestSearch,
        sportTest: null
    }

    test('when called with undefined state, returns initialState', () => {
        const action = {
            type: 'DO_NOTHING'
        }
        const state = reducer(undefined, action)

        expect(state).toEqual(initialState)
    })

    test('FIND_SPORTTESTS_COMPLETED', () => {

        const state = completedState
        const action = actions.findSportTestsCompleted(sportTestSearch)

        deepFreeze(state)
        const newState = reducer(state, action)

        expect(newState.provinces).toEqual(completedState.provinces)
        expect(newState.sportTestTypes).toEqual(completedState.sportTestTypes)
        expect(newState.sportTestSearch).toEqual(sportTestSearch)
        expect(newState.sportTest).toEqual(completedState.sportTest)
    })

    test('CLEAR_SPORTTEST_SEARCH', () => {

        const state = clearState
        const action = actions.clearSportTestSearch()

        deepFreeze(state)
        const newState = reducer(state, action)

        expect(newState.provinces).toEqual(clearState.provinces)
        expect(newState.sportTestTypes).toEqual(clearState.sportTestTypes)
        expect(newState.sportTestSearch).toEqual(initialState.sportTestSearch)
        expect(newState.sportTest).toEqual(initialState.sportTest)
    })
})