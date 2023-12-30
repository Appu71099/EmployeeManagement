package com.demo.employee.dto;

import com.demo.employee.entity.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CityDto {

	private Long id;
	private String name;
    private State state;
}
