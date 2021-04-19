package com.ideas2it.employeeManagementSystem.employee.service.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public boolean createEmployee(String name, String emailId, String dateOfBirth, String dateOfJoining,
			long phoneNumber, List<Map<String, String>> addresses) throws EmployeeManagementException {
		java.sql.Date dobDate = Date.valueOf(dateOfBirth);
		java.sql.Date dojDate = Date.valueOf(dateOfJoining);
		boolean isPermanent = true;
		Employee employee = new Employee(name, emailId, dobDate, dojDate, phoneNumber);
		List<Address> addressList = new ArrayList<Address>();
		for (int index = 0; index < addresses.size(); index++) {
			Map<String, String> addressMap = addresses.get(index);
			String doorNumber = addressMap.get(Constants.DOORNUMBER);
			String streetNumber = addressMap.get(Constants.STREETNUMBER);
			String city = addressMap.get(Constants.CITY);
			String district = addressMap.get(Constants.DISTRICT);
			String state = addressMap.get(Constants.STATE);
			String country = addressMap.get(Constants.COUNTRY);
			long pincode = Long.parseLong(addressMap.get(Constants.PINCODE));
			if (Constants.ZERO == index) {
				isPermanent = true;
			} else {
				isPermanent = false;
			}
			Address address = new Address(doorNumber, streetNumber, city, district, state, country, pincode,
					isPermanent);
			addressList.add(address);
		}
		employee.setAddressList(addressList);
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
		List<Employee> employeeList = employeeDaoImpl.getEmployeeById(id);
		Employee employee = employeeList.get(0);
		List<Address> addressList = employee.getAddressList();
		for (Address list : addressList) {
			list.setIsDelete(true);
		}
		employee.setIsDelete(true);
		boolean resultOfDeleteEmployee = employeeDaoImpl.updateEmployee(employee);
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		List<Project> projectList = projectServiceImpl.getProjectDetails();
		for(Project project : projectList) {
			List<Employee> employees = project.getEmployeeList();
			if(employees.contains(employee)) {
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
	public boolean updateAll(int id, String emailId, long phoneNumber, String name, String dateOfBirthString,
			String dateOfJoiningString, List<Map<String, String>> addressList) throws EmployeeManagementException {
		List<Employee> employeeList = employeeDaoImpl.getEmployeeById(id);
		Employee employee = employeeList.get(0);
		List<Address> addresses = employee.getAddressList();
		java.sql.Date dob = Date.valueOf(dateOfBirthString);
		java.sql.Date doj = Date.valueOf(dateOfJoiningString);
		employee.setId(id);
		employee.setName(name);
		employee.setEmailId(emailId);
		employee.setPhoneNumber(phoneNumber);
		employee.setDateOfBirth(dob);
		employee.setDateOfJoining(doj);
		int index = 0, count = 0;
		for(Address address : addresses) {
			Map<String, String> permanentAddressMap = addressList.get(index++);
			address.setStreetNumber(permanentAddressMap.get(Constants.STREETNUMBER));
			address.setCity(permanentAddressMap.get(Constants.CITY));
			address.setDistrict(permanentAddressMap.get(Constants.DISTRICT));
			address.setState(permanentAddressMap.get(Constants.STATE));
			address.setCountry(permanentAddressMap.get(Constants.COUNTRY));
			address.setPincode(Long.parseLong(permanentAddressMap.get(Constants.PINCODE)));
			addresses.set(count++,address);
		}
		employee.setAddressList(addresses);
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
	public boolean updateEmployee(Employee employee)throws EmployeeManagementException {
		return employeeDaoImpl.updateEmployee(employee);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean unassignProject(int employeeId, int projectId) throws EmployeeManagementException {
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		Project project = projectServiceImpl.getProjectByProjectId(projectId);
		List<Employee> employeeList = employeeDaoImpl.getEmployeeById(employeeId);
		Employee employee = employeeList.get(0);
		List<Project> projectList = employee.getProjectList();
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
		List<Employee> employeeList = employeeDaoImpl.getEmployeeById(employeeId);
		Employee employee = employeeList.get(0);
		List<Project> projectList = employee.getProjectList();
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
	public List<Employee> getEmployeeById(int id) throws EmployeeManagementException {
		List<Employee> employeeList = employeeDaoImpl.getEmployeeById(id);
		if (null != employeeList) {
			employeeManagementLogger.logClassname(Constants.EMPLOYEE_SERVICE_IMPL);
			employeeManagementLogger.logInfo(Constants.GET_EMPLOYEE_BY_ID_SUCCESS);
		}
		return employeeList;
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
	public boolean isAssignExist(int employeeId, int projectId) throws EmployeeManagementException {
		ProjectService projectServiceImpl = new ProjectServiceImpl();
		Project project = projectServiceImpl.getProjectByProjectId(projectId);
		List<Employee> employeeList = employeeDaoImpl.getEmployeeById(employeeId);
		Employee employee = employeeList.get(0);
		List<Project> projectList = employee.getProjectList();
		boolean flag = false;
		if (projectList.contains(project)) {
			flag = true;
		}
		return flag;
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
