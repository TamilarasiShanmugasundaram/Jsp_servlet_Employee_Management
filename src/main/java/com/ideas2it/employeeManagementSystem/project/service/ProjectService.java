package com.ideas2it.employeeManagementSystem.project.service;

import java.sql.Date;
import java.util.List; 

import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.project.model.Project;

public interface ProjectService {

    /**
     * To create the project
     * @param title
     *        provides the title of project
     * @param startDate
     *        provides the startDate of project
     * @param estimateEndDate
     *        provides the estimateEndDate of project
     * @param status
     *        provides the status of project
     * @param client
     *        provides the client of project
     * @param budget
     *        provides the budget of project
     * return boolean
     *        return true if successfully created otherwise return false
     */
    public boolean createProject(Project project) throws EmployeeManagementException;

    /**
     * To delete the employee
     * @param id
     *        Provides the deleteId of the project you want to delete
     * return boolean
     *        return true if project deleted succcessfully otherwise return false
     */
    public boolean deleteProject(int id) throws EmployeeManagementException;
    
    /**
     * To assign employee for project
     * @param id
     *        Provides the project id 
     * @param title
     *        Provides the project title
     * @param status
     *        provides project status
     * @param client
     *        provides project client
     * @param estimatedEndDate
     *        providses the end date
     * @param budget
     *        provides the project budget
     * return boolean 
     *        return true if employee assigned successfully otherwise return false
     */
    public boolean updateAll(Project project) throws EmployeeManagementException;
    
    /**
     * To assign employee for project
     * @param projectId
     *        Provides the project id 
     * @param employeeId
     *        Provides the id of employee
     * return boolean 
     *        return true if employee assigned successfully otherwise return false
     */
    public boolean assignEmployee(int employeeId, int projectId) throws EmployeeManagementException;

	/**
     * To unassign project for employee
     * @param employeeId
     *        Provides the employee id 
     * @param projectIdArray
     *        Provides the array of project
     * return boolean 
     *        return true if employee unassigned succcessfully otherwise return false
     */
    public boolean unassignEmployee(int employeeId, int projectId) throws EmployeeManagementException;
	
    /**
     * To get project details
     * return List<Project> 
     *        provides the list of Project
     */ 
    public List<Project> getDeletedProjects() throws EmployeeManagementException;
	
    /**
     * To retrieve project 
     * @param projectId
     *        Provides the project id 
     * return boolean 
     *        return true if project retrieved succcessfully otherwise return false
     */
    public boolean retrieveProject(int projectId) throws EmployeeManagementException;
	
    /**
     * To get project by projectId
     * @param id
     *        Provides the id of the project
     * return project
     *       provides the project object 
     */
    public Project getProjectByProjectId(int id) throws EmployeeManagementException;

    /**
     * To get project details
     * return List<Project>
     *        provides the list of Project
     */ 
    public List<Project> getProjectDetails() throws EmployeeManagementException;

    /**
     * To identify the project is exixts or not
     * return boolean
     *        return true if project exist otherwise false
     */ 
    public boolean isProjectExist(int id) throws EmployeeManagementException;
    
	/**
     * To check project and employee is already assigned or not
     * @param employeeId
     *        Provides the employee id 
     * @param projectIdArray
     *        Provides the array of project
     * return boolean 
     *        return true if already assigned  otherwise return false
     */
    public boolean isEmployeeAssignExist(int projectId, List<Integer>employeeIdList) throws EmployeeManagementException;
    
	/**
     * To update the projet
     * @param project
     *        Provides the project object
     * return boolean 
     *        return true if updated  otherwise return false
     */ 
    public boolean updateProject(Project project) throws EmployeeManagementException;

    /**
     * To validate the date
     * return boolean
     *        return true if date is valid otherwise return false
     */ 
    public boolean validateDate(String estimateEndDate);

    /**
     * To validate the time period
     * return String 
     *        return String 
     */
    public String validateTimePeriodForProject(String startDate, String estimateEndDate);
    
    public List<Project> getProjects() throws EmployeeManagementException;

   /**
    * To close the session factory
    */
    public void closeSessionFactory();
}

