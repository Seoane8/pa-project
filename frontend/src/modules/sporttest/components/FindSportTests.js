import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import ProvinceSelector from "./ProvinceSelector";
import * as actions from '../actions';
import SportTestTypeSelector from "./SportTestTypeSelector";

const FindSportTests = () => {

    const dispatch = useDispatch();
    const [startDate, setStartDate] = useState('');
    const [finishDate, setFinishDate] = useState('');
    const [provinceId, setProvinceId] = useState('');
    const [typeId, setTypeId] = useState('');

    const handleSubmit = event => {
        event.preventDefault();
        dispatch(actions.findSportTests(
            {startDate: startDate,
                finishDate: finishDate,
                provinceId: toNumber(provinceId),
                typeId: toNumber(typeId),
                page: 0}));
    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return (

        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <input id="startDate" type="date" className="form-control mr-sm-2"
                   value={startDate} onChange={e => setStartDate(e.target.value)}/>

            <input id="finishDate" type="date" className="form-control mr-sm-2"
                   value={finishDate} onChange={e => setFinishDate(e.target.value)}/>

            <ProvinceSelector id="provinceId" className="custom-select my-1 mr-sm-2"
                   value={provinceId} onChange={e => setProvinceId(e.target.value)}/>

            <SportTestTypeSelector id="sportTestTypeId" className="custom-select my-1 mr-sm-2"
                   value={typeId} onChange={e => setTypeId(e.target.value)}/>

            <button type="submit" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search'/>
            </button>

        </form>
    )
}

export default FindSportTests;