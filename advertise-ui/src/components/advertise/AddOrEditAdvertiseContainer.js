import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import toastr from 'toastr';
import * as advertiseAction from '../../action/AdvertiseAction';
import AdvertiseForm from './AdvertiseForm'; // eslint-disable-line import/no-named-as-default

export class AddOrEditAdvertiseContainer extends React.Component {


    constructor() {
        super();
        this.handleSave = this.handleSave.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
    	console.log('AddOrEditAdvertiseContainer constructor');

    }

    componentDidMount() {
        
    	console.log('AddOrEditAdvertiseContainer getAdvertiseAction ID=' + this.props.match.params.id);
    	
    	if(this.props.match.params.id)
    	this.props.action.getAdvertiseAction(this.props.match.params.id)
            .catch(error => {
                toastr.error(error);
            });
    }
    
    componentWillUnmount() {
    	
    	console.log('AdvertiseAction cleanFormAdvertiseAction');
    	this.props.action.cleanFormAdvertiseAction();
    }

    handleSave(values) {
        const advertise = {
            id: values.id,
            title: values.title,
            content: values.content
        };

        this.props.action.saveAdvertiseAction(advertise)
            .then(() => {
                toastr.success('Advertise saved');
                this.props.history.push('/advertises');
            }).catch(error => {
                toastr.error(error);
            });
    }

    handleCancel(event) {
        event.preventDefault();
        this.props.history.replace('/advertises');
    }

    render() {
        const { advertise } = this.props;
        const heading = advertise && advertise.id ? 'Edit' : 'Add';
        
        console.log('AddOrEditAdvertiseContainer render advertise=' + JSON.stringify(this.props.advertise));
		
        
        return (
            <div className="container">
                <AdvertiseForm
                    heading={heading}
                    handleSave={this.handleSave}
                    handleCancel={this.handleCancel}
                    initialValues={this.props.advertise}
                />
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {

	console.log('AddOrEditAdvertiseContainer mapStateToProps ' + JSON.stringify(state.selectedAdvertiseReducer.advertise));

	const advertiseId = ownProps.match.params.id; //from the path '/advertise/:id'

	if (advertiseId && state.selectedAdvertiseReducer.advertise && advertiseId === state.selectedAdvertiseReducer.advertise.id) {
		
		return {
			advertise: state.selectedAdvertiseReducer.advertise
         };
    }

};

const mapDispatchToProps = dispatch => ({
    action: bindActionCreators({ ...advertiseAction }, dispatch)
});



AddOrEditAdvertiseContainer.propTypes = {
    action: PropTypes.object.isRequired,
    history: PropTypes.object,
    advertise: PropTypes.object,
    match: PropTypes.object.isRequired
};



export default connect(mapStateToProps, mapDispatchToProps)(AddOrEditAdvertiseContainer);
