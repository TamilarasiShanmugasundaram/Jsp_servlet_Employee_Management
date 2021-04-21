package com.ideas2it.employeeManagementSystem.project.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.employee.service.Impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.project.model.Project;
import com.ideas2it.employeeManagementSystem.project.service.ProjectService;
import com.ideas2it.employeeManagementSystem.project.service.Impl.ProjectServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//import org.osgi.application.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * Class to perform CRUD operation on project
 * 
 * @author TamilarasiShanmugasundaram created 23-02-2021
 */
@Controller
public class ProjectController {
	ProjectService projectServiceImpl = new ProjectServiceImpl();
	ModelAndView modelAndview = new ModelAndView();

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(value ={"/", Constants.INDEX})
	public String getIndex() {
		return Constants.INDEX;
	}
	
	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.PROJECT_URL)
	public String requestProject() {
		return Constants.PROJECT;
	}

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@RequestMapping(value = Constants.GET_PROJECT_FOR_UPDATE_URL)
	public String getProjectForUpdate() {
		 return Constants.GET_PROJECT_FOR_UPDATE;
	}
	
	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.GETID_FOR_DELETE_PROJECT)
	public String getIdfordeleteProject() {
		return Constants.DELETE_PROJECT;
	}

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(value = Constants.GET_PROJECTID_FOR_DISPLAY)
	public String getProjectIdForDisplay() {
		return Constants.GET_PROJECTID;
	}

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.GETID_FOR_PROJECT_UPDATE_URL)
	public String getIdForProjectUpdate() {
		return Constants.GETID_FOR_PROJECT_UPDATE;
	}

	/**
	 * To map the employee jsp
	 * 
	 * return ModelAndView provides both model view name
	 */
	@GetMapping(Constants.ASSIGN_EMPLOYEE_URL)
	public ModelAndView assignEmployee() {
		try {
			EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
			modelAndview.setViewName(Constants.ASSIGN_EMPLOYEE);
			modelAndview.addObject(Constants.EMPLOYEES, employeeServiceImpl.getEmployees());
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.UNASSIGN_EMPLOYEE_GET_URL)
	public String unassignEmployee() {
		return Constants.UNASSIGN_EMPLOYEE;
	}

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.RETRIEVE_PROJECT_URL)
	public String retrieveProject() {
		return Constants.RETRIEVE_PROJECT;
	}

	/**
	 * To create the project
	 * 
	 * @param project provides the project object
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.CREATE_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView createProject(@ModelAttribute(Constants.PROJECT) Project project) {
		try {
			boolean statusOfCreateProject = projectServiceImpl.createProject(project);
			if (statusOfCreateProject) {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.ADD_SUCCESS);
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.ADD_FAILURE);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}
	
	/**
	 * To delete the project
	 * 
	 * @param id provides the project id return ModelAndView provides both model
	 *           and view
	 */
	@RequestMapping(value = Constants.DELETE_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView deleteProject(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				boolean statusOfDeleteProject = projectServiceImpl.deleteProject(id);
				if (statusOfDeleteProject) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_FAILURE);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the project
	 * 
	 * @param id provides the project id
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.GET_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				Project project = projectServiceImpl.getProjectByProjectId(id);
				modelAndview.setViewName(Constants.CREATE_PROJECT);
				modelAndview.addObject(Constants.PROJECT, project);
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}
	
	/**
	 * To update the project
	 * 
	 * @param project provides the project object
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.UPDATE_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute(Constants.PROJECT) Project project) {
		try {
			if (projectServiceImpl.isProjectExist(project.getProjectId())) {
				boolean statusOfUpdateProject = projectServiceImpl.updateAll(project);
				if (statusOfUpdateProject) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_FAILURE);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get project by projectId
	 * 
	 * @param id provides the project id
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.DISPLAY_SINGLE_PROJECT_POST_URL, method = RequestMethod.GET)
	public ModelAndView getProjectByProjectId(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				Project project = projectServiceImpl.getProjectByProjectId(id);
				modelAndview.setViewName(Constants.DISPLAY_SINGLE_PROJECT);
				modelAndview.addObject(Constants.PROJECT, project);
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To assign project for employee
	 * 
	 * @param projectId provides the project id
	 * @param employeeIdList provides the list of employee id
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.ASSIGN_EMPLOYEE_POST_URL, method = RequestMethod.POST)
	public ModelAndView assignEmployee(@RequestParam("employeeIds") List<Integer> employeeIdList,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (projectServiceImpl.isProjectExist(projectId)) {
				projectServiceImpl.isAssignExist(projectId, employeeIdList);
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.ASSIGN_SUCCESS);	
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To unassign project for employee
	 * 
	 * @param projectId provides the project id
	 * @param employeeId provides the employee id
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.UNASSIGN_EMPLOYEE_URL, method = RequestMethod.POST)
	public ModelAndView unassignEmployee(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (projectServiceImpl.isProjectExist(projectId)) {
				EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
				if (employeeServiceImpl.isEmployeeExist(employeeId)) {
					boolean statusOfUnassignProject = projectServiceImpl.unassignEmployee(employeeId, projectId);
					if (statusOfUnassignProject) {
						modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
						modelAndview.addObject(Constants.MESSAGE, Constants.UNASSIGN_SUCCESS);
					} else {
						modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
						modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_ASSIGN);
					}
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To retrieve project
	 * 
	 * @param projectId provides the project id
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.RETRIEVE_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView retrieveProject(@RequestParam(Constants.ID) int id) {
		try {
			if (false == projectServiceImpl.isProjectExist(id)) {
				boolean statusOfRetrieveProject = projectServiceImpl.retrieveProject(id);
				if (statusOfRetrieveProject) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_FAILURE);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_DELETED);
			}

		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get project details 
	 * 
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.RETRIEVE_PROJECT_GET_URL, method = RequestMethod.GET)
	public ModelAndView getDeletedProjects() {
		try {
			List<Project> projectList = projectServiceImpl.getDeletedProjects();
			modelAndview.setViewName(Constants.DISPLAY_DELETED_PROJECTS);
			modelAndview.addObject(Constants.DELETED_PROJECTS, projectList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get deleted project 
	 * 
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.DISPLAY_ALL_PROJECT_URL, method = RequestMethod.GET)
	public ModelAndView getProjectDetails() {
		try {
			List<Project> projectList = projectServiceImpl.getProjectDetails();
			modelAndview.setViewName("displayProjects");
			modelAndview.addObject(Constants.PROJECTS, projectList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get project object
	 * 
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.GET_PROJECT_URL)
	public ModelAndView getProject() {
		ModelAndView model = new ModelAndView(Constants.CREATE_PROJECT);
		model.addObject(Constants.PROJECT, new Project());
		return model;
	}
	
	/**
	 * To close the session factory
	 */
	public void closeSessionFactory() {
		projectServiceImpl.closeSessionFactory();
	}
}