import React from 'react'
import { FormattedMessage, FormattedNumber } from 'react-intl'
import PropTypes from 'prop-types'

const ShowDorsal = ({dorsal, onClose}) => {

  if (!dorsal) {
    return null
  }

  return (

    <div className="alert alert-success alert-dismissible fade show"
      role="alert"
    >

      <FormattedMessage id='project.inscription.ShowDorsal.message'/>
      : <FormattedNumber value={dorsal} />

      <button type="button" className="close" data-dismiss="alert" aria-label="Close" 
        onClick={() => onClose()}>
        <span aria-hidden="true">&times;</span>
      </button>

    </div>

  );

}

ShowDorsal.propTypes = {
  dorsal: PropTypes.number,
  onClose: PropTypes.func.isRequired
}

export default ShowDorsal;
