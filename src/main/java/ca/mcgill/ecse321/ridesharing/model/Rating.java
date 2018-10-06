package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum Rating{
	ONE;
    public String one;
    
    public void setOne(String value) {
        this.one = value;
     }
     
     @Id
     public String getCancelled() {
        return this.one;
     }
   }
