import React, { useState } from 'react'
import { FormattedMessage } from 'react-intl'
import { useDispatch } from 'react-redux'
import { useHistory } from 'react-router'
import PropTypes from 'prop-types'
import { Errors } from '../../common'
import * as actions from '../actions'

const InscriptionForm = ({ sportTestId }) => {
  const dispatch = useDispatch()
  const history = useHistory()
  const [cardNumber, setCardNumber] = useState('')
  const [errors, setErrors] = useState(null)
  let form

  const handleSubmit = event => {
    event.preventDefault()

    if (form.checkValidity()) {
      dispatch(actions.inscribe(
        sportTestId,
        cardNumber,
        () => history.push('/inscription/inscribe-completed'),
        errors => setErrors(errors)
      ))
    } else {
      setErrors(null)
      form.classList.add('was-validated')
    }
  }

  return (
    <div className='card text-center border-dark'>
      <h5 className='card-header text'>
        <FormattedMessage id='project.inscription.InscriptionForm.title' />
      </h5>
      <Errors errors={errors} onClose={() => setErrors(null)} />
      <div className='card-body'>
        <form
          ref={node => form = node}
          className='needs-validation' noValidate
          onSubmit={(e) => handleSubmit(e)}
        >
          <div className='form-group justify-content-md-center'>
            <label
              htmlFor='cardNumber'
              className='col-form-label'
            >
              <FormattedMessage id='project.global.fields.cardNumber' />
            </label>
            <div>
              <input
                type='text' id='cardNumber' className='form-control'
                value={cardNumber}
                onChange={e => setCardNumber(e.target.value)}
                autoFocus
                minLength='14'
                maxLength='19'
                required
              />
              <div className='invalid-feedback'>
                <FormattedMessage id='project.global.validator.cardNum' />
              </div>
            </div>
          </div>
          <div className='form-group row justify-content-md-center'>
            <div className='col-md-auto'>
              <button type='submit' className='btn btn-primary'>
                <FormattedMessage id='project.global.buttons.inscribe' />
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  )
}

InscriptionForm.propTypes = {
  sportTestId: PropTypes.number.isRequired
}

export default InscriptionForm
