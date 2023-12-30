package com.demo.employee.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO {	
 	private Long userId;
    private String title;
    private String firstName;
    private String lastName;
    private Long cityId;
    private Long stateId;
    private String company;
    private String jobRole;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
}
