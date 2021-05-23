const getModuleState = state => state.inscription

export const getLastInscribeInfo = state =>
  getModuleState(state).lastInscribeInfo

export const getSportTestName = id => state => {
  const inscriptions = getModuleState(state).inscriptionsSearch?.result.items
  if (!inscriptions) return ''

  const inscription = inscriptions.find(ins => ins.id === id)

  return inscription ? inscription.sportTestName : ''
}

export const getInscriptionsSearch = state =>
  getModuleState(state).inscriptionsSearch
