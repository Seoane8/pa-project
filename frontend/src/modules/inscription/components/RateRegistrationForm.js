import backend from "../../../backend";
import React, {useState} from "react";
import {Errors} from "../../common";
import {FormattedMessage} from "react-intl";

const RateRegistrationForm = ({inscriptionId}) =>{

    const [score, setScore] = useState(null)
    const [errors, setErrors] = useState(null)
    let form

    const handleSubmit = event => {

        event.preventDefault()

        if (form.checkValidity()) {

            backend.inscriptionService.scoreTest(
                {inscriptionId, score},
                result => setScore(result.score),
                errors => setErrors(errors)
            )

        } else {
            setErrors(null)
            form.classList.add('was-validated')
        }
    }

    return(
        <div className='card text-left'>
            <Errors errors={errors} onClose={() => setErrors(null)} />
            <div className='card-body'>
                <form ref={node => form = node}
                      className="needs-validation" noValidate
                      onSubmit={(e) => handleSubmit(e)}>
                    <div className='form-group row justify-content-md-center'>
                        <select className="form-select" onChange={e => setScore(e.target.value)}>
                            <option selected>
                                <FormattedMessage id='project.global.buttons.selectscore'/>
                            </option>
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