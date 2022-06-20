package com.example.ACsecurity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.ACsecurity.model.Category;
import com.example.ACsecurity.model.CategoryRepository;
import com.example.ACsecurity.model.Page;
import com.example.ACsecurity.model.PageRepository;

@ControllerAdvice
public class Common {
	
	@Autowired
	private PageRepository pageRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@ModelAttribute
	public void sharedData(Model model) {
		
		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
		
		List<Category> categories = categoryRepo.findAll();
		
		model.addAttribute("cpages", pages);
		model.addAttribute("ccategories", categories);
	    
	
	
	}

	
}
