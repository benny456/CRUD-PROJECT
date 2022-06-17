package com.example.ACsecurity.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ACsecurity.model.Category;
import com.example.ACsecurity.model.CategoryRepository;
import com.example.ACsecurity.model.Page;
import com.example.ACsecurity.model.Product;
import com.example.ACsecurity.model.ProductRepository;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
     
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public String index(Model model) {
		
		List<Product>products = productRepo.findAll();

		model.addAttribute("products" ,products);
		
		return "/admin/products/index";
	}
	
	@GetMapping("/add") 
	public String add(Product product,Model model) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		return "admin/products/add";
	}
	
	@PostMapping("/add")
	public String add (@Valid Product product, BindingResult bindingResult,
			MultipartFile file, RedirectAttributes redirectAttributes,
			Model model) throws IOException {
		
		List<Category> categories = categoryRepo.findAll();
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("categories",categories);
			return "admin/products/add";
		}
		// uploading image and checking if its jpg or png
		boolean fileOk = false;
		byte[] bytes = file.getBytes();
		String filename = file.getOriginalFilename();
		Path path = Paths.get("src/main/resources/static/media/" + filename);
		if(filename.endsWith("jpg")|| filename.endsWith("png")) {
			
			fileOk = true;
		}
		
		redirectAttributes.addFlashAttribute("message","Product added");
		redirectAttributes.addFlashAttribute("alertClass","alert-success");
		
		String slug = product.getName().toLowerCase().replace(" ","-");
		
		Product productExists = productRepo.findBySlug(slug);
		
		if(!fileOk) {
			
			redirectAttributes.addFlashAttribute("message","Image must be a jpg or a png");
			redirectAttributes.addFlashAttribute("alertClass","alert-danger");
			redirectAttributes.addFlashAttribute("product",product);
			
		}
		
		else if (productExists != null) {
			redirectAttributes.addFlashAttribute("message","Product exists, choose another");
			redirectAttributes.addFlashAttribute("alertClass","alert-danger");
			redirectAttributes.addFlashAttribute("product",product);
			
			
		} else {
			product.setSlug(slug);
			product.setImage(filename);
			productRepo.save(product);
			Files.write(path, bytes);
		}
		
		return "redirect:/admin/products/add";
	}
}
