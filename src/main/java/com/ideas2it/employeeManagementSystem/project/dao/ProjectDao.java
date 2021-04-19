package com.ideas2it.employeeManagementSystem.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List; 

import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.project.model.Project;

public interface ProjectDao {

    /**
     * To create the project
     * @param project
     *        provides the project object
     * return boolean
     *        return true if successfully created otherwise return false;
     */
    public boolean createProject(Project project) throws EmployeeManagementException;

    /**
     * To update details of the project
     * @param project
     *        Provides the project object you want to update
     * return boolean
     *        return true if project updated succcessfully otherwise return false
     */
    public boolean updateProject(Project project) throws EmployeeManagementException;

    /**
     * To get project by projectId
     * @param id
     *        Provides the id of the project
     * return project
     *       provides the project object
     */
    public Project getProjectByProjectId(int id) throws EmployeeManagementException;
  
    /**
     * To get deleted project by projectId
     * @param id
     *        Provides the id of the project
     * return project
     *       provides the project object
     */
    public List<Project> getDeletedProjectById(int id) throws EmployeeManagementException;
	
    /**
     * To get project details
     * return List<Project> 
     *        provides the list of Project
     */ 
    public List<Project> getDeletedProjects() throws EmployeeManagementException;

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
    * To close the session factory
    */
    public void closeSessionFactory();
}