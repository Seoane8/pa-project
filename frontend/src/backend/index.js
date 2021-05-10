import {init} from './appFetch';
import * as userService from './userService';
import * as sportTestService from './sportTestService'
import * as inscriptionService from './inscriptionService'

export {default as NetworkError} from "./NetworkError";

export default {init, userService, sportTestService, inscriptionService};
