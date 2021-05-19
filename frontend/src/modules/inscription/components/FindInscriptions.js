import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const FindInscriptions = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {

        dispatch(actions.findInscriptions({page: 0}));
        history.push('/inscription/find-inscriptions-result');

    });

    return null;

}

export default FindInscriptions;