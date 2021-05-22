import React, {useState} from "react";
import {Errors, Success} from "../../common";
import {FormattedMessage} from "react-intl";
import { useDispatch } from "react-redux";
import * as actions from '../actions';

const RateInscriptionForm = ({inscriptionId}) =>{
    const dispatch = useDispatch()
    const [score, setScore] = useState('')
    const [errors, setErrors] = useState(null)
    const [success, setSuccess] = useState(null)
    let form

    const handleSubmit = event => {

        event.preventDefault()

        if (form.checkValidity()) {

            dispatch(actions.score(
                parseInt(inscriptionId), parseInt(score),
                () => {
                    setSuccess(<FormattedMessage id='project.global.RateInscription.success'/>)
                    setErrors(null)
                },
                errors => setErrors(errors)
            ))

        } else {
            setErrors(null)
            form.classList.add('was-validated')
        }
    }

    return(
        <div className='card text-left'>
            <Errors errors={errors} onClose={() => setErrors(null)} />
            <Success onClose={() => setSuccess(null)}>
                {success}
            </Success>
            <div className='card-body'>
                <form ref={node => form = node}
                      className="needs-validation justify-content-center" noValidate
                      onSubmit={(e) => handleSubmit(e)}>
                    <div className='form-group row justify-content-center'>
                        <div className='col-auto'>
                            <select  
                                className="form-control form-select" 
                                value={score}
                                onChange={e => setScore(e.target.value)}
                                required
                            >
                                <option values=''></option>
                                <option value='1'>1</option>
                                <option value='2'>2</option>
                                <option value='3'>3</option>
                                <option value='4'>4</option>
                                <option value='5'>5</option>
                            </select>
                            <div className='invalid-feedback'>
                                <FormattedMessage id='project.global.validator.required'/>
                            </div>
                        </div>
                    </div>
                    <div className='form-group row justify-content-center'>
                        <div className='col-auto'>
                            <button type='submit' className='btn btn-primary'>
                                <FormattedMessage id='project.global.buttons.score'/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default RateInscriptionForm;