package com.demo.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.employee.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByStateId(Long stateId);
}
