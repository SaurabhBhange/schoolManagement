package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.school.model.GraphicCategory;
import com.school.repository.GraphicCategoryRepo;

@Service
public class GraphicCategoryService {
	
	@Autowired
	@Qualifier("graphicCategoryRepo")
	private GraphicCategoryRepo graphicCategoryRepo;
	
	public GraphicCategory createCategory(GraphicCategory c){
    try{		
    	return  graphicCategoryRepo.save(c);	    	
		}
    catch (Exception e) {
    	return null;
    	}
   }
	
	public List<GraphicCategory> getAllCategory()
	{
		return graphicCategoryRepo.findAll();
	}
	
	
public void deleteCategory(int category_id) {
		
	graphicCategoryRepo.deleteById(category_id);
	}	


public GraphicCategory getCategoryByName(String category) {
	try {
		return graphicCategoryRepo.findByCategory(category);
		 
	}catch (Exception e) {
		System.out.println(e);
		return null;
	}
}

}