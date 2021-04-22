package com.ideas2it.employeeManagementSystem.employee.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.model.Address;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.project.model.Project;

public interface EmployeeService {

    /**
     * To validate the name
     * @param name
     *        provides the name of the employee 
     * return boolean
     *        return true if validation success otherwise return false
     */
    public boolean validateName(String name);

    /**
     * To create the employee
     * @param phoneNumber
     *        provides phone number of the employee
     * return boolean
     *        return true if validation success otherwise return false
     */
    public boolean validatePhoneNumber(String phoneNumber);

    /**
     * To validate email id
     * @param emailId
     *        provides emailID of the employee
     * return boolean
     *        return true if validation success otherwise return false
     */
    public boolean validateEmailId(String emailId);

    /**
     * To validate date 
     * @param date
     *        provides date 
     * return boolean
     *        return true if validation success otherwise return false
     */
    public boolean validateDate(String date);

    /**
     * To create the employee
     * @param name
     *        provides the name of the employee
     * @param emailId
     *        provides the emailId of the employee 
     * @param dateOfBirth
     *        provides the dateOfBirth of the employee
     * @param dateOfJoining
     *        provides the dateOfJoining of the employee
     * @param phoneNumber
     *        provides the phoneNumber of the employee
     * @param addressList
     *        provides the list of address of the employee
     * return boolean
     *        return true if employee created succcessfully otherwise return false
     */
    public boolean createEmployee(Employee employee, List<Address> addresses) throws EmployeeManagementException;

    /**
     * To delete the employee
     * @param id
     *        Provides the deleteId of the employee you want to delete
     * return boolean
     *      return true if deleted successfully otherwise return false
     */
    public boolean deleteEmployee(int id) throws EmployeeManagementException;
	
    /**
     * To get deleted employee details
     * return List<Employee>  
     *        provides the List of employee
     */ 
    public List<Employee> getDeletedEmployees() throws EmployeeManagementException;
	
    /**
     * To retrieve project 
     * @param projectId
     *        Provides the project id 
     * return boolean 
     *       return true if employee retrieved successfully otherwise return false
     */
    public boolean retrieveEmployee(int employeeId) throws EmployeeManagementException;

    /**
     * To get the temporary address by using employee id
     * @param id
     *        Provides the employee id 
     * return List<Address> 
     *       provides the address list
     */
	public List<Address> getTemporaryAddressByEmployeeId(int id) throws EmployeeManagementException;
    
    /**
     * To check the project is assiged to employee or not
     * @param employeeId
     *        Provides the array of employee
     *             * @param projectId
     *        Provides the project id 
     * return boolean 
     *       return true if project is assiged to employee otherwise return false
     */
    public boolean isProjectAssignExist(int employeeId, List<Integer> projectIdList) throws EmployeeManagementException;
    
    /**
     * To assign employee for project
     * @param projectId
     *        Provides the project id 
     * @param employeeId
     *        Provides the array of employee
     * return boolean 
     *       return true if project assigned successfully otherwise return false
     */
    public boolean assignProject(int projectId, int employeeId) throws EmployeeManagementException;
	
    /**
     * To unassign employee for project
     * @param employeeId
     *        Provides the array of employee
     * @param projectId
     *        Provides the project id 
     * return boolean 
     *       return true if project unassigned successfully otherwise return false
     */	
	public boolean unassignProject(int employeeId, int projectId) throws EmployeeManagementException;

    /**
     * To update all the details of employee
     * @param id
     *        Provides the id of the employee
     * @param emailId
     *        Provides the emailId of the employee
     * @param phone number
     *        Provides the phone number of the employee
     * @param name
     *        Provides the name of the employee
     * return boolean
     *       return true if all details are updated otherwise return false
     */
	public boolean updateAll( Employee employee) throws EmployeeManagementException;
	
    /**
     * To get employee by employeeId
     * @param id
     *        Provides the id of the employee
     * return Employee
     *       provides the employee object
     */
    public Employee getEmployeeById(int id) throws EmployeeManagementException;

    /**
     * To get employee by employeeId
     * @param id
     *        Provides the id of the employee
     * return Employee
     *       provides the employee object
     */
    public Employee getDeletedEmployeeById(int id) throws EmployeeManagementException;
    
    /**
     * To get employee details
     * return List<Employee>
     *        provides the list of employee
     */  
    public List<Employee> getEmployeeDetails() throws EmployeeManagementException;
	
    /**
     * To identify the employee is exixts or not
     * return boolean
     *        return true if employee exist otherwise return false
     */ 
    public boolean isEmployeeExist(int id) throws EmployeeManagementException;

    /**
     * To identify the email id exists or not
     * return Exception
     *        provides the Exception
     */ 
    public boolean isEmailExist(String emailId) throws EmployeeManagementException;
    
    /**
     * To update details of the employee
     * @param employee
     *        Provides the employee object you 
     * return boolean
     *        return true if employee updated successfully otherwise return false
     */
    public boolean updateEmployee(Employee employee)throws EmployeeManagementException;

    /**
     * To identify the phone number id exists or not
     * return Exception
     *        provides the Exception
     */ 
    public boolean isPhoneNumberExist(long phoneNumber) throws EmployeeManagementException;
    
    /**
     * To get all details
     * return List<Employee>
     *        provides the list of employee
     */  
    public List<Employee> getEmployees() throws EmployeeManagementException;

    /**
     * To validate the phone number and email id
     * @param phoneNumber
     *        provides the phone number 
     * @param emailId
     *        provides the emailid 
     */ 
    public Map<String,String> validate(String phoneNumber, String emailId)  throws EmployeeManagementException;
}
