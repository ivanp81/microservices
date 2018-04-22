import React from 'react';



const Footer = () => {
    return (
        <footer>
            <h2 className="display-4 text-center py-5 my-4">Features</h2>

            <nav className="nav justify-content-center nav-pills flex-column flex-md-row">
            	<a className="nav-link active" href="#frontend" data-toggle="tab">Frontend</a>
            	<a className="nav-link" href="#backend" data-toggle="tab">Backend</a>
            </nav>

            <div className="tab-content py-5">
            	
	            <div className="tab-pane active" id="frontend">
	            <h3>Frontend</h3>
		            <ul>
		            	<li>React JS</li>
		            	<li>React Router</li>
		            	<li>Redux</li>
		                <li>Bootstrap</li>                    
		            </ul>
		        </div>                
            
            	<div className="tab-pane" id="backend">
                    <h3>Backend</h3>
                    <ul>
	            		<li>Spring Boot</li>  
	            		<li>MongoDB</li>  
	            		<li>ElasticSearch</li>
	            		<li>RabbitMQ</li>  
	                </ul>
                </div>

            </div>
        </footer>
    );
};



export default Footer;
