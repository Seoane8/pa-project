import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import {SportTestLink} from '../../common';

const SportTests = ({sporttests}) => (

    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.department'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.name'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {sporttests.map(sporttest =>
            <tr key={sporttest.id}>
                <td><SportTestLink id={sporttest.id} name={sporttest.name}/></td>
            </tr>
        )}
        </tbody>

    </table>
);

SportTests.propTypes = {
    sporttests: PropTypes.array.isRequiredÇ
};

export default SportTests;