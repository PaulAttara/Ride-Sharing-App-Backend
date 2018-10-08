package ca.mcgill.ecse321.ridesharing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import ca.mcgill.ecse321.ridesharing.controller.*;
import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.*;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RideSharingApplicationTests {

	

	@Test
	public void contextLoads() {

	}
	
	@Mock 
	private RideSharingRepository userDao;
	
	@InjectMocks
	private RideSharingController controller;

	private static final String USER_KEY = "TestUser";
	private static final String NONEXISTING_Key = "NotAUser";
	
//	@BeforeEach
//	void setMockOutput() {
//	  when(userDao.getUser(anyString())).then( (InvocationOnMock invocation) -> {
//	    if(invocation.getArgumentAt(0).equals(USER_KEY)) {
//	      User user = new User();
//	      participant.setName(USER_KEY);
//	      return participant;
//	    } else {
//	      return null;
//	    }
//	  });
//	}
}