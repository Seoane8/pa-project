import {config, appFetch} from './appFetch';

const baseUrl = '/sportTestsSearch'

export const findSportTests = ({startDate, finishDate, page},
                               onSuccess) => {

    let path = `${baseUrl}/sportTests?page=${page}`;

    path += startDate ? `&startDate=${startDate}` : "";
    path += finishDate ? `&finishDate=${finishDate}` : "";

    appFetch(path, config('GET'), onSuccess);

}