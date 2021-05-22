import React, { useState } from 'react'
import { FormattedMessage } from 'react-intl'
import PropTypes from 'prop-types'
import { Errors } from '../../common'
import backend from '../../../backend'
import ShowDorsal from './ShowDorsal'

const CollectDorsalForm = ({sportTestId}) => {
  
  const [cardNumber, setCardNumber] = useState('')
  const [inscriptionId, setInscriptionId] = useState('')
  const [dorsal, setDorsal] = useState(null)
  const [errors, setErrors] = useState(null)
  let form

  const handleSubmit = event => {

    event.preventDefault()

    if (form.checkValidity()) {

      backend.inscriptionService.collectDorsal(
        {inscriptionId, sportTestId, cardNumber},
        result => {
          setDorsal(result.dorsal)
          setErrors(null)
        },
        errors => setErrors(errors)
      )

    } else {
      setErrors(null)
      form.classList.add('was-validated')
    }
  }

  return (
    <div className='card text-left border-dark'>
      <h5 className='card-header text-center'>
        <FormattedMessage id='project.inscription.CollectDorsalForm.title' />
      </h5>
      <Errors errors={errors} onClose={() => setErrors(null)} />
      <ShowDorsal dorsal={dorsal} onClose={() => setDorsal(null)} />
      <div className='card-body'>
        <form ref={node => form = node}
          className="needs-validation" noValidate 
          onSubmit={(e) => handleSubmit(e)}>
          <div className='form-group justify-content-md-center'>
            <label htmlFor='inscriptionId' 
              className='col-form-label'>
              <FormattedMessage id='project.global.fields.inscriptionId'/>
            </label>
            <div>
              <input type='text' id='inscriptionId' className='form-control'
                value={inscriptionId}
                onChange={e => setInscriptionId(e.target.value)}
                autoFocus
                required/>
              <div className='invalid-feedback'>
                <FormattedMessage id='project.global.validator.required'/>
              </div>
            </div>
          </div>
          <div className='form-group justify-content-md-center'>
            <label htmlFor='cardNumber' 
              className='col-form-label'>
              <FormattedMessage id='project.global.fields.cardNumber'/>
            </label>
            <div>
              <input type='text' id='cardNumber' className='form-control'
                value={cardNumber}
                onChange={e => setCardNumber(e.target.value)}
                autoFocus
                minLength='14'
                maxLength='19'
                required/>
              <div className='invalid-feedback'>
                <FormattedMessage id='project.global.validator.cardNum'/>
              </div>
            </div>
          </div>
          <div className='form-group row justify-content-md-center'>
            <div className='col-md-auto'>
              <button type='submit' className='btn btn-primary'>
                <FormattedMessage id='project.global.buttons.collectDorsal'/>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  )
}

CollectDorsalForm.propTypes = {
  sportTestId: PropTypes.number.isRequired
}

export default CollectDorsalForm
