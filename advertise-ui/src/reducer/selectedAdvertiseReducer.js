import * as ActionType from '../action/ActionType';
import initialState from './initialState';
import _ from 'lodash';


const selectedAdvertiseReducer = (state = initialState.selectedAdvertiseReducer, action) => {
    switch(action.type) {

        case ActionType.GET_ADVERTISE_RESPONSE: {

        	console.log('selectedAdvertiseReducer GET_ADVERTISE_RESPONSE=' + JSON.stringify(action.advertise));
        	        	
        	return {
                ...state,
                advertise: _.assign(action.advertise)
            };
        }
        
        case ActionType.CLEAN_ADVERTISE_FORM: {
        	
        	console.log('selectedAdvertiseReducer CLEAN_ADVERTISE_FORM');

        	return {
                ...state,
                advertise: _.assign(undefined)
            };
        }

        default: { return state; }
    }
};


export default selectedAdvertiseReducer;