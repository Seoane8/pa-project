const getModuleState = state => state.sportTest

export const getProvinces = state =>
    getModuleState(state).provinces

export const getProvinceName = (provinces, id) => {
    if (!provinces) return ''

    const province = provinces.find(province => province.id === id)

    return province ? province.name : ''
}

export const getSportTestTypes = state =>
    getModuleState(state).sportTestTypes

export const getSportTestTypeName = (sportTestTypes, id) => {
    if (!sportTestTypes) return ''

    const type = sportTestTypes.find(type => type.id === id)

    return type ? type.name : ''
}

export const getSportTestSearch = state =>
    getModuleState(state).sportTestSearch

export const getSportTest = state =>
    getModuleState(state).sportTest;

export const isInscriptionEnabled = state =>
    getModuleState(state).sportTest 
        ? getModuleState(state).sportTest.resgistrationEnabled
        : null