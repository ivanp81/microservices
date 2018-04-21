import React from 'react';
import PropTypes from 'prop-types';
import { Field, reduxForm } from 'redux-form';
import FieldInput from '../common/FieldInput';

export const AdvertiseSearchForm = ({ handleSubmit, pristine, reset, submitting, handleSearch }) => {
    return (
        <form onSubmit={handleSubmit(handleSearch)}>
            
            <Field
                type="text"
                name="q"
                label=""
                placeholder="Search advertises"
                component={FieldInput}
            />

            <div>
                <button type="submit" disabled={submitting} className="btn btn-primary"><i className="fa fa-search-o" aria-hidden="true" /> Search</button>
            </div>
        </form>
    );
};

const validate = values => {
    const errors = {};

    if (!values.q) {
        errors.q = 'Required';
    }

    return errors;
};



AdvertiseSearchForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    pristine: PropTypes.bool.isRequired,
    reset: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    handleSearch: PropTypes.func.isRequired
};



export default reduxForm({
    form: 'AdvertiseSearchForm',
    validate
})(AdvertiseSearchForm);
