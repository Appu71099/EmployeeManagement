package com.demo.employee.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Employee {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private Long userId;
	    private String title;
	    private String firstName;
	    private String lastName;
	    
	    @ManyToOne
	    @JoinColumn(name = "city_id")
	    private City city;
	    
	    @ManyToOne
	    @JoinColumn(name = "state_id")
	    private State state;
	    private String company;
	    private String jobRole;
	    private LocalDate dateOfBirth;
	    private LocalDate dateOfJoining;
}
