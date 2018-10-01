package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Participant{
@Id private String name;
   
   public void setName(String value) {
this.name = value;
    }
public String getName() {
return this.name;
       }
   }
