import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import toastr from 'toastr';
import * as advertiseAction from '../../action/AdvertiseAction';
import AdvertiseList from './AdvertiseList';


export class AdvertiseListContainer extends React.Component {

    constructor() {
        super();

        this.state = {selectedAdvertiseId: undefined};

        this.handleAddAdvertise = this.handleAddAdvertise.bind(this);
        this.handleEditAdvertise = this.handleEditAdvertise.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleRowSelect = this.handleRowSelect.bind(this);
    }


    componentDidMount() {
        
    	console.log('AdvertiseListContainer getAdvertisesAction')
    	
    	this.props.action.getAdvertisesAction()
            .catch(error => {
                toastr.error(error);
            });
    }



    handleAddAdvertise() {
        this.props.history.push('/advertise');
    }



    handleEditAdvertise() {
        const selectedAdvertiseId = this.state.selectedAdvertiseId;

        if (selectedAdvertiseId) {
            this.setState({selectedAdvertiseId: undefined});
            this.props.history.push(`/advertise/${selectedAdvertiseId}`);
        }
    }



    handleDelete() {
        const selectedAdvertiseId = this.state.selectedAdvertiseId;

        if (selectedAdvertiseId) {
            this.setState({selectedAdvertiseId: undefined});
            this.props.action.deleteAdvertiseAction(selectedAdvertiseId)
                .catch(error => {
                    toastr.error(error);
                });
        }
    }



    handleRowSelect(row, isSelected) {
        if (isSelected) {
            this.setState({selectedAdvertiseId: row.id});
        }
    }



    render() {
        const { advertises } = this.props;

        if (!advertises) {
            return (
                <div>Loading...</div>
            );
        }

        return (
            <div className="container-fluid">
                <div className="row mt-3">
                    <div className="col">
                        <h1>Advertises</h1>
                    </div>
                </div>

                <div className="row mt-3">
                    <div className="col">
                        <div className="btn-group" role="group">
                            <button
                                type="button"
                                className="btn btn-primary"
                                onClick={this.handleAddAdvertise}
                            >
                                <i className="fa fa-plus" aria-hidden="true"/> New
                            </button>

                            <button
                                type="button"
                                className="btn btn-warning ml-2"
                                onClick={this.handleEditAdvertise}
                            >
                                <i className="fa fa-pencil" aria-hidden="true"/> Edit
                            </button>

                            <button
                                type="button"
                                className="btn btn-danger ml-2"
                                onClick={this.handleDelete}
                            >
                                <i className="fa fa-trash-o" aria-hidden="true" onClick={this.handleDelete}/> Delete
                            </button>
                        </div>
                    </div>
                </div>

                <div className="row">
                    <div className="col">
                        <AdvertiseList advertises={advertises} handleRowSelect={this.handleRowSelect}/>
                    </div>
                </div>
            </div>
        );
    }
}



const mapStateToProps = state => ({
    advertises: state.advertisesReducer.advertises
});



const mapDispatchToProps = dispatch => ({
    action: bindActionCreators(advertiseAction, dispatch)

});
 


AdvertiseListContainer.propTypes = {
    advertises: PropTypes.array,
    action: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired
};



export default connect(mapStateToProps, mapDispatchToProps)(AdvertiseListContainer);
