package com.demo.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.employee.entity.State;

public interface StateRepository extends JpaRepository<State,Long>{

}
