import {config, appFetch} from './appFetch'

const baseUrl = '/sportTestsSearch'

export const findSportTests = ({startDate, finishDate, provinceId,
                                   typeId, page},
                               onSuccess) => {

    let path = `${baseUrl}/sportTests?page=${page}`;

    path += startDate ? `&startDate=${startDate}` : "";
    path += finishDate ? `&finishDate=${finishDate}` : "";
    path += provinceId ? `&provinceId=${provinceId}` : "";
    path += typeId ? `&typeId=${typeId}` : "";

    appFetch(path, config('GET'), onSuccess);

}

export const findAllProvinces = (onSuccess) => {
    const path = `${baseUrl}/provinces`

    appFetch(path, config('GET'), onSuccess)
}

export const findAllSportTestTypes = (onSuccess) => {
    const path = `${baseUrl}/types`

    appFetch(path, config('GET'), onSuccess)
}