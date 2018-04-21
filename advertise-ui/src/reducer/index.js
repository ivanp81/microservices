import {combineReducers} from 'redux';
import {reducer as formReducer} from 'redux-form';
import advertisesReducer from './advertisesReducer';
import selectedAdvertiseReducer from './selectedAdvertiseReducer';
import apiReducer from './apiReducer';

export default combineReducers({
    advertisesReducer,
    selectedAdvertiseReducer,
    apiReducer,
    form: formReducer    
});


