package it.joint.microservices.advertise.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.io.Serializable;

@Data
@SuppressWarnings("serial")
@Document(collection = "advertises")
public class Advertise implements Serializable {

	@Id
	private String id;
	private String title;
	private String content;
		
	public static class Builder {

		private String id;
		private String title;
		private String content;
		
        public Builder() {

        }

        public Builder withId(String id){
            this.id = id;
            return this; 
        }
        
        public Builder withTitle(String title){
            this.title = title;
            return this; 
        }
        
        public Builder withContent(String content){
            this.content = content;
            return this; 
        }
        
        public Advertise build(){

        	Advertise advertise = new Advertise(); 
        	
        	advertise.id = this.id;
        	advertise.title = this.title;
        	advertise.content = this.content;
        	
            return advertise;

        }
    }
}
