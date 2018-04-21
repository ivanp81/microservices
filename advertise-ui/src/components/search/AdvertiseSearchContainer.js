import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import toastr from 'toastr';
import * as advertiseAction from '../../action/AdvertiseAction';
import AdvertiseList from '../advertise/AdvertiseList';
import AdvertiseSearchForm from './AdvertiseSearchForm';



export class AdvertiseSearchContainer extends React.Component {

    constructor() {
        super();

        this.state = {selectedAdvertiseId: undefined};
        this.handleRowSelect = this.handleRowSelect.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
    }


    componentDidMount() {
    	this.props.action.cleanAdvertiseListAction()
    	.catch(error => {
    		toastr.error(error);
    	});
    }
    
    handleSearch(values) {
    	
    	const q = values.q
    	    	
    	this.props.action.searchAdvertisesAction(q)
        	.catch(error => {
        		toastr.error(error);
        });
    }
    
    handleRowSelect(row, isSelected) {
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
                        <h1>Search Advertises</h1>
                    </div>
                </div>
                	
                <div className="row">
                	<div className="col-4">
        				<AdvertiseSearchForm
        					handleSearch={this.handleSearch}
        				/>
        			</div>
        		</div>
        		
        		<div className="row">
        		&nbsp;
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



AdvertiseSearchContainer.propTypes = {
    advertises: PropTypes.array,
    action: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired
};



export default connect(mapStateToProps, mapDispatchToProps)(AdvertiseSearchContainer);
