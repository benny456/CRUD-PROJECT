package com.example.ACsecurity.model;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PageRepository extends JpaRepository<Page,Integer> {
    
	Page findBySlug(String slug);
	
	Page findBySlugAndIdNot (String slug, int index);
	
	List<Page> findAllByOrderBySortingAsc();
}