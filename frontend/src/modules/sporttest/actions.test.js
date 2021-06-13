import configureMockStore from 'redux-mock-store'
import thunk from "redux-thunk"

import backend from '../../backend'
import * as actions from './actions'

const mockStore = configureMockStore([thunk])

describe('SportTest action', () => {
    const criteria = {"finishDate": "", "page": 0, "provinceId": null, "startDate": "", "typeId": null}
    const dispatchFindSportTestsAction = () => {
        const action = actions.findSportTests(criteria)
        const store = mockStore({})
        store.dispatch(action)

        return store
    }

    afterEach(() => {
        backend.sportTestService.findSportTests.mockRestore()
    })

    test('calls backend service correctly', () => {
        const backendInscribeSpy = jest
            .spyOn(backend.sportTestService, 'findSportTests')
            .mockImplementation((_criteria) => null)

        dispatchFindSportTestsAction()

        expect(backendInscribeSpy.mock.calls[0][0])
            .toEqual(criteria)
    })

    test('when the request is successful, dispatch action to store', () => {
        const result = {
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
        const sportTestSearch = {
            criteria: {
                startDate: '',
                finishDate: '',
                provinceId: null,
                typeId: null,
                page: 0
            },
            result: result
        }
        const findSportTestsCompletedAction = actions.findSportTestsCompleted(sportTestSearch)
        const clearSportTestSearchAction = actions.clearSportTestSearch()

        jest.spyOn(backend.sportTestService, 'findSportTests')
            .mockImplementation((_criteria, onSuccess) => onSuccess(result))

        const expectedActions = [clearSportTestSearchAction, findSportTestsCompletedAction]
        const store = dispatchFindSportTestsAction()

        expect(store.getActions()).toEqual(expectedActions)

    })
})