package com.demo.employee.dto;

import com.demo.employee.entity.City;
import com.demo.employee.entity.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SearchParametersDto {

	private State state;
	private City city;
	private String companyName;
	private String jobRole;
}
