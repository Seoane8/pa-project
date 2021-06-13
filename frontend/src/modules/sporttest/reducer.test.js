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
    const clearState = {
        provinces: null,
        sportTestTypes: null,
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

        const state = initialState
        const action = actions.findSportTestsCompleted(sportTestSearch)

        deepFreeze(state)
        const newState = reducer(state, action)

        expect(newState.provinces).toEqual(initialState.provinces)
        expect(newState.sportTestTypes).toEqual(initialState.sportTestTypes)
        expect(newState.sportTestSearch).toEqual(sportTestSearch)
        expect(newState.sportTest).toEqual(initialState.sportTest)
    })

    test('CLEAR_SPORTTEST_SEARCH', () => {

        const state = clearState
        const action = actions.clearSportTestSearch()

        deepFreeze(state)
        const newState = reducer(state, action)

        expect(newState.provinces).toEqual(initialState.provinces)
        expect(newState.sportTestTypes).toEqual(initialState.sportTestTypes)
        expect(newState.sportTestSearch).toEqual(initialState.sportTestSearch)
        expect(newState.sportTest).toEqual(initialState.sportTest)
    })
})