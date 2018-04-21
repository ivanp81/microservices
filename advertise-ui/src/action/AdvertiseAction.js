import * as ActionType from './ActionType';
import AdvertiseApi from '../api/AdvertiseApi';
import { ApiCallBeginAction, ApiCallErrorAction } from './ApiAction';

export const cleanAdvertiseListResponse = () => ({
    type: ActionType.CLEAN_ADVERTISE_LIST
});

export function cleanAdvertiseListAction() {
	return (dispatch) => {
	
		dispatch(cleanAdvertiseListResponse());
	};
}

export const cleanFormAdvertiseResponse = () => ({
    type: ActionType.CLEAN_ADVERTISE_FORM
});

export function cleanFormAdvertiseAction() {
	return (dispatch) => {
	
		dispatch(cleanFormAdvertiseResponse());
	};
}

export const getAdvertisesResponse = advertises => ({
    type: ActionType.GET_ADVERTISES_RESPONSE,
    advertises
});



export function getAdvertisesAction() {
    return (dispatch) => {

    	console.log('AdvertiseAction getAdvertisesAction')
    	
        dispatch(ApiCallBeginAction());

        return AdvertiseApi.getAllAdvertises()
            .then(advertises => {
                dispatch(getAdvertisesResponse(advertises));
            }).catch(error => {
                throw error;
            });
    };
}

export const getAdvertiseResponse = advertiseFound => ({
    type: ActionType.GET_ADVERTISE_RESPONSE,
    advertise: advertiseFound
});



export function getAdvertiseAction(advertiseId) {
    
	console.log('AdvertiseAction getAdvertiseAction ID=' + advertiseId)
	
	
	return (dispatch) => {

        dispatch(ApiCallBeginAction());

        return AdvertiseApi.getAdvertise(advertiseId)
            .then(advertise => {
                dispatch(getAdvertiseResponse(advertise));
            }).catch(error => {
                throw error;
            });
    };
}


export const addNewAdvertiseResponse = () => ({
    type: ActionType.ADD_NEW_ADVERTISE_RESPONSE
});



export const updateExistingAdvertiseResponse = () => ({
    type: ActionType.UPDATE_EXISTING_ADVERTISE_RESPONSE
});



export function saveAdvertiseAction(advertiseBeingAddedOrEdited) {
    return function (dispatch) {

        dispatch(ApiCallBeginAction());

        return AdvertiseApi.saveAdvertise(advertiseBeingAddedOrEdited)
            .then(() => {
                if (advertiseBeingAddedOrEdited.id) {
                    dispatch(updateExistingAdvertiseResponse());
                } else {
                    dispatch(addNewAdvertiseResponse());
                }
            }).then(() => {
                dispatch(getAdvertisesAction());
            }).catch(error => {
                dispatch(ApiCallErrorAction());
                throw (error);
            });
    };
}

export const deleteAdvertiseResponse = () => ({
    type: ActionType.DELETE_ADVERTISE_RESPONSE
});



export function deleteAdvertiseAction(advertiseId) {
    return (dispatch) => {

        dispatch(ApiCallBeginAction());

        return AdvertiseApi.deleteAdvertise(advertiseId)
            .then(() => {
                dispatch(deleteAdvertiseResponse());
            }).then(() => {
                dispatch(getAdvertisesAction());
            }).catch(error => {
                throw error;
            });
    };
}

export const searchAdvertisesResponse = advertises => ({
    type: ActionType.SEARCH_ADVERTISE_RESPONSE,
    advertises
});

export function searchAdvertisesAction(q) {
    return (dispatch) => {

    	console.log('AdvertiseAction searchAdvertisesAction')
    	
        dispatch(ApiCallBeginAction());

        return AdvertiseApi.searchAdvertises(q)
            .then(advertises => {
                dispatch(searchAdvertisesResponse(advertises));
            }).catch(error => {
                throw error;
            });
    };
}