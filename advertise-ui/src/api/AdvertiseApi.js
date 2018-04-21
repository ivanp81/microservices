import * as ApiConstants from './ApiConstants';

class AdvertiseApi {
    
	static getAdvertise(advertiseId) {

		console.log('AdvertiseApi getAdvertise ID=' + advertiseId)
    	
		const request = new Request(ApiConstants.ADVERTISE_API_URL  + "/" + advertiseId, {
	          method: 'GET',
	          headers: new Headers({
	            'Content-Type': 'application/json'
	          })
	        });
				
		return fetch(request).then(response => {
    		return response.json();
        });
	}
	
    static getAllAdvertises() {
    	
    	console.log('AdvertiseApi getAdvertises')
    	    	
    	return fetch(ApiConstants.ADVERTISE_API_URL).then(response => {
    		return response.json();
        });
    }
    
    static saveAdvertise(advertise) {
        
    	const request = new Request(ApiConstants.ADVERTISE_API_URL, {
          method: 'POST',
          headers: new Headers({
            'Content-Type': 'application/json'
          }), 
          body: JSON.stringify(advertise)
        });

        return fetch(request).then(response => {
          return response.json();
        });
      }
    
    static deleteAdvertise(advertiseId) {
        const request = new Request(ApiConstants.ADVERTISE_API_URL + "/" + advertiseId, {
          method: 'DELETE',
          headers: new Headers({
            'Content-Type': 'application/json'
          })
        });

        return fetch(request).then(response => {
         
        });
      }
    
    static searchAdvertises(q) {
    	
    	console.log('AdvertiseApi searchAdvertises q=' + q)
    	    	
    	return fetch(ApiConstants.ADVERTISE_SEARCH_API_URL + "/_search?q=" + q).then(response => {
    		return response.json();
        });
    }
}

export default AdvertiseApi;
