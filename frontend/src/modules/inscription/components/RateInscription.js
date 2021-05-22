import React from "react";

import {BackLink} from '../../common';
import {useParams} from "react-router-dom";

import * as selectors from "../selectors";
import RateInscriptionForm from "./RateInscriptionForm";
import { useSelector } from "react-redux";


const RateInscription = () => {

    const {id} = useParams();
    const sportTestName = useSelector(selectors.getSportTestName(parseInt(id)));

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
                    <RateInscriptionForm
                    inscriptionId={id}/>

                </div>
            </div>
        </div>
    );
}

export default RateInscription;