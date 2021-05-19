import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

import InscriptionLink from './InscriptionLink';

const Inscriptions = ({inscriptions}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    {"Holaaa"}
                </th>
                <th scope="col">
                    {"Fechaaa primo"}
                </th>
                <th scope="col">
                    {"Fechaaa primo"}
                </th>
                <th scope="col">
                    {"Fechaaa primo"}
                </th>
            </tr>
        </thead>

        <tbody>
            {inscriptions.map(inscription =>
                <tr key={inscription.id}>
                    <td><InscriptionLink id={inscription.id}/></td>
                    <td>
                        <FormattedDate value={new Date(inscription.date)}/> - <FormattedTime value={new Date(inscription.date)}/>
                    </td>
                </tr>
        )}
        </tbody>

    </table>

);

Inscriptions.propTypes = {
    inscriptions: PropTypes.array.isRequired
};

export default Inscriptions;

