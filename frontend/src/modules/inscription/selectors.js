const getModuleState = state => state.inscription

export const getLastInscribeInfo = state =>
  getModuleState(state).lastInscribeInfo

export const isRatingEnabled = state =>
  getModuleState(state).inscription
    ? getModuleState(state).inscription.ratingEnabled
    : null

export const getSportTestName = id => state => {
  const inscriptions = getModuleState(state).inscriptionsSearch?.result.items
  if (!inscriptions) return ''

  const inscription = inscriptions.find(ins => ins.id === id)

  return inscription ? inscription.sportTestName : ''
}

export const getInscription = state =>
  getModuleState(state).inscription

export const getInscriptionsSearch = state =>
  getModuleState(state).inscriptionsSearch
