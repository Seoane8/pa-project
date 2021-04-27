import {config, appFetch} from './appFetch';

export const findSportTests = ({startDate, finishDate, page},
                               onSuccess) => {

    let path = `/sportTests?page=${page}`;

    path += startDate ? `&startDate=${startDate}` : "";
    path += finishDate ? `&finishDate=${finishDate}` : "";

    appFetch(path, config('GET'), onSuccess);

}