package com.demo.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.employee.dto.CompanyDto;
import com.demo.employee.entity.City;
import com.demo.employee.entity.Employee;
import com.demo.employee.entity.State;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

//	List<Employee> findByCity(String city);
//	
//	List<Employee> findByDesignation(String designation);
//	
//	List<Employee> findByCityAndDesignation(String city, String designation);
	
//	@Query("SELECT NEW CityKeyValuePair(e.id, e.city) FROM Employee e GROUP BY e.city")
//	List<CityKeyValuePair> findDistinctCities();
	
	@Query("SELECT DISTINCT e.company FROM Employee e")
	List<String> findDistinctCompany();
	 
	@Query("SELECT DISTINCT e.jobRole FROM Employee e")
	 List<String> findDistinctJobRole();
	
	
	List<Employee> findByState(State stateId);
	
	List<Employee> findByCity(City city);
	
	List<Employee> findByCityAndState(City city,State stateId);
	
	
	
	
	
	
}
