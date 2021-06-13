import React from 'react'
import { createStore } from 'redux'
import { createMemoryHistory } from 'history'
import { fireEvent, render } from '@testing-library/react'
import { Provider } from 'react-redux'
import { IntlProvider } from 'react-intl'
import { Router } from 'react-router-dom'

import InscriptionForm from './InscriptionForm'
import messages_en from '../../../i18n/messages/messages_en'
import * as actions from '../actions'

const  generateRandomCardNumber = (len) => 
  Math.random().toString(10).substring(2,len+3)

describe('Rendering InscriptionForm component', () => {
  const sportTestId = 1
  let component
  let history

  beforeEach(() => {
    const initialState = {inscription: {lastInscribeInfo: null}}
    const store = createStore(() => initialState)
    store.dispatch = jest.fn()
    history = createMemoryHistory()

    component = render(
      <Provider store={store}>
        <IntlProvider locale='en' messages={messages_en}>
          <Router history={history}>
            <InscriptionForm sportTestId={sportTestId} />
          </Router>
        </IntlProvider>
      </Provider>
    )
  })

  test('show tittle, card number input and submit button', () => {
    component.getByLabelText(messages_en['project.global.fields.cardNumber'])
    const [tittle, submit] = component.queryAllByText(messages_en['project.global.buttons.inscribe'])
    expect(tittle).not.toBeNull()
    expect(submit).not.toBeNull()
  })

  test('can be written to the card number input', () => {
    const cardNumber = generateRandomCardNumber(16)
    const cardNumberInput = component.getByLabelText(messages_en['project.global.fields.cardNumber'])

    fireEvent.change(cardNumberInput, {target: {value: cardNumber}})

    expect(cardNumberInput.value).toEqual(cardNumber)
  })

  describe('Trying to inscribe in the sport test', () => {
    let cardNumberInput
    let inscribeButton

    beforeEach(() => {
      cardNumberInput = component.getByLabelText(messages_en['project.global.fields.cardNumber'])
      inscribeButton = component.queryAllByText(messages_en['project.global.buttons.inscribe'])[1]
    })

    afterEach(() => actions.inscribe.mockRestore())



    test('when not insert card number, not dispatch action', () => {
      const inscribeSpy = jest.spyOn(actions, 'inscribe').mockImplementation(
        (_sportTestId, _cardNumber, _onSuccess, _onErrors) => null
      )

      fireEvent.click(inscribeButton)

      expect(inscribeSpy).not.toHaveBeenCalled()
    })

    test('when insert card nuumber, dispatch action correctly', () => {
      const cardNumber = generateRandomCardNumber(16)
      const inscribeSpy = jest.spyOn(actions, 'inscribe').mockImplementation(
        (_sportTestId, _cardNumber, _onSuccess, _onErrors) => null
      )

      fireEvent.change(cardNumberInput, {target: {value: cardNumber}})
      fireEvent.click(inscribeButton)

      expect(inscribeSpy.mock.calls[0][0]).toEqual(sportTestId)
      expect(inscribeSpy.mock.calls[0][1]).toEqual(cardNumber)
    })

    test('when the request is successful, change the path', () => {
      const cardNumber = generateRandomCardNumber(16)
      jest.spyOn(actions, 'inscribe').mockImplementation(
        (_sportTestId, _cardNumber, onSuccess, _onErrors) => onSuccess()
      )

      fireEvent.change(cardNumberInput, {target: {value: cardNumber}})
      fireEvent.click(inscribeButton)

      expect(history).toHaveLength(2)
      expect(history.location.pathname).toEqual('/inscription/inscribe-completed')
    })

    test('when the request fails, show backend errors', () => {
      const cardNumber = generateRandomCardNumber(16)
      const backendError = 'Some error has ocurred'
      jest.spyOn(actions, 'inscribe').mockImplementation(
        (_sportTestId, _cardNumber, _onSuccess, onErrors) => 
          onErrors({globalError: backendError})
      )

      fireEvent.change(cardNumberInput, {target: {value: cardNumber}})
      fireEvent.click(inscribeButton)
      
      const error = component.getByRole('alert')
      expect(error).toHaveTextContent(backendError)
      expect(history).toHaveLength(1)
      expect(history.location.pathname).toEqual('/')
    })

  })

})