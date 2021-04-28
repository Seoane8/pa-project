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
                <FormattedMessage id='project.global.fields.name'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.date'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {sporttests.map(sporttest =>
            <tr key={sporttest.id}>
                <td>{sporttest.name}</td>
                <td>{new Date(sporttest.date).toLocaleString()}</td>
            </tr>
        )}
        </tbody>

    </table>
);

SportTests.propTypes = {
    sporttests: PropTypes.array.isRequired
};

export default SportTests;