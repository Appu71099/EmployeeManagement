package com.demo.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.dto.ApiResponse;
import com.demo.employee.dto.StateDto;
import com.demo.employee.entity.State;
import com.demo.employee.services.StateService;

@RestController
@RequestMapping("/stateApi")
public class StateController {

	@Autowired
	public StateService stateServ;
	
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<State>> addState(@RequestBody StateDto stateDto){
		
		try {
			
			ApiResponse<State> response = stateServ.addState(stateDto);
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			 e.printStackTrace();
			 
			 ApiResponse<State> errorResponse = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ApiResponse<List<State>>> getAllState(){
		try {
			ApiResponse<List<State>> response = stateServ.getState();
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			 ApiResponse<List<State>> errorResponse = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
}
