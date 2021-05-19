import * as actions from './actions'
import * as actionTypes from './actionTypes'
import reducer from './reducer'
import * as selectors from './selectors'

export {default as InscriptionForm} from './components/InscriptionForm'
export {default as InscriptionResult} from './components/InscriptionResult'
export {default as CollectDorsalForm} from './components/CollectDorsalForm'

export default {actions, actionTypes, reducer, selectors}