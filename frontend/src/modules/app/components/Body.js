import React from 'react'
import { useSelector } from 'react-redux'
import { Route, Switch } from 'react-router-dom'

import AppGlobalComponents from './AppGlobalComponents'
import Home from './Home'
import users, { Login, SignUp, UpdateProfile, ChangePassword, Logout } from '../../users'

import { SportTestDetails } from '../../sporttest'
import { InscriptionResult, FindInscriptionsResult, FindInscriptions, RateInscription } from '../../inscription'

const Body = () => {
  const loggedIn = useSelector(users.selectors.isLoggedIn)

  return (

    <div className='container'>
      <br />
      <AppGlobalComponents />
      <Switch>
        <Route exact path='/'><Home /></Route>
        <Route exact path='/sporttest/sport-test-details/:id'><SportTestDetails /></Route>
        {loggedIn && <Route exact path='/inscription/find-inscriptions'><FindInscriptions /></Route>}
        {loggedIn && <Route exact path='/inscription/find-inscriptions-result'><FindInscriptionsResult /></Route>}
        {loggedIn && <Route exact path='/inscription/rate-inscription/:id'><RateInscription /></Route>}
        {loggedIn && <Route exact path='/inscription/inscribe-completed'><InscriptionResult /></Route>}
        {loggedIn && <Route exact path='/users/update-profile'><UpdateProfile /></Route>}
        {loggedIn && <Route exact path='/users/change-password'><ChangePassword /></Route>}
        {loggedIn && <Route exact path='/users/logout'><Logout /></Route>}
        {!loggedIn && <Route exact path='/users/login'><Login /></Route>}
        {!loggedIn && <Route exact path='/users/signup'><SignUp /></Route>}
        <Route><Home /></Route>
      </Switch>
    </div>

  )
}

export default Body
