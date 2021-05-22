import React from 'react'
import { FormattedMessage, FormattedNumber } from 'react-intl'
import PropTypes from 'prop-types'
import Success from '../../common/components/Success'

const ShowDorsal = ({ dorsal, onClose }) => (

  <Success onClose={onClose}>
    {dorsal
      ? <>
        <FormattedMessage id='project.inscription.ShowDorsal.message' />
        : <FormattedNumber value={dorsal} />
      </>
      : null}
  </Success>

)

ShowDorsal.propTypes = {
  dorsal: PropTypes.number,
  onClose: PropTypes.func.isRequired
}

export default ShowDorsal
