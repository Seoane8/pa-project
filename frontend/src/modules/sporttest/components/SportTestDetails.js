import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import {InscriptionForm, CollectDorsalForm} from '../../inscription';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';

const SportTestDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const isAdmin = useSelector(users.selectors.isAdmin);
    const inscriptionEnabled = useSelector(selectors.isInscriptionEnabled);
    const collectDorsalEnabled = useSelector(selectors.isCollectDorsalEnabled);
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
            <div className='card' >
                <div className="card-header text-center">
                    <h2>{sportTest.name}</h2>
                </div>
                <div className="card-body text-center row align-items-stretch">
                    <div className='col mb-5 mb-md-0 justify-content-around'>
                        <p className="p-2 border-bottom border-secondary">
                            {sportTest.description}
                        </p>

                        <div className='row justify-content-center my-4'>
                            <div className="px-3 py-1 border-right border-left">
                                <span>{new Date(sportTest.date).toLocaleString()}</span>
                            </div>
                            <div className="px-3 py-1 border-right border-left">
                                <span>
                                    {sportTest.location}
                                    , {selectors.getProvinceName(provinces, sportTest.province)}
                                </span>
                            </div>
                            <div className="px-3 py-1 border-right border-left">
                                <span>{selectors.getSportTestTypeName(sportTestTypes, sportTest.type)}</span>
                            </div>
                        </div>

                        <div className='row justify-content-around'>
                            <div className="card text-center m-1 border-secondary">
                                <p className='card-header p-2 bg-secondary text-light'>
                                    <FormattedMessage id='project.global.fields.numParticipants'/>
                                </p>
                                <p className='h5 py-3 px-4 m-0 text-secondary'>
                                    <FormattedNumber value={sportTest.numParticipants}/>
                                    &nbsp;/ <FormattedNumber value={sportTest.maxParticipants}/>
                                </p>
                            </div>
                            <div className="card text-center m-1 border-primary">
                                <p className='card-header p-2 bg-primary text-light'>
                                    <FormattedMessage id='project.global.fields.price'/>
                                </p>
                                <p className='h5 py-3 px-4 m-0 text-primary'>
                                    <FormattedNumber value={sportTest.price}/>€
                                </p>
                            </div>
                            <div className="card text-center m-1 border-secondary">
                                <p className='card-header p-2 bg-secondary text-light'>
                                    <FormattedMessage id='project.global.fields.rating'/>
                                </p>
                                <p className='h5 py-3 px-4 m-0 text-secondary'>
                                    {sportTest.rating !== 0
                                        ?  <FormattedNumber value={sportTest.rating}/>
                                        : 'S/N'
                                    }
                                </p>
                            </div>
                        </div>
                    </div>

                    {loggedIn && !isAdmin && inscriptionEnabled 
                        && <div className='col-md-5 m-auto'>
                            <InscriptionForm sportTestId={id} />
                        </div>
                    }

                    {loggedIn && isAdmin && collectDorsalEnabled 
                        && <div className='col-md-5 m-auto'>
                            <CollectDorsalForm sportTestId={id} />
                        </div>
                    }
                </div>
            </div>
        </div>

    );

}

export default SportTestDetails;
