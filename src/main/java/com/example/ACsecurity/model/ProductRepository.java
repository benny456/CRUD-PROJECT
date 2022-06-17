package com.example.ACsecurity.model;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository <Product,Integer>{
	
	Product findBySlug(String slug);

}
