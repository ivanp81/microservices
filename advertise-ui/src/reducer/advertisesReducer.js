import * as ActionType from '../action/ActionType';
import initialState from './initialState';
import _ from 'lodash';



const advertisesReducer = (state = initialState.advertisesReducer, action) => {
    switch(action.type) {
        case ActionType.GET_ADVERTISES_RESPONSE: {
            
        	console.log('advertisesReducer GET_ADVERTISES_RESPONSE=' + JSON.stringify(action.advertises));
        	
        	return {
                ...state, 
                advertises: _.assign(action.advertises)
            };
        }
        
        case ActionType.SEARCH_ADVERTISE_RESPONSE: {
            
        	console.log('advertisesReducer SEARCH_ADVERTISE_RESPONSE=' + JSON.stringify(action.advertises));
        	
        	return {
                ...state, 
                advertises: _.assign(action.advertises)
            };
        }

        case ActionType.CLEAN_ADVERTISE_LIST: {
            
        	console.log('advertisesReducer CLEAN_ADVERTISE_LIST');
        	
        	return {
                ...state, 
                advertises: []
            };
        }


        default: { return state; }
    }
};



export default advertisesReducer;