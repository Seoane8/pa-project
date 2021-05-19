import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

import InscriptionLink from './InscriptionLink';

const Inscriptions = ({inscriptions}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    {"ID"}
                </th>
                <th scope="col">
                    {"Fecha-Hora"}
                </th>
                <th scope="col">
                    {"Prueba"}
                </th>
                <th scope="col">
                    {"Dorsal"}
                </th>
                <th scope="col">
                    {"Tarjeta Bancaria"}
                </th>
                <th scope="col">
                    {"Recogido"}
                </th>
                <th scope="col">
                    {"Puntuación"}
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
                    <td>{inscription.sportTestName}</td>
                    <td>{inscription.dorsal}</td>
                    <td>{inscription.cardNumber}</td>
                    <td>{inscription.dorsalCollected}</td>
                    <td>{inscription.score == -1 ? "S/N" : inscription.score}</td>


                </tr>
        )}
        </tbody>

    </table>

);

Inscriptions.propTypes = {
    inscriptions: PropTypes.array.isRequired
};

export default Inscriptions;

