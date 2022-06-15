package com.example.ACsecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCotroller {
	
    @GetMapping ("/someRandomPage")
	public String home() {
		return "home";
	}
	
}
