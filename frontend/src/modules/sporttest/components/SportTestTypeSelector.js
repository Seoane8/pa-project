import React from 'react'
import { useSelector } from 'react-redux'
import { FormattedMessage } from 'react-intl'
import PropTypes from 'prop-types'

import * as selectors from '../selectors'

const SportTestTypeSelector = (selectProps) => {
  const sportTestTypes = useSelector(selectors.getSportTestTypes)

  return (

    <select {...selectProps}>

      <FormattedMessage id='project.sporttest.SportTestTypeSelector.allSportTestTypes'>
        {message => (<option value=''>{message}</option>)}
      </FormattedMessage>

      {sportTestTypes && sportTestTypes.map(sportTestType =>
        <option key={sportTestType.id} value={sportTestType.id}>{sportTestType.name}</option>
      )}

    </select>
  )
}

SportTestTypeSelector.propTypes = {
  selectProps: PropTypes.object
}

export default SportTestTypeSelector
