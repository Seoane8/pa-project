const getModuleState = state => state.inscription

export const getLastInscribeInfo = state =>
    getModuleState(state).lastInscribeInfo

export const getInscription = state =>
    getModuleState(state).inscription;

export const getInscriptionsSearch = state =>
    getModuleState(state).inscriptionsSearch;
