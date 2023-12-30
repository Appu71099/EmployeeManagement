package com.demo.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.employee.dto.ApiResponse;
import com.demo.employee.dto.StateDto;
import com.demo.employee.entity.State;
import com.demo.employee.repository.StateRepository;

@Service
public class StateService {

	
	@Autowired
	private StateRepository stateRepo;
	
	
	public ApiResponse<State> addState(StateDto stateDto){
		
		try {
			State state = new State();
			state.setName(stateDto.getName());
			state.setStateCode(stateDto.getStateCode());
			
			State addState = stateRepo.save(state);
			return new ApiResponse<>(addState, HttpStatus.OK.value());
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	
	public ApiResponse<List<State>> getState(){
		try {
			List<State> getState = stateRepo.findAll();
			return new ApiResponse<>(getState,HttpStatus.OK.value());
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}
