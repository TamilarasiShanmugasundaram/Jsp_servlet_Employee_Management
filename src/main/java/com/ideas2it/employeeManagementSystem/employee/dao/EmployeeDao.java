package com.ideas2it.employeeManagementSystem.employee.dao;

import java.util.List;

import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.model.Address;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.project.model.Project;

public interface EmployeeDao {

    /**
     * Method to create the employee
     * @param employee
     *        Provides the employee object
     * return boolean
     *        return true if employee created succcessfully otherwise return false
     */
    public boolean createEmployee(Employee employee) throws EmployeeManagementException;

    /**
     * To update details of the employee
     * @param employee
     *        Provides the employee object you 
     * return boolean
     *        return true if employee updated successfully otherwise return false
     */
    public boolean updateEmployee(Employee employee) throws EmployeeManagementException;
	
    /**
     * To get the address by using employee id
     * @param id
     *        Provides the employee id 
     * return List<Address> 
     *       provides the permanent address list
     */
    public List<Address> getPermanentAddressByEmployeeId(int id) throws EmployeeManagementException;

    /**
     * To get the temporary address by using employee id
     * @param id
     *        Provides the employee id 
     * return List<Address> 
     *       provides the temporary address list
     */
    public List<Address> getTemporaryAddressByEmployeeId(int id) throws EmployeeManagementException;
	
    /**
     * To get deleted employee details
     * return List<Employee>  
     *        provides the List of deleted employees
     */ 
    public List<Employee> getDeletedEmployees() throws EmployeeManagementException;

    /**
     * To get employee by employeeId
     * @param id
     *        Provides the id of the employee
     * return Employee 
     *       provides the employee object
     */
    public List<Employee> getEmployeeById(int id) throws EmployeeManagementException;
    
    /**
     * To get deleted employee by employeeId
     * @param id
     *        Provides the id of the employee
     * return Employee 
     *       provides the employee object
     */
    public Employee getDeletedEmployeeById(int id) throws EmployeeManagementException;

    /**
     * To get the employee details
     * return List<Employee>  of employee
     *       provides the list of employees
     */ 
    public List<Employee> getEmployeeDetails() throws EmployeeManagementException;
	
    /**
     * To identify the employee is exists or not
     * return boolean
     *        return true if employee already exist otherwise false
     */ 
     public boolean isEmployeeExist(int id) throws EmployeeManagementException;

    /**
     * To identify the email id exists or not
     * return boolean
     *        return true if employee email id already exist otherwise false
     */ 
    public boolean isEmailExist(String emailId) throws EmployeeManagementException;

    /**
     * To identify the phone number id exists or not
     * return boolean
     *        return true if employee phone number already exist otherwise false
     */ 
    public boolean isPhoneNumberExist(long phoneNumber) throws EmployeeManagementException;
}
