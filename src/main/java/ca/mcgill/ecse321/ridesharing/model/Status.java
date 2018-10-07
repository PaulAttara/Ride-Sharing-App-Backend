package ca.mcgill.ecse321.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public enum Status {
	Pending, Accepted, EnRoute, Ended, Cancelled
}