package com.Biniam.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ACsecurity.model.Page;
import com.example.ACsecurity.model.User;

import com.Biniam.Test.ControllerTest;

public class ControllerTest {
	    @Test
	    public void testgetmail() {
	    User user = new User();
	    user.setEmail("user@yahoo.com");

      assertEquals("user@yahoo.com", user.getEmail());
	    }
	 
	    
	    @Test
	    public void testgetmail1() {
		    User user = new User();
		    user.setEmail("user@yahoo.com");

	      
		  assertEquals("fhdjgr@yellowbook.com", user.getEmail());
	    }
	
}
