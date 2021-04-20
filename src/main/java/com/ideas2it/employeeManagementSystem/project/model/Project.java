package com.ideas2it.employeeManagementSystem.project.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;

/**
 * To store the address details of project
 * @author Tamilarasi Shanmugasundaram
 * created 04-03-2021
 */
@XmlRootElement
public class Project {
    String title;
    Date startDate;
    Date estimatedEndDate;  
    String status;
    String client;
    long budget;
    int projectId;
    List<Employee> employeeList = new ArrayList<Employee>(); 
    boolean isDelete = false;

    public Project() {
    }

    public Project(String title, Date startDate, Date estimatedEndDate, String status, String client, long budget) {
        this.title = title;
        this.startDate = startDate;
        this.estimatedEndDate = estimatedEndDate;
        this.status = status;
        this.client = client;
        this.budget = budget;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getClient() {
        return client;
    }

    public long getBudget() {
        return budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public int getProjectId() {
        return projectId;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEstimatedEndDate(Date estimateEndDate) {
        this.estimatedEndDate = estimateEndDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setProjectId(int id) {
        this.projectId = id;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
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
        Project project = (Project) object;
        return projectId == project.projectId;
    }
	
    @Override
    public int hashCode() {
	return Objects.hash(projectId);
    }

    @Override 
    public String toString() {     
        return "Project Id = " + this.projectId +"\n" + "Title = " + this.title+"\n" + "Start date = " + this.startDate+"\n"  + "Estimate End Date = " + this.estimatedEndDate+"\n"  + "Status = " + this.status+"\n"+ "Client = " + this.client+"\n" + "Budget = " + this.budget;  
    }
}


