package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum Status{
	CANCELLED;
    private String cancelled;
   
   public void setCancelled(String value) {
      this.cancelled = value;
   }
   
   @Id
   public String getCancelled() {
      return this.cancelled;
   }
   
   }
