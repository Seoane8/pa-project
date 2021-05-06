import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';

const SportTestDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const sportTest = useSelector(selectors.getSportTest);
    const provinces = useSelector(selectors.getProvinces);
    const sportTestTypes = useSelector(selectors.getSportTestTypes)
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        const sportTestId = Number(id);

        if (!Number.isNaN(sportTestId)) {
            dispatch(actions.findSportTestById(sportTestId));
        }

        return () => dispatch(actions.clearSportTest());

    }, [id, dispatch]);

    if (!sportTest) {
        return null;
    }

    return (

        <div>

            <BackLink/>
            <div className="card text-center">
                <h5 className="card-title">{sportTest.name}</h5>
            </div>

            <div className="card text-left">
                <div className="card-body">
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.date'/>:&nbsp;
                        {new Date(sportTest.date).toLocaleString()}

                    </p>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.sportTestType'/>:&nbsp;
                        {selectors.getSportTestTypeName(sportTestTypes, sportTest.type)}
                    </p>

                    <p className="card-text">
                       <FormattedMessage id='project.global.fields.province'/>:&nbsp;
                        {selectors.getProvinceName(provinces, sportTest.province)}
                    </p>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.location'/>:&nbsp;
                        {sportTest.location}
                    </p>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.rating'/>
                        : {sportTest.rating != 0 ? <FormattedNumber value={sportTest.rating}/> :
                        <FormattedMessage id='project.sporttest.SportTestDetails.notScoredAnything'/>}
                    </p>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.maxParticipants'/>
                        : <FormattedNumber value={sportTest.maxParticipants}/>
                    </p>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.numParticipants'/>
                        : <FormattedNumber value={sportTest.numParticipants}/>
                    </p>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.description'/>:&nbsp;
                        {sportTest.description}
                    </p>

                    <p className="card-text text-center font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>
                        : <FormattedNumber value={sportTest.price}/>€
                    </p>
                </div>
            </div>
        </div>

    );

}

export default SportTestDetails;
