package com.ideas2it.employeeManagementSystem.employee.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ideas2it.employeeManagementSystem.employee.model.Address;
import com.ideas2it.employeeManagementSystem.project.model.Project;

/**
 * To store the employee data
 * @author Tamilarasi Shanmugasundaram
 * created 23-02-2021
 */
public class Employee {
    String name;
    String emailId;
    Date dateOfBirth;
    Date dateOfJoining;
    long phoneNumber;
    int id;
    List<Address> addressList= new ArrayList<Address>();
    List<Project> projectList = new ArrayList<Project>(); 
    boolean isDelete = false;   

    public Employee() {
    }

    public Employee(String name, String emailId, Date dateOfBirth, Date dateOfJoining, long phoneNumber) {
        this.name = name;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsDelete() {
        return isDelete;
    }
	
    public List<Address> getAddressList() {
        return addressList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setName(String empName) {
        this.name = empName;
    }

    public void setEmailId(String empEmailId) {
        this.emailId = empEmailId;
    }


    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setPhoneNumber(long empPhoneNumber) {
        this.phoneNumber = empPhoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Employee employee = (Employee) object;
        return id == employee.id;
    }
	
    @Override
    public int hashCode() {
	return Objects.hash(id);
    }
	
    @Override 
    public String toString() {

        return "Employee Id = " + this.id +"\n" + "Name = " + this.name+"\n" + "Email Id = " + this.emailId +"\n" + "Date of birth = " + this.dateOfBirth                               +"\n" + "Date Of Joining = " + this.dateOfJoining +"\n"+ "phonenumber = " + this.phoneNumber; 
    }
}


