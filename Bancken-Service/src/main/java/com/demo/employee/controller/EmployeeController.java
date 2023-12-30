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
import com.demo.employee.dto.CompanyDto;
import com.demo.employee.dto.EmployeeDTO;
import com.demo.employee.dto.JobRoleDto;
import com.demo.employee.dto.SearchParametersDto;
import com.demo.employee.entity.Employee;
import com.demo.employee.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;
	

	
	@PostMapping("/saveEmployee")
	public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody EmployeeDTO empDto){
		
		try {
			ApiResponse<Employee> response = empServ.addEmployee(empDto);
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}
			else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			ApiResponse<Employee> errorResponse = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	
	@GetMapping("/getEmployee")
	public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployee(){
		try {
			ApiResponse<List<Employee>> response = empServ.getAllEmployee();
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ApiResponse<List<Employee>> errorRespone = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRespone);
		}
	}

	@GetMapping("/getCompaanys")
	public ResponseEntity<ApiResponse<List<CompanyDto>>> getAllCompany(){
		
		try {
			ApiResponse<List<CompanyDto>> response = empServ.getAllCompany();
			
			if(response.getStatus() == HttpStatus.OK.value()){
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ApiResponse<List<CompanyDto>> errorRespone = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRespone);
		}
	}
	
	
	@GetMapping("/getJobRoles")
	public ResponseEntity<ApiResponse<List<JobRoleDto>>> getJobRoles(){
		try {
			ApiResponse<List<JobRoleDto>> response = empServ.getAllJobRole();
			
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ApiResponse<List<JobRoleDto>> errorRespone = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRespone);
		}
	}
	
	
	@PostMapping("/searchEmployee")
	public ResponseEntity<ApiResponse<List<Employee>>> searchEmployee(@RequestBody SearchParametersDto searchParameter){
		try {
			ApiResponse<List<Employee>> response = empServ.searchEmployee(searchParameter);
			if(response.getStatus() == HttpStatus.OK.value()) {
				return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ApiResponse<List<Employee>> errorRespone = new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRespone);
		}
	}
	
	
}
