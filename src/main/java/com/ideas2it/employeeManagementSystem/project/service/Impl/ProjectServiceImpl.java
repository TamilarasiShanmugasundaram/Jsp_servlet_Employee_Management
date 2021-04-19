package com.ideas2it.employeeManagementSystem.project.service.Impl;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.employee.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.employee.service.Impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;
import com.ideas2it.employeeManagementSystem.project.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.project.dao.Impl.ProjectDaoImpl;
import com.ideas2it.employeeManagementSystem.project.model.Project;
import com.ideas2it.employeeManagementSystem.project.service.ProjectService;

/**
 * To perform CRUD operation on Project
 * 
 * @author Tamilarasi Shanmugasundaram created 05-03-2021
 */
public class ProjectServiceImpl implements ProjectService {
	EmployeeManagementLogger employeeManagementLogger = new EmployeeManagementLogger();
	ProjectDao projectDaoImpl = new ProjectDaoImpl();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createProject(String title, Date startDateString, Date estimateEndDateString, String status,
			String client, long budget) throws EmployeeManagementException {
	//	Date startDate = Date.valueOf(startDateString);
		//Date estimateEndDate = Date.valueOf(estimateEndDateString);
		Project project = new Project(title, startDateString, estimateEndDateString, status, client, budget);
		project.getIsDelete();
		boolean resultOfCreateProject = projectDaoImpl.createProject(project);
		if (resultOfCreateProject) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.ADD_SUCCESS);
		}
		return resultOfCreateProject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteProject(int id) throws EmployeeManagementException {
		Project project  = projectDaoImpl.getProjectByProjectId(id);
		project.setIsDelete(true);
		boolean resultOfDeleteProject = projectDaoImpl.updateProject(project);
		EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
		List<Employee> employeeList = employeeServiceImpl.getEmployeeDetails();
		for(Employee employee : employeeList) {
			List<Project> projects = employee.getProjectList();
			if(projects.contains(project)) {
				projects.remove(project);
				employee.setProjectList(projects);
				employeeServiceImpl.updateEmployee(employee);
			}
		}
		
		if (resultOfDeleteProject) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.DELETE_SUCCESS);
		}
		return resultOfDeleteProject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateAll(int id, String title, String status, String client, String estimateEndDateString, String startDateString,
			long budget) throws EmployeeManagementException {
		Date estimateEndDate = Date.valueOf(estimateEndDateString);
		Date startDate = Date.valueOf(startDateString);
		Project project = projectDaoImpl.getProjectByProjectId(id);
		project.setEstimatedEndDate(estimateEndDate);
		project.setClient(client);
		project.setStartDate(startDate);
		project.setBudget(budget);
		project.setTitle(title);
		project.setStatus(status);
		project.setProjectId(id);
		boolean resultOfUpdateProject = projectDaoImpl.updateProject(project);
		if (resultOfUpdateProject) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.UPDATE_SUCCESS);
		}
		return resultOfUpdateProject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateProject(Project project) throws EmployeeManagementException {
		return projectDaoImpl.updateProject(project);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Project getProjectByProjectId(int id) throws EmployeeManagementException {
		Project project = projectDaoImpl.getProjectByProjectId(id);
		if (null != project) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.GET_PROJECT_BY_ID_SUCCESS);
		}
		return project;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean assignEmployee(int employeeId, int projectId) throws EmployeeManagementException {
		EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
		List<Employee> singleEmployee = employeeServiceImpl.getEmployeeById(employeeId);
		Employee employee = singleEmployee.get(0);
		Project project = projectDaoImpl.getProjectByProjectId(projectId);
		List<Employee> employeeList = project.getEmployeeList();
		employeeList.add(employee);
		project.setEmployeeList(employeeList);
		boolean resultOfAssignProject = projectDaoImpl.updateProject(project);
		if (resultOfAssignProject) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.ASSIGN_SUCCESS);
		}
		return resultOfAssignProject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean unassignEmployee(int employeeId, int projectId) throws EmployeeManagementException {
		EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
		List<Employee> singleEmployee = employeeServiceImpl.getEmployeeById(employeeId);
		Employee employee = singleEmployee.get(0);
		Project project = projectDaoImpl.getProjectByProjectId(projectId);
		List<Employee> employeeList = project.getEmployeeList();
		boolean flag = false;
		if (employeeList.contains(employee)) {
			flag = true;
			employeeList.remove(employee);
		}
		if (flag) {
			project.setEmployeeList(employeeList);
			boolean resultOfUnassignProject = projectDaoImpl.updateProject(project);
			if (resultOfUnassignProject) {
				employeeManagementLogger.logClassname("projectServiceImpl");
				employeeManagementLogger.logInfo(Constants.UNASSIGN_SUCCESS);
			}
			return resultOfUnassignProject;
		} else {
			return flag;
		}
	}

	public boolean isAssignExist(int employeeId, int projectId) throws EmployeeManagementException {
		EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
		List<Employee> singleEmployee = employeeServiceImpl.getEmployeeById(employeeId);
		Employee employee = singleEmployee.get(0);
		Project project = projectDaoImpl.getProjectByProjectId(projectId);
		List<Employee> employeeList = project.getEmployeeList();
		boolean flag = false;
		if (employeeList.contains(employee)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getDeletedProjects() throws EmployeeManagementException {
		List<Project> projectList = projectDaoImpl.getDeletedProjects();
		if (0 < projectList.size()) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.GET_DELETED_PROJECT_SUCCESS);
		}
		return projectList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retrieveProject(int projectId) throws EmployeeManagementException {
		List<Project> projectList = projectDaoImpl.getDeletedProjectById(projectId);
		Project project = projectList.get(0);
		project.setIsDelete(false);
		boolean resultOfRetrirveProject = projectDaoImpl.updateProject(project);
		if (resultOfRetrirveProject) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.RETRIEVE_SUCCESS);
		}
		return resultOfRetrirveProject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getProjectDetails() throws EmployeeManagementException {
		List<Project> projectList = projectDaoImpl.getProjectDetails();
		if (0 < projectList.size()) {
			employeeManagementLogger.logClassname("projectServiceImpl");
			employeeManagementLogger.logInfo(Constants.GET_ALL_PROJECT_SUCCESS);
		}
		return projectList;
	}

	/**
	 * To identify the project is exixts or not return boolean status of the
	 * operation which check the project exists or not
	 */
	public boolean isProjectExist(int id) throws EmployeeManagementException {
		return projectDaoImpl.isProjectExist(id);
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
	public String validateTimePeriodForProject(String startDateString, String estimateEndDateString) {
		String error = null;
		try {
			Date startDate = Date.valueOf(startDateString);
			Date estimateEndDate = Date.valueOf(estimateEndDateString);
			int timePeriod = startDate.compareTo(estimateEndDate);
			if (Constants.MINUS_ONE != timePeriod) {
				throw new EmployeeManagementException(Constants.INVALID_TIME_INTERVAL);
			} else {
				return error;
			}
		} catch (Exception exception) {
			return exception.getMessage();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeSessionFactory() {
		projectDaoImpl.closeSessionFactory();
	}
}