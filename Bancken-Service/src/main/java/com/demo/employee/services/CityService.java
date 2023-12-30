package com.demo.employee.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.employee.dto.ApiResponse;
import com.demo.employee.dto.CityDto;
import com.demo.employee.dto.CityListResponseDto;
import com.demo.employee.entity.City;
import com.demo.employee.entity.State;
import com.demo.employee.repository.CityRepository;
import com.demo.employee.repository.StateRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private StateRepository stateRepos;
	
	//Add City
	public ApiResponse<City> addCity(CityDto cityDto){
		
		try {
			City city = new City();
			city.setName(cityDto.getName());
			
//			State state = new State();
			city.setState(cityDto.getState());
			
			City saveCity = cityRepo.save(city);
			return new ApiResponse<>(saveCity, HttpStatus.OK.value());
		}catch(Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	
	
	//Get City
	public ApiResponse<List<City>> getCityList(){
		try {
			
			List<City> cityList = cityRepo.findAll();
			
			return new ApiResponse<>(cityList,HttpStatus.OK.value());
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null,HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	
	//Get City By State
	public ApiResponse<CityListResponseDto> getCitiesByState(Long stateId) {
		try {
			State state = stateRepos.findById(stateId)
					.orElseThrow(() -> new EntityNotFoundException("State not found with id: " + stateId));

			List<City> cityList = cityRepo.findByStateId(stateId);
			
			List<CityDto> cityDtos = new ArrayList<>();
			
			cityList.forEach(city -> {
				CityDto cityDto = new CityDto();
				cityDto.setId(city.getId());
				cityDto.setName(city.getName());
				cityDtos.add(cityDto);
			});
			
			CityListResponseDto cityListResponseDto = new CityListResponseDto();
			cityListResponseDto.setState(state.getName());
			cityListResponseDto.setCity(cityDtos);
			
			return new ApiResponse<>(cityListResponseDto,HttpStatus.OK.value());
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null,HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
}
