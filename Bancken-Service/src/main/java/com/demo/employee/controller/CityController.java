package com.demo.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.dto.ApiResponse;
import com.demo.employee.dto.CityDto;
import com.demo.employee.dto.CityListResponseDto;
import com.demo.employee.entity.City;
import com.demo.employee.services.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {

	@Autowired 
	private CityService cityService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<City>> addCity(@RequestBody CityDto cityDto){
		
		try {
			ApiResponse<City> response = cityService.addCity(cityDto);
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				
				return ResponseEntity.status(response.getStatus()).body(response);
			}
			
		}catch(Exception e) {
			ApiResponse<City> errorResponse = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<City>>> getList(){
		try {
			ApiResponse<List<City>> response = cityService.getCityList();
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ApiResponse<List<City>> errorResponse = new ApiResponse<>(null,  HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping("/getByState/{id}")
	public ResponseEntity<ApiResponse<CityListResponseDto>> getCitysByState(@PathVariable Long id){
		
		try {
			ApiResponse<CityListResponseDto> response = cityService.getCitiesByState(id);
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			ApiResponse<CityListResponseDto> errorResponse = new ApiResponse<>(null,  HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
}
