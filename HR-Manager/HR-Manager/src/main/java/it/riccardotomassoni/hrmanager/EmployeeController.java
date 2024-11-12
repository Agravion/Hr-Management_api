package it.riccardotomassoni.hrmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	private static final String SUCCESS_STRING = "Operation completed successfully.";
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
    @GetMapping()
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok(employeeService.getEmployeeById(id));
		} catch (ApiException e) {
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@PostMapping()
	public ResponseEntity<String> insertEmployee(@RequestBody Employee employee) {
		try {
			employeeService.insertEmployee(employee);
			return ResponseEntity.ok(SUCCESS_STRING);
		} catch (ApiException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping()
	public ResponseEntity<String> overwriteEmployee(@RequestBody Employee employee) {
		try {
			employeeService.overwriteEmployee(employee);
			return ResponseEntity.ok(SUCCESS_STRING);
		} catch (ApiException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable long id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok(SUCCESS_STRING);
	}
}
