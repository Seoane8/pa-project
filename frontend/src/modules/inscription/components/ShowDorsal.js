import React from 'react';
import { FormattedMessage, FormattedNumber } from 'react-intl';

const ShowDorsal = ({dorsal, onClose}) => {

  if (!dorsal) {
    return null;
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

export default ShowDorsal;
