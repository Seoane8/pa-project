import React from 'react'
import PropTypes from 'prop-types'

const Success = ({ children, onClose }) => children && (
  <div className='alert alert-success alert-dismissible fade show' role='alert'>
    {children}
    <button
      type='button' className='close' data-dismiss='alert' aria-label='Close'
      onClick={() => onClose()}
    >
      <span aria-hidden='true'>&times;</span>
    </button>
  </div>
)

Success.propTypes = {
  children: PropTypes.node,
  onClose: PropTypes.func.isRequired
}

export default Success
