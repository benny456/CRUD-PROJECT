package com.Biniam.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.ACsecurity.model.User;

import com.Biniam.Test.ControllerTest;

public class ControllerTest {
	    @Test
	    public void testgetmail() {
	    User user = new User();
	    user.setEmail("user@yahoo.com");

	  assertEquals("user@yahoo.com", user.getEmail());
//	  assertEquals("fhdjgr@yellowbook.com", user.getEmail());
	}
	}


