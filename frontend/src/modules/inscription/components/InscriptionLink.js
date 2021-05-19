import React from 'react';
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';

const InscriptionLink = ({id}) => {

    return (
        <Link to={`/sporttest/inscription-details/${id}`}>
            {id}
        </Link>
    );

}

InscriptionLink.propTypes = {
    id: PropTypes.number.isRequired
};

export default InscriptionLink;