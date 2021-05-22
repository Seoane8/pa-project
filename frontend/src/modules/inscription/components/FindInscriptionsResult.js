import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { FormattedMessage } from 'react-intl'

import * as actions from '../actions'
import * as selectors from '../selectors'
import { Pager } from '../../common'
import Inscriptions from './Inscriptions'

const FindInscriptionsResult = () => {
  const inscriptionsSearch = useSelector(selectors.getInscriptionsSearch)
  const dispatch = useDispatch()

  if (!inscriptionsSearch) {
    return null
  }

  if (inscriptionsSearch.result.items.length === 0) {
    return (
      <div className='alert alert-info' role='alert'>
        <FormattedMessage id='project.inscription.FindInscriptionResult.noInscriptions' />
      </div>
    )
  }

  return (

    <div>
      <Inscriptions inscriptions={inscriptionsSearch.result.items} />
      <Pager
        back={{
          enabled: inscriptionsSearch.criteria.page >= 1,
          onClick: () => dispatch(actions.previousFindInscriptionsResultPage(inscriptionsSearch.criteria))
        }}
        next={{
          enabled: inscriptionsSearch.result.existMoreItems,
          onClick: () => dispatch(actions.nextFindInscriptionsResultPage(inscriptionsSearch.criteria))
        }}
      />
    </div>

  )
}

export default FindInscriptionsResult
