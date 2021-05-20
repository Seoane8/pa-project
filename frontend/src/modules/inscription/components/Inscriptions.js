import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

import { SportTestLink } from '../../common';
import {useSelector} from "react-redux";
import users from "../../users";
import RateLink from "./RateLink";

const Inscriptions = ({inscriptions}) => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const isAdmin = useSelector(users.selectors.isAdmin);

    return (

        <table className="table table-striped table-hover">

            <thead>
            <tr>
                <th scope="col">
                    {"ID"}
                </th>
                <th scope="col">
                    <FormattedMessage id="project.global.fields.date" />
                </th>
                <th scope="col">
                    <FormattedMessage id="project.global.fields.sportTest" />
                </th>
                <th scope="col">
                    <FormattedMessage id="project.global.fields.dorsal" />
                </th>
                <th scope="col">
                    <FormattedMessage id="project.global.fields.cardNumber" />
                </th>
                <th scope="col">
                    <FormattedMessage id="project.global.fields.collected" />
                </th>
                <th scope="col">
                    <FormattedMessage id="project.global.fields.score" />
                </th>
            </tr>
            </thead>

            <tbody>
            {inscriptions.map(inscription =>
                <tr key={inscription.id}>
                    <td> {inscription.id}</td>
                    <td>
                        <FormattedDate value={new Date(inscription.date)}/> - <FormattedTime value={new Date(inscription.date)}/>
                    </td>
                    <td>
                        <SportTestLink
                            id={inscription.sportTestId}
                            name={inscription.sportTestName}
                        />
                    </td>
                    <td>{inscription.dorsal}</td>
                    <td>{`****${inscription.cardNumber}`}</td>
                    <td>{inscription.dorsalCollected ? '✅' : '❌'}</td>
                    <td>
                        {inscription.score !== -1 
                            ? inscription.score 
                            : !isAdmin && inscription.ratingEnabled
                                ? <RateLink id={inscription.id} />
                                : <FormattedMessage id='project.global.fields.notAvailable' />
                        }
                    </td>
                </tr>
            )}
            </tbody>

        </table>

    );
}

Inscriptions.propTypes = {
    inscriptions: PropTypes.array.isRequired
};

export default Inscriptions;

