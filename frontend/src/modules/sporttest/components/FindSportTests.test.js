import React from 'react';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import {render, fireEvent} from '@testing-library/react';
import {createMemoryHistory} from 'history'
import {IntlProvider} from 'react-intl';
import {Router} from 'react-router-dom';

import FindSportTests from "./FindSportTests";
import * as actions from '../actions';
import messages_en from "../../../i18n/messages/messages_en";

describe('Rendering FindSportTests component', () => {
    let component
    let history

    beforeEach(() => {
        const initialState = {sportTest: { provinces: [{id: 1, name: 'A Coruña'}],
                sportTestTypes: [{id: 1, name: 'Running'}]}}
        const store = createStore(() => initialState);
        store.dispatch = jest.fn();
        history = createMemoryHistory();

        component = render(
            <Provider store={store}>
                <IntlProvider locale="en" messages={messages_en}>
                    <Router history={history}>
                        <FindSportTests/>
                    </Router>
                </IntlProvider>
            </Provider>
        )
    })

    test('show from input, to input, province input, sportTestType input and submit button', () => {
        component.getByLabelText(messages_en['project.global.fields.from'])
        component.getByLabelText(messages_en['project.global.fields.to'])
        component.getByLabelText(messages_en['project.global.fields.province'])
        component.getByLabelText(messages_en['project.global.fields.sportTestType'])
        const submit = component.queryByText(messages_en['project.global.buttons.search'])
        expect(submit).not.toBeNull()
    })

    test('can select a date in from input', () => {
        const date = "2021-06-10"
        const fromInput = component.getByLabelText(messages_en['project.global.fields.from'])

        fireEvent.change(fromInput, {target: {value: date}})

        expect(fromInput.value).toEqual(date)
    })

    test('can select a date in to input', () => {
        const date = "2021-06-10"
        const toInput = component.getByLabelText(messages_en['project.global.fields.to'])

        fireEvent.change(toInput, {target: {value: date}})

        expect(toInput.value).toEqual(date)
    })

    test('can select a province in province input', () => {
        const provinceId = "1"
        const provinceInput = component.getByLabelText(messages_en['project.global.fields.province'])

        component.getByText("A Coruña")
        fireEvent.change(provinceInput, {target: {value: provinceId}})

        expect(provinceInput.value).toEqual(provinceId)
    })

    test('can select a sportTestType in sportTestType input', () => {
        const sportTestTypeId = "1"
        const sportTestTypeInput = component.getByLabelText(messages_en['project.global.fields.sportTestType'])

        component.getByText("Running")
        fireEvent.change(sportTestTypeInput,  {target: {value: sportTestTypeId}})

        expect(sportTestTypeInput.value).toEqual(sportTestTypeId)
    })

    describe('Trying to find sport tests', () => {
        const date = "2021-06-10"
        const provinceId = 1
        const sportTestTypeId = 1

        let fromInput
        let toInput
        let provinceInput
        let sportTestTypeInput
        let searchButton

        beforeEach(() => {
            fromInput = component.getByLabelText(messages_en['project.global.fields.from'])
            toInput = component.getByLabelText(messages_en['project.global.fields.to'])
            provinceInput = component.getByLabelText(messages_en['project.global.fields.province'])
            sportTestTypeInput = component.getByLabelText(messages_en['project.global.fields.sportTestType'])
            searchButton = component.queryByText(messages_en['project.global.buttons.search'])
        })

        afterEach(() => actions.findSportTests.mockRestore())

        test('when not insert any date, province or sportTestType', () => {

            const findSportTestsSpy = jest.spyOn(actions, 'findSportTests').mockImplementation(
                (_criteria) => null
            )

            fireEvent.click(searchButton)

            expect(findSportTestsSpy.mock.calls[0][0])
                .toEqual({finishDate: "", page: 0, provinceId: null, startDate: "", typeId: null})
        })

        test('when insert dates, province and sportTestType', () => {
            const findSportTestsSpy = jest.spyOn(actions, 'findSportTests').mockImplementation(
                (_criteria) => null
            )

            fireEvent.change(fromInput, {target: {value: date}})
            fireEvent.change(toInput, {target: {value: date}})
            fireEvent.change(provinceInput, {target: {value: provinceId}})
            fireEvent.change(sportTestTypeInput,  {target: {value: sportTestTypeId}})
            fireEvent.click(searchButton)

            expect(findSportTestsSpy.mock.calls[0][0])
                .toEqual({finishDate: date, page: 0, provinceId: provinceId, startDate: date, typeId: sportTestTypeId})
        })

        test('when searching, path doesn´t change', () => {
            jest.spyOn(actions, 'findSportTests').mockImplementation(
                (_criteria) => null
            )

            fireEvent.change(fromInput, {target: {value: date}})
            fireEvent.change(toInput, {target: {value: date}})
            fireEvent.change(provinceInput, {target: {value: provinceId}})
            fireEvent.change(sportTestTypeInput,  {target: {value: sportTestTypeId}})
            fireEvent.click(searchButton)

            expect(history).toHaveLength(1)
            expect(history.location.pathname).toEqual('/')
        })
    })
})