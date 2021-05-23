import React from 'react'
import { useSelector } from 'react-redux'
import { FormattedMessage, FormattedNumber } from 'react-intl'

import { BackLink } from '../../common'

import * as selectors from '../selectors'

const InscriptionResult = () => {
  const lastInscribeInfo = useSelector(selectors.getLastInscribeInfo)

  if (!lastInscribeInfo) {
    return null
  }

  return (
    <div>
      <BackLink/>
      <div className='alert alert-success' role='alert'>
        <FormattedMessage id='project.inscription.InscribeCompleted.inscribedWithId' />
        : <FormattedNumber value={lastInscribeInfo.id} />
        <br />
        <FormattedMessage id='project.inscription.InscribeCompleted.yourDorsalIs' />
        : <FormattedNumber value={lastInscribeInfo.dorsal} />
      </div>
    </div>
  )
}

export default InscriptionResult
