import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { FormattedMessage } from 'react-intl'

import ProvinceSelector from './ProvinceSelector'
import * as actions from '../actions'
import SportTestTypeSelector from './SportTestTypeSelector'

const FindSportTests = () => {
  const dispatch = useDispatch()
  const [startDate, setStartDate] = useState('')
  const [finishDate, setFinishDate] = useState('')
  const [provinceId, setProvinceId] = useState('')
  const [typeId, setTypeId] = useState('')

  const handleSubmit = event => {
    event.preventDefault()
    dispatch(actions.findSportTests(
      {
        startDate: startDate,
        finishDate: finishDate,
        provinceId: toNumber(provinceId),
        typeId: toNumber(typeId),
        page: 0
      }))
  }

  const toNumber = value => value.length > 0 ? Number(value) : null

  return (

    <form className='form-row justify-content-center' onSubmit={e => handleSubmit(e)}>
      <div className='row justify-content-center align-items-end'>
        <div className='form-group mx-2 row col'>
          <label htmlFor='startDate' className='col-form-label p-0'>
            <FormattedMessage id='project.global.fields.from' />
          </label>
          <input
            id='startDate'
            type='date'
            className='form-control'
            value={startDate}
            onChange={e => setStartDate(e.target.value)}
          />
        </div>
        <div className='form-group mx-2 row col'>
          <label htmlFor='finishDate' className='col-form-label p-0'>
            <FormattedMessage id='project.global.fields.to' />
          </label>
          <input
            id='finishDate'
            type='date'
            className='form-control'
            value={finishDate}
            onChange={e => setFinishDate(e.target.value)}
          />
        </div>
        <div className='form-group mx-2 row col'>
          <label htmlFor='finishDate' className='col-form-label p-0'>
            <FormattedMessage id='project.global.fields.province' />
          </label>
          <ProvinceSelector
            id='provinceId'
            className='custom-select'
            value={provinceId}
            onChange={e => setProvinceId(e.target.value)}
          />
        </div>
        <div className='form-group mx-2 row col'>
          <label htmlFor='finishDate' className='col-form-label p-0'>
            <FormattedMessage id='project.global.fields.sportTestType' />
          </label>
          <SportTestTypeSelector
            id='sportTestTypeId'
            className='custom-select'
            value={typeId}
            onChange={e => setTypeId(e.target.value)}
          />
        </div>
        <div className='form-group mx-2 col-auto'>
          <button type='submit' className='btn btn-primary'>
            <FormattedMessage id='project.global.buttons.search' />
          </button>
        </div>
      </div>
    </form>
  )
}

export default FindSportTests
