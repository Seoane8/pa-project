const getModuleState = state => state.inscription

export const getLastInscribeInfo = state =>
    getModuleState(state).lastInscribeInfo