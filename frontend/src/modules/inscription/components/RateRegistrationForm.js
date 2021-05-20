import React, {useState} from "react";
import {Errors, Success} from "../../common";
import {FormattedMessage} from "react-intl";
import { useDispatch } from "react-redux";
import * as actions from '../actions';

const RateRegistrationForm = ({inscriptionId}) =>{
    const dispatch = useDispatch()
    const [score, setScore] = useState(1)
    const [errors, setErrors] = useState(null)
    const [success, setSuccess] = useState(null)
    let form

    const handleSubmit = event => {

        event.preventDefault()

        if (form.checkValidity()) {

            dispatch(actions.score(
                parseInt(inscriptionId), parseInt(score),
                () => setSuccess(<FormattedMessage id='project.global.RateRegistration.success'/>),
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
            <Success message={success} onClose={() => setSuccess(null)} />
            <div className='card-body'>
                <form ref={node => form = node}
                      className="needs-validation" noValidate
                      onSubmit={(e) => handleSubmit(e)}>
                    <div className='form-group row justify-content-md-center'>
                        <select className="form-select" onChange={e => setScore(e.target.value)} defaultValue='1'>
                            <option value='1'>1</option>
                            <option value='2'>2</option>
                            <option value='3'>3</option>
                            <option value='4'>4</option>
                            <option value='5'>5</option>
                        </select>
                    </div>
                    <div className='form-group row justify-content-md-center'>
                        <div className='col-md-auto'>
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

export default RateRegistrationForm;