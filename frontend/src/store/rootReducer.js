import { combineReducers } from 'redux'

import app from '../modules/app'
import users from '../modules/users'
import sportTest from '../modules/sporttest'
import inscription from '../modules/inscription'

const rootReducer = combineReducers({
  app: app.reducer,
  users: users.reducer,
  sportTest: sportTest.reducer,
  inscription: inscription.reducer
})

export default rootReducer
