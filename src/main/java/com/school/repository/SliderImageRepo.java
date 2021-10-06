package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.SlidingImage;

public interface SliderImageRepo extends JpaRepository<SlidingImage, Integer> {

}
