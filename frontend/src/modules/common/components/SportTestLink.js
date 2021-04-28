import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const SportTestLink = ({id, name}) => {

    return (
        <Link to={`/sporttest/sport-test-details/${id}`}>
            {name}
        </Link>
    )

}

SportTestLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
};

export default SportTestLink;