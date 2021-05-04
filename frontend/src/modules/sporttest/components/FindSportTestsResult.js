import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {Pager} from '../../common';
import SportTests from './SportTests';

const FindSportTestsResult = () => {

    const sportTestSearch = useSelector(selectors.getSportTestSearch);
    const provinces = useSelector(selectors.getProvinces);
    const sportTestTypes = useSelector(selectors.getSportTestTypes);
    const dispatch = useDispatch();

    if (!sportTestSearch) {
        return null;
    }

    if (sportTestSearch.result.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.sporttest.FindSportTestsResult.noSportTestsFound'/>
            </div>
        );
    }

    return (

        <div>
            <SportTests sporttests={sportTestSearch.result.items} provinces={provinces} sportTestTypes={sportTestTypes}/>
            <Pager
                back={{
                    enabled: sportTestSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindSportTestsResultPage(sportTestSearch.criteria))}}
                next={{
                    enabled: sportTestSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindSportTestsResultPage(sportTestSearch.criteria))}}/>
        </div>

    );
}

export default FindSportTestsResult;