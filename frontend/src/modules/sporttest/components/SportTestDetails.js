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
    const sportTest = useSelector(selectors.getSportTest());
    const provinces = useSelector(selectors.getProvinces());
    const sportTestTypes = useSelector(selectors.getSportTestTypes())
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
                <div className="card-body">
                    <h5 className="card-title">{sportTest.name}</h5>

                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.date'/>:&nbsp;
                        {sportTest.date}
                    </p>

                    {<p className="card-text">
                        <FormattedMessage id='project.global.fields.sportTestType'/>:&nbsp;
                        {selectors.getSportTestTypeName(sportTestTypes, sportTest.typeId)}
                    </p>
                    <p className="card-text">
                       <FormattedMessage id='project.global.fields.province'/>:&nbsp;
                        {selectors.getProvinceName(provinces, sportTest.provinceId)}
                    </p>


                    <p className="card-text">{sportTest.description}</p>
                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>
                        : <FormattedNumber value={sportTest.price}/>€
                    </p>}
                </div>
            </div>
        </div>

    );

}

export default SportTestDetails;
