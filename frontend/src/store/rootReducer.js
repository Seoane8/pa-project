import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import sportTest from '../modules/sporttest'

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    sportTest: sportTest.reducer

});

export default rootReducer;
