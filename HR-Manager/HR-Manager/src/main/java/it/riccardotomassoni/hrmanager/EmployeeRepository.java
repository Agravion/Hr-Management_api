package it.riccardotomassoni.hrmanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class EmployeeRepository {
	
	private final List<Employee> employees = new ArrayList<Employee>();
	
	@PostConstruct
	private void init() {
		employees.add(new Employee(1, "Riccardo", "Tomassoni", "CEO", 2.0));
		employees.add(new Employee(2, "Giuseppe", "Tomassoni", "Bidello", 2000.0));
	}
	
	public List<Employee> getAllEmployes() {
		return employees;
	}
	
	public Employee getEmployeeById(long id) {
		for (Employee employee : employees) {
			if (employee.id() == id) {
				return employee;
			}
		}
		return null;
	}
	
	public void insertEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void overwriteEmployee(Employee employee) {
	    for (int i = 0; i < employees.size(); i++) {
	        if (employees.get(i).id() == employee.id()) {
	        	employees.set(i, employee);
	            break;
	        }
	    }
	}

	
	public void deleteEmployeeById(long id) {
		employees.removeIf(employee -> employee.id() == id);
	}
}
