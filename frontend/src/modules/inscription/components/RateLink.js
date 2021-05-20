import {Link} from "react-router-dom";
import PropTypes from "prop-types";
import React from "react";
import {FormattedMessage} from "react-intl";

const RateLink = ({id}) => {

    return (
        <Link to={`/inscription/rate-registration/${id}`}>
            <FormattedMessage id='project.global.fields.ratingaction'/>
        </Link>
    )

}

RateLink.propTypes = {
    id: PropTypes.number.isRequired
};

export default RateLink;