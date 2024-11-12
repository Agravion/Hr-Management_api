package it.riccardotomassoni.hrmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployes();
	}
	
	public Employee getEmployeeById(long id) {
		Employee employee = employeeRepository.getEmployeeById(id);
		if (employee == null) {
			throw new ApiException("Employee with the given id does not exists.");
		}
		return employee;
	}
	
	public void insertEmployee(Employee employee) {
		if (employeeRepository.getEmployeeById(employee.id()) != null) {
			throw new ApiException("Employee with the given id already exists.");
		}
		employeeRepository.insertEmployee(employee);
	}
	
	public void overwriteEmployee(Employee employee) {
		if (getEmployeeById(employee.id()) == null) {
			throw new ApiException("Employee with the given id does not exist.");
		}
		employeeRepository.overwriteEmployee(employee);
	}
	
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteEmployeeById(id);
	}
}
