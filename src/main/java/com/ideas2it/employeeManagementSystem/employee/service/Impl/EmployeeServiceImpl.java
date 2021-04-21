package com.ideas2it.employeeManagementSystem.employee.service.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.employee.dao.Impl.EmployeeDaoImpl;
import com.ideas2it.employeeManagementSystem.employee.model.Address;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.employee.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;
import com.ideas2it.employeeManagementSystem.project.model.Project;
import com.ideas2it.employeeManagementSystem.project.service.ProjectService;
import com.ideas2it.employeeManagementSystem.project.service.Impl.ProjectServiceImpl;

/**
 * Class to perform CRUD operation on employee
 * 
 * @author TamilarasiShanmugasundaram created 05-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeManagementLogger employeeManagementLogger = new EmployeeManagementLogger();
	EmployeeDao employeeDaoImpl = new EmployeeDaoImpl();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validateName(String name) {
		return name.matches(Constants.NAME_REGEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validatePhoneNumber(String phoneNumberString) {
		return phoneNumberString.matches(Constants.PHONENUMBER_REGEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validateEmailId(String emailId) {
		return emailId.matches(Constants.EMAIL_REGEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validateDate(String date) {
		return date.matches(Constants.DATE_REGEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createEmployee(Employee employee, List<Address> addresses) throws EmployeeManagementException {
		for (int i = 0; i < addresses.size(); i++) {
			if (Constants.ZERO == i) {
				((List<Address>) addresses).get(i).setIsPermanent(true);
			} else {
				((List<Address>) addresses).get(i).setIsPermanent(false);
			}
		}
		employee.setAddressList(addresses);
		boolean resultOfCreateEmployee = employeeDaoImpl.createEmployee(employee);
		if (resultOfCreateEmployee) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.ADD_SUCCESS);
		}
		return resultOfCreateEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteEmployee(int id) throws EmployeeManagementException {
		Employee employee = employeeDaoImpl.getEmployeeById(id);
		List<Address> addressList = employee.getAddressList();
		for (Address list : addressList) {
			list.setIsDelete(true);
		}
		employee.setIsDelete(true);
		boolean resultOfDeleteEmployee = employeeDaoImpl.updateEmployee(employee);
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		List<Project> projectList = projectServiceImpl.getProjectDetails();
		for (Project project : projectList) {
			Set<Employee> employees = project.getEmployeeList();
			if (employees.contains(employee)) {
				employees.remove(employee);
				project.setEmployeeList(employees);
				projectServiceImpl.updateProject(project);
			}
		}
		if (resultOfDeleteEmployee) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.DELETE_SUCCESS);
		}
		return resultOfDeleteEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retrieveEmployee(int employeeId) throws EmployeeManagementException {
		Employee employee = employeeDaoImpl.getDeletedEmployeeById(employeeId);
		List<Address> addressList = employee.getAddressList();
		for (Address list : addressList) {
			list.setIsDelete(false);
		}
		employee.setAddressList(addressList);
		employee.setIsDelete(false);
		boolean resultOfRetrieveEmployee = employeeDaoImpl.updateEmployee(employee);
		if (resultOfRetrieveEmployee) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.RETRIEVE_SUCCESS);
		}
		return resultOfRetrieveEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAll(Employee employee) throws EmployeeManagementException {
		Employee employeeData = employeeDaoImpl.getEmployeeById(employee.getId());
		employee.setProjectList(employeeData.getProjectList());
		boolean resultOfUpdateEmployee = employeeDaoImpl.updateEmployee(employee);
		if (resultOfUpdateEmployee) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.UPDATE_SUCCESS);
		}
		return resultOfUpdateEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> getTemporaryAddressByEmployeeId(int id) throws EmployeeManagementException {
		List<Address> addressList = employeeDaoImpl.getTemporaryAddressByEmployeeId(id);
		if (0 < addressList.size()) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.GET_TEMPORARY_ADDRESS);
		}
		return addressList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateEmployee(Employee employee) throws EmployeeManagementException {
		return employeeDaoImpl.updateEmployee(employee);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean unassignProject(int employeeId, int projectId) throws EmployeeManagementException {
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		Project project = projectServiceImpl.getProjectByProjectId(projectId);
		Employee employee = employeeDaoImpl.getEmployeeById(employeeId);
		// Employee employee = employeeList.get(0);
		HashSet<Project> projectList = (HashSet<Project>) employee.getProjectList();
		boolean flag = false;
		if (projectList.contains(project)) {
			flag = true;
			projectList.remove(project);
		}
		if (flag) {
			employee.setProjectList(projectList);
			boolean resultOfUnassignEmployee = employeeDaoImpl.updateEmployee(employee);
			if (resultOfUnassignEmployee) {
				employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
				employeeManagementLogger.logInfo(Constants.UNASSIGN_SUCCESS);
			}
			return resultOfUnassignEmployee;
		} else {
			return flag;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean assignProject(int projectId, int employeeId) throws EmployeeManagementException {
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		Project project = projectServiceImpl.getProjectByProjectId(projectId);
		Employee employee = employeeDaoImpl.getEmployeeById(employeeId);
		// Employee employee = employeeList.get(0);
		Set<Project> projectList = employee.getProjectList();
		projectList.add(project);
		employee.setProjectList(projectList);
		boolean resultOfAssignEmployee = employeeDaoImpl.updateEmployee(employee);
		if (resultOfAssignEmployee) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.ASSIGN_SUCCESS);
		}
		return resultOfAssignEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getEmployeeById(int id) throws EmployeeManagementException {
		Employee employee = employeeDaoImpl.getEmployeeById(id);
		if (null != employee) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.GET_EMPLOYEE_BY_ID_SUCCESS);
		}
		return employee;
	}

	public Employee getDeletedEmployeeById(int id) throws EmployeeManagementException {
		return employeeDaoImpl.getDeletedEmployeeById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getDeletedEmployees() throws EmployeeManagementException {
		List<Employee> employeeList = employeeDaoImpl.getDeletedEmployees();
		if (0 < employeeList.size()) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.GET_ALL_PROJECT_SUCCESS);
		}
		return employeeList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getEmployeeDetails() throws EmployeeManagementException {
		List<Employee> employeeList = employeeDaoImpl.getEmployeeDetails();
		if (0 < employeeList.size()) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.GET_ALL_EMPLOYEE_SUCCESS);
		}
		return employeeList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAssignExist(int employeeId, List<Integer> projectIdList) throws EmployeeManagementException {
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		boolean flag = false;
		for (int id : projectIdList) {
			Project project = projectServiceImpl.getProjectByProjectId(id);
			Employee employee = employeeDaoImpl.getEmployeeById(employeeId);
			Set<Project> projectList = employee.getProjectList();
			if (projectList.contains(project)) {
				flag = true;
			} else {
				assignProject(id, employeeId);
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmployeeExist(int id) throws EmployeeManagementException {
		return employeeDaoImpl.isEmployeeExist(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmailExist(String emailId) throws EmployeeManagementException {
		return employeeDaoImpl.isEmailExist(emailId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPhoneNumberExist(long phoneNumber) throws EmployeeManagementException {
		return employeeDaoImpl.isPhoneNumberExist(phoneNumber);
	}

	public List<Employee> getEmployees() throws EmployeeManagementException {
		return employeeDaoImpl.getEmployees();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> validate(String phoneNumber, String emailId) throws EmployeeManagementException {
		Map<String, String> isValid = new HashMap<String, String>();
		isValid.put(Constants.BOOLEAN, Constants.TRUE);
		if (isPhoneNumberExist(Long.parseLong(phoneNumber))) {
			isValid.put(Constants.BOOLEAN, Constants.FALSE);
			isValid.put(Constants.MESSAGE, Constants.PHONENUMBER_EXIST);
		} else if (isEmailExist(emailId)) {
			isValid.put(Constants.BOOLEAN, Constants.FALSE);
			isValid.put(Constants.MESSAGE, Constants.EMAIL_EXIST);
		} else if (false == validatePhoneNumber(phoneNumber)) {
			isValid.put(Constants.BOOLEAN, Constants.FALSE);
			isValid.put("Message", Constants.PHONENUMBER_NOT_VALID);
		}
		return isValid;
	}
}
