package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.GraphicCategory;

public interface GraphicCategoryRepo extends JpaRepository<GraphicCategory, Integer> {

//	@Query(value="select * from graphiccategory where categoryName=:categoryName",nativeQuery=true)
	public GraphicCategory findByCategory(String category);

}
