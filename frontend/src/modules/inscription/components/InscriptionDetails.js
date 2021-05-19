import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedTime} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {BackLink} from '../../common';

const InscriptionDetails = () => {

    const {id} = useParams();
    const inscription = useSelector(selectors.getInscription);
    const dispatch = useDispatch();

    useEffect(() => {

        if (!Number.isNaN(id)) {
            dispatch(actions.findInscription(id));
        }

        return () => dispatch(actions.clearInscription());

    }, [id, dispatch]);

    if (!inscription) {
        return null;
    }

    return (

        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">
                        {inscription.id}
                    </h5>
                    <h6 className="card-subtitle text-muted">
                        <FormattedDate value={new Date(inscription.date)}/>
                        -
                        <FormattedTime value={new Date(inscription.date)}/>
                    </h6>
                    <p className="card-text">
                        {"hola"} - {"que tal"}
                    </p>
                </div>
            </div>
        </div>

    );

}

export default InscriptionDetails;