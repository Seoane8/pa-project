import {config, appFetch} from './appFetch'

const baseUrl = '/inscriptions'

export const inscribe = (inscribeParams, onSuccess, onErrors) =>
  appFetch(baseUrl, config('POST', inscribeParams), onSuccess, onErrors)