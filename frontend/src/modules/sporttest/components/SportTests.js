import React from 'react'
import { FormattedMessage } from 'react-intl'
import PropTypes from 'prop-types'
import SportTestLink from '../../common/components/SportTestLink'

import * as selectors from '../selectors'

const SportTests = ({ sporttests, provinces, sportTestTypes }) => (

  <div className='table-responsive'>
    <table className='table table-striped table-hover'>

      <thead>
        <tr>
          <th scope='col'>
            <FormattedMessage id='project.global.fields.name' />
          </th>
          <th scope='col'>
            <FormattedMessage id='project.global.fields.date' />
          </th>
          <th scope='col'>
            <FormattedMessage id='project.global.fields.rating' />
          </th>
          <th scope='col'>
            <FormattedMessage id='project.global.fields.province' />
          </th>
          <th scope='col'>
            <FormattedMessage id='project.global.fields.sportTestType' />
          </th>
        </tr>
      </thead>

      <tbody>
        {sporttests.map(sporttest =>
          <tr key={sporttest.id}>
            <td><SportTestLink id={sporttest.id} name={sporttest.name} /></td>
            <td>{new Date(sporttest.date).toLocaleString()}</td>
            <td>{sporttest.rating !== 0
              ? sporttest.rating
              : <FormattedMessage id='project.sporttest.SportTestDetails.notScoredAnything' />}
            </td>
            <td>{selectors.getProvinceName(provinces, sporttest.provinceId)}</td>
            <td>{selectors.getSportTestTypeName(sportTestTypes, sporttest.sportTestType)}</td>
          </tr>
        )}
      </tbody>

    </table>
  </div>
)

SportTests.propTypes = {
  sporttests: PropTypes.array.isRequired,
  provinces: PropTypes.array.isRequired,
  sportTestTypes: PropTypes.array.isRequired
}

export default SportTests
