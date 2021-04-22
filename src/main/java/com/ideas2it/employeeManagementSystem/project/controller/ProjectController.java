package com.ideas2it.employeeManagementSystem.project.controller;

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
	 * To map the index jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(value = { "/", Constants.INDEX })
	public String getIndex() {
		return Constants.INDEX;
	}

	/**
	 * To map the project jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.PROJECT_URL)
	public String requestProject() {
		return Constants.PROJECT;
	}

	/**
	 * To map to get the id for display single project jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(value = Constants.GET_PROJECTID_FOR_DISPLAY)
	public String getProjectIdForDisplay() {
		return Constants.GET_PROJECTID;
	}	
	
	/**
	 * To map the assign employee jsp
	 * 
	 * return ModelAndView provides both model view name
	 */
	@RequestMapping(value = Constants.ASSIGN_EMPLOYEE_URL, method = RequestMethod.POST)
	public ModelAndView assignEmployee(@RequestParam(Constants.ID) int projectId) {
		try {
			if (projectServiceImpl.isProjectExist(projectId)) {
				EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
				modelAndview.setViewName(Constants.ASSIGN_EMPLOYEE);
				modelAndview.addObject(Constants.EMPLOYEES, employeeServiceImpl.getEmployees());
				modelAndview.addObject(Constants.PROJECT, projectServiceImpl.getProjectByProjectId(projectId));
			} else {
				displayMessages(Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To create the project
	 * 
	 * @param project provides the project object return ModelAndView provides both
	 *                model and view
	 */
	@RequestMapping(value = Constants.CREATE_PROJECT_POST_URL, method = RequestMethod.POST)
	public String createProject(@ModelAttribute(Constants.PROJECT) Project project) {
		try {
			if (false == projectServiceImpl.createProject(project)) {
				displayMessages(Constants.DELETE_SUCCESS);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return "redirect:/DisplayAllProject";
	}

	public String redirect() {
		return "redirect:/DisplayAllProject";
	}

	public ModelAndView displayMessages(String message) {
		modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
		modelAndview.addObject(Constants.MESSAGE, message);
		return modelAndview;
	}

	/**
	 * To delete the project
	 * 
	 * @param id provides the project id return ModelAndView provides both model and
	 *           view
	 */
	@RequestMapping(value = Constants.DELETE_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView deleteProject(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				if (projectServiceImpl.deleteProject(id)) {
					List<Project> projectList = projectServiceImpl.getProjectDetails();
					modelAndview.setViewName("displayProjects");
					modelAndview.addObject(Constants.PROJECTS, projectList);
				} else {
					displayMessages(Constants.DELETE_FAILURE);
				}
			} else {
				displayMessages(Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the project
	 * 
	 * @param id provides the project id return ModelAndView provides both model and
	 *           view
	 */
	@RequestMapping(value = Constants.GET_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				Project project = projectServiceImpl.getProjectByProjectId(id);
				modelAndview.setViewName(Constants.CREATE_PROJECT);
				modelAndview.addObject(Constants.PROJECT, project);
			} else {
				displayMessages(Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the project
	 * 
	 * @param project provides the project object return ModelAndView provides both
	 *                model and view
	 */
	@RequestMapping(value = Constants.UPDATE_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute(Constants.PROJECT) Project project) {
		try {
			if (projectServiceImpl.isProjectExist(project.getProjectId())) {
				if (projectServiceImpl.updateAll(project)) {
					List<Project> projectList = projectServiceImpl.getProjectDetails();
					modelAndview.setViewName("displayProjects");
					modelAndview.addObject(Constants.PROJECTS, projectList);
				} else {
					displayMessages(Constants.UPDATE_FAILURE);
				}
			} else {
				displayMessages(Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get project by projectId
	 * 
	 * @param id provides the project id return ModelAndView provides both model and
	 *           view
	 */
	@RequestMapping(value = Constants.DISPLAY_SINGLE_PROJECT_POST_URL, method = RequestMethod.GET)
	public ModelAndView getProjectByProjectId(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				Project project = projectServiceImpl.getProjectByProjectId(id);
				modelAndview.setViewName(Constants.DISPLAY_SINGLE_PROJECT);
				modelAndview.addObject(Constants.PROJECT, project);
			} else {
				displayMessages(Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To assign project for employee
	 * 
	 * @param projectId      provides the project id
	 * @param employeeIdList provides the list of employee id return ModelAndView
	 *                       provides both model and view
	 */
	@RequestMapping(value = Constants.ASSIGN_EMPLOYEE_POST_URL, method = RequestMethod.POST)
	public ModelAndView assignEmployee(@RequestParam("employeeIds") List<Integer> employeeIdList,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (projectServiceImpl.isProjectExist(projectId)) {
				projectServiceImpl.isEmployeeAssignExist(projectId, employeeIdList);
				Project project = projectServiceImpl.getProjectByProjectId(projectId);
				modelAndview.setViewName(Constants.DISPLAY_SINGLE_PROJECT);
				modelAndview.addObject(Constants.PROJECT, project);
			} else {
				displayMessages(Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To retrieve project
	 * 
	 * @param projectId provides the project id return ModelAndView provides both
	 *                  model and view
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.POST)
	public ModelAndView retrieveProject(@RequestParam(Constants.ID) int id) {
		try {
			if (false == projectServiceImpl.isProjectExist(id)) {
				if (projectServiceImpl.retrieveProject(id)) {
					List<Project> projectList = projectServiceImpl.getProjectDetails();
					modelAndview.setViewName("displayProjects");
					modelAndview.addObject(Constants.PROJECTS, projectList);
				} else {
					displayMessages(Constants.RETRIEVE_FAILURE);
				}
			} else {
				displayMessages(Constants.PROJECT_NOT_DELETED);
			}

		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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