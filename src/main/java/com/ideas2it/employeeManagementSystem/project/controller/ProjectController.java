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
	 * To create the project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/createProject", method = RequestMethod.POST)
	public ModelAndView createProject(@ModelAttribute("project") Project project) {
		try {
			boolean statusOfCreateProject = projectServiceImpl.createProject(project);
			if (statusOfCreateProject) {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.ADD_SUCCESS);
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.ADD_FAILURE);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	@GetMapping("/index")
	public ModelAndView viewIndex() {
		modelAndview.setViewName("index");
		return modelAndview;
	}

	@GetMapping("/")
	public ModelAndView getIndex() {
		modelAndview.setViewName("index");
		return modelAndview;
	}
	
	@GetMapping("/Project")
	public ModelAndView requestProject() {
		modelAndview.setViewName("project");
		return modelAndview;
	}

	@RequestMapping(value = "/getProjectForUpdate")
	public ModelAndView getProjectForUpdate() {
		 modelAndview.setViewName("getProjectForUpdate");
		 return modelAndview;
	}
	
	/**
	 * To delete the project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@GetMapping("/getIdfordeleteProject")
	public ModelAndView getIdfordeleteProject() {
		modelAndview.setViewName("deleteProject");
		return modelAndview;
	}

	@GetMapping(value = "/getProjectIdForDisplay")
	public ModelAndView getProjectIdForDisplay() {
		modelAndview.setViewName("getProjectId");
		return modelAndview;
	}

	@GetMapping("/getIdForProjectUpdate")
	public ModelAndView getIdForProjectUpdate() {
		modelAndview.setViewName("getIdForProjectUpdate");
		return modelAndview;
	}

	@GetMapping("/assignEmployee")
	public ModelAndView assignEmployee() {
		modelAndview.setViewName("assignEmployee");
		return modelAndview;
	}

	@GetMapping("/unassignEmployee")
	public ModelAndView unassignEmployee() {
		modelAndview.setViewName("unassignEmployee");
		return modelAndview;
	}

	@GetMapping("/retrieveProject")
	public ModelAndView retrieveProject() {
		modelAndview.setViewName("retrieveProject");
		return modelAndview;
	}

	@RequestMapping(value = "/DeleteProject", method = RequestMethod.POST)
	public ModelAndView deleteProject(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				boolean statusOfDeleteProject = projectServiceImpl.deleteProject(id);
				if (statusOfDeleteProject) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_FAILURE);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/UpdateProject", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				Project project = projectServiceImpl.getProjectByProjectId(id);
				modelAndview.setViewName("createProject");
				modelAndview.addObject("project", project);
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}
	
	/**
	 * To update the status of the project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute("project") Project project) {
		try {
			if (projectServiceImpl.isProjectExist(project.getProjectId())) {
				boolean statusOfUpdateProject = projectServiceImpl.updateAll(project);
				if (statusOfUpdateProject) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_FAILURE);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get project by projectId
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/displaySingleProject", method = RequestMethod.GET)
	public ModelAndView getProjectByProjectId(@RequestParam(Constants.ID) int id) {
		try {
			if (projectServiceImpl.isProjectExist(id)) {
				Project project = projectServiceImpl.getProjectByProjectId(id);
				modelAndview.setViewName("displaySingleProject");
				modelAndview.addObject(Constants.PROJECT, project);
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To assign project for employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/AssignEmployee", method = RequestMethod.POST)
	public ModelAndView assignEmployee(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			System.out.println(projectServiceImpl.isProjectExist(projectId));
			if (projectServiceImpl.isProjectExist(projectId)) {
				EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
				if (employeeServiceImpl.isEmployeeExist(employeeId)) {
					if (projectServiceImpl.isAssignExist(employeeId, projectId)) {
						modelAndview.setViewName("displayMessages");
						modelAndview.addObject(Constants.MESSAGE, Constants.ALREADY_ASSIGN);
					} else {
						if (projectServiceImpl.assignEmployee(employeeId, projectId)) {
							modelAndview.setViewName("displayMessages");
							modelAndview.addObject(Constants.MESSAGE, Constants.ASSIGN_SUCCESS);
						} else {
							modelAndview.setViewName("displayMessages");
							modelAndview.addObject(Constants.MESSAGE, Constants.ASSIGN_FAILURE);
						}
					}
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		// RequestDispatcher requetsDispatcher =
		// request.getRequestDispatcher("/displayMessages.jsp");
		// requetsDispatcher.forward(request, response);
		return modelAndview;
	}

	/**
	 * To unassign project for employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/UnassignEmployee", method = RequestMethod.POST)
	public ModelAndView unassignEmployee(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (projectServiceImpl.isProjectExist(projectId)) {
				EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
				if (employeeServiceImpl.isEmployeeExist(employeeId)) {
					boolean statusOfUnassignProject = projectServiceImpl.unassignEmployee(employeeId, projectId);
					if (statusOfUnassignProject) {
						modelAndview.setViewName("displayMessages");
						modelAndview.addObject(Constants.MESSAGE, Constants.UNASSIGN_SUCCESS);
					} else {
						modelAndview.setViewName("displayMessages");
						modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_ASSIGN);
					}
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To retrieve project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.POST)
	public ModelAndView retrieveProject(@RequestParam(Constants.ID) int id) {
		try {
			//Project project = projectServiceImpl.isProjectExist(id);
			if (false == projectServiceImpl.isProjectExist(id)) {
				boolean statusOfRetrieveProject = projectServiceImpl.retrieveProject(id);
				if (statusOfRetrieveProject) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_FAILURE);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, "This project is not deleted!!");
			}

		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get project details return List<Project> provides the list of Project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/Retrieve", method = RequestMethod.GET)
	public ModelAndView getDeletedProjects() {
		try {
			List<Project> projectList = projectServiceImpl.getDeletedProjects();
			modelAndview.setViewName("displayDeletedProjects");
			modelAndview.addObject(Constants.DELETED_PROJECTS, projectList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get deleted project return List<Project> provides the list of Project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/DisplayAllProject", method = RequestMethod.GET)
	public ModelAndView getProjectDetails() {
		try {
			List<Project> projectList = projectServiceImpl.getProjectDetails();
			modelAndview.setViewName("displayProjects");
			modelAndview.addObject(Constants.PROJECTS, projectList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	@RequestMapping(value = "/getProject")
	public ModelAndView getProject() {
		ModelAndView model = new ModelAndView("createProject");
		model.addObject("project", new Project());
		return model;
	}



	/**
	 * To close the session factory
	 */
	public void closeSessionFactory() {
		projectServiceImpl.closeSessionFactory();
	}
}