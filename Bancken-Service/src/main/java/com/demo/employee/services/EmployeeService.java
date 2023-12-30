package com.demo.employee.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.employee.dto.ApiResponse;
import com.demo.employee.dto.CompanyDto;
import com.demo.employee.dto.EmployeeDTO;
import com.demo.employee.dto.JobRoleDto;
import com.demo.employee.dto.SearchParametersDto;
import com.demo.employee.entity.City;
import com.demo.employee.entity.Employee;
import com.demo.employee.entity.State;
import com.demo.employee.repository.CityRepository;
import com.demo.employee.repository.EmployeeRepository;
import com.demo.employee.repository.StateRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private StateRepository stateRepo;

	// Entity To DTO Mapper
	@SuppressWarnings("unused")
	private Employee mapDtoToEntity(EmployeeDTO empDto) {

		Employee emp = new Employee();
		emp.setUserId(empDto.getUserId());
		emp.setTitle(empDto.getTitle());
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());
		emp.setCompany(empDto.getCompany());
		emp.setJobRole(empDto.getJobRole());
		emp.setDateOfBirth(empDto.getDateOfBirth());
		emp.setDateOfJoining(empDto.getDateOfJoining());

		City city = cityRepo.findById(empDto.getCityId())
				.orElseThrow(() -> new EntityNotFoundException("City not found with id: " + empDto.getCityId()));

		emp.setCity(city);

		State state = stateRepo.findById(empDto.getStateId())
				.orElseThrow(() -> new EntityNotFoundException("State not found with id: " + empDto.getStateId()));
		emp.setState(state);

		return emp;
	}

	// Add User
	public ApiResponse<Employee> addEmployee(EmployeeDTO empDto) {
		try {
			empDto.setDateOfJoining(LocalDate.now());
			Employee emp = mapDtoToEntity(empDto);
			Employee saveEmp = empRepo.save(emp);

			return new ApiResponse<>(saveEmp, HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	// Get User List
	public ApiResponse<List<Employee>> getAllEmployee() {
		try {
			List<Employee> emp = empRepo.findAll();
			return new ApiResponse<>(emp, HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}

	// Get Destinct Company List
	public ApiResponse<List<CompanyDto>> getAllCompany() {

		try {

			List<String> employee = empRepo.findDistinctCompany();
			List<CompanyDto> distincCompany = new ArrayList<>();

			for (String emp : employee) {
				CompanyDto companyDto = new CompanyDto(emp);

				if (!distincCompany.contains(companyDto)) {
					distincCompany.add(companyDto);
				}
			}

			return new ApiResponse<>(distincCompany, HttpStatus.OK.value());
		} catch (Exception e) {

			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}
	
	//Get Destinct Job Role
	
	public ApiResponse<List<JobRoleDto>> getAllJobRole(){
		try {
			List<String> jobRole = empRepo.findDistinctJobRole();
			List<JobRoleDto> jobRoleDto = new ArrayList<>();
			
			for(String role : jobRole) {
				JobRoleDto jobeRoles = new JobRoleDto(role);
				
				if(!jobRoleDto.contains(jobRoleDto)) {
					jobRoleDto.add(jobeRoles);
				}
			}
			return new ApiResponse<>(jobRoleDto, HttpStatus.OK.value());
			
		} catch(Exception e){
			
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	//Search Employee by state and city
	
	public ApiResponse<List<Employee>> searchEmployee(SearchParametersDto search){
		try {
			
			if(search.getState() != null && search.getCity() != null ) {
				List<Employee> list = empRepo.findByCityAndState(search.getCity(),search.getState());
				return new ApiResponse<>(list,HttpStatus.OK.value());
			}else if(search.getCity() != null) {
				List<Employee> list = empRepo.findByCity(search.getCity());
				return new ApiResponse<>(list,HttpStatus.OK.value());
			}else if(search.getState() != null) {
				List<Employee> list = empRepo.findByState(search.getState());
				return new ApiResponse<>(list,HttpStatus.OK.value());
			}else {
				List<Employee> emp = empRepo.findAll();
				return new ApiResponse<>(emp, HttpStatus.OK.value());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}
