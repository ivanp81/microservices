package it.joint.microservices.advertise.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@SuppressWarnings("serial")
@Document(collection = "advertises")
public class Advertise implements Serializable {

	@Id
	private String id;

	private String title;
	
	private String content;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean equals(Object o) {

		if (o == null)
			return false;
		if (!(o instanceof Advertise))
			return false;

		Advertise other = (Advertise) o;
		
		if (!this.id.equals(other.id))
			return false;

		return true;
	}

	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
	
	public String toString() {
		
		return "id: " + this.id;
	}
	
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
