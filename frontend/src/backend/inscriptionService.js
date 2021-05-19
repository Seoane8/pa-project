import {config, appFetch} from './appFetch';

const baseUrl = '/inscriptions'

export const findInscriptions = ({page}, onSuccess) =>
    appFetch(`${baseUrl}?page=${page}`, config('GET'), onSuccess);

export const findInscription = (inscriptionId, onSuccess) =>
    appFetch(`${baseUrl}/${inscriptionId}`, config('GET'), onSuccess);

export const inscribe = (inscribeParams, onSuccess, onErrors) =>
  appFetch(baseUrl, config('POST', inscribeParams), onSuccess, onErrors)

export const collectDorsal = (params, onSuccess, onErrors) => {
  const {inscriptionId, ...collectDorsalParams} = params
  const path = `${baseUrl}/${inscriptionId}/collect`

  appFetch(path, config('POST', collectDorsalParams), onSuccess, onErrors)
}