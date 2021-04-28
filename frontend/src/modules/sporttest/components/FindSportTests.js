import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';

const FindSportTests = () => {

    const dispatch = useDispatch();
    const [startDate, setStartDate] = useState('');
    const [finishDate, setFinishDate] = useState('');

    const handleSubmit = event => {
        event.preventDefault();
        dispatch(actions.findSportTests(
            {startDate: startDate,
            finishDate: finishDate, page: 0}));
    }

    return (

        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <input id="startDate" type="date" className="form-control mr-sm-2"
                   value={startDate} onChange={e => setStartDate(e.target.value)}/>

            <input id="finishDate" type="date" className="form-control mr-sm-2"
                   value={finishDate} onChange={e => setFinishDate(e.target.value)}/>

            <button type="submit" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search'/>
            </button>

        </form>
    )
}

export default FindSportTests;