import React, {useEffect} from "react";

import {BackLink} from '../../common';
import {useParams} from "react-router-dom";

import * as selectors from "../selectors";
import RateRegistrationForm from "./RateRegistrationForm";


const RateRegistration = () => {

    const {id} = useParams();
    const sportTestName = selectors.getSportTestName(id);

    if (!sportTestName){
        return null;
    }

    return (
        <div>
            <BackLink/>
            <div className='card' >
                <div className="card-header text-center">
                    <h2>
                        {sportTestName}
                    </h2>
                </div>
                <div className="card-body text-center">
                    <RateRegistrationForm
                    inscriptionId={id}/>

                </div>
            </div>
        </div>
    );
}

export default RateRegistration;