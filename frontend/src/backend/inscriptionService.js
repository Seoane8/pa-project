import {config, appFetch} from './appFetch';

const baseUrl = '/inscriptions'

export const findInscriptions = ({page}, onSuccess) =>
    appFetch(`${baseUrl}?page=${page}`, config('GET'), onSuccess);

export const findInscription = (inscriptionId, onSuccess) =>
    appFetch(`${baseUrl}/${inscriptionId}`, config('GET'), onSuccess);
