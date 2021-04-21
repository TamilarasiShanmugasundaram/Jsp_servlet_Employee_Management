package com.ideas2it.employeeManagementSystem.employee.controller;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.employee.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.employee.service.Impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.project.service.ProjectService;
import com.ideas2it.employeeManagementSystem.project.service.Impl.ProjectServiceImpl;

/**
 * Class to perform CRUD operation on employee
 * 
 * @author TamilarasiShanmugasundaram created 23-02-2021
 */
@Controller
public class EmployeeController {
	EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
	ModelAndView modelAndview = new ModelAndView();

	/**
	 * To map the employee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.EMPLOYEE_URL)
	public String requestProject() {
		return Constants.EMPLOYEE;
	}

	/**
	 * To map the deleteEmployee jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.DELETE_EMPLOYEE_URL)
	public String deleteEmployee() {
		return Constants.DELETE_EMPLOYEE;
	}

	/**
	 * To map the getIdForEmployeeUpdate jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.GETID_UPDATE_URL)
	public String getIdForEmployeeUpdate() {
		return Constants.GETID_UPDATE;
	}

	/**
	 * To map the assignProject jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.ASSIGN_PROJECT_URL)
	public ModelAndView assignProject() {
		try {
			ProjectService projectServiceImpl = new ProjectServiceImpl();
			modelAndview.setViewName(Constants.ASSIGN_PROJECT);
			modelAndview.addObject(Constants.PROJECTS, projectServiceImpl.getProjects());
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To map the assignProject jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.UNASSIGN_PROJECT_URL)
	public String unassignProject() {
		return Constants.UNASSIGN_PROJECT;
	}

	/**
	 * To map the assignProject jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.RETRIEVE_EMPLOYEE_URL)
	public String retrieveEmployee() {
		return Constants.RETRIEVE_EMPLOYEE;
	}

	/**
	 * To map the assignProject jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.GET_EMPLOYEEID_URL)
	public String getEmployeeId() {
		return Constants.GET_EMPLOYEEID;
	}

	/**
	 * To create the employee
	 * 
	 * @param employee provides the employee object return ModelAndView provides
	 *                 both model and view
	 */
	@RequestMapping(value = Constants.CREATE_EMPLOYEE_URL, method = RequestMethod.POST)
	public ModelAndView createEmployee(@ModelAttribute(Constants.EMPLOYEE) Employee employee) {
		try {
			Map<String, String> isValid = employeeServiceImpl.validate(String.valueOf(employee.getPhoneNumber()),
					employee.getEmailId());
			if (Constants.TRUE == isValid.get(Constants.BOOLEAN)) {
				employeeServiceImpl.createEmployee(employee, employee.getAddressList());
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.ADD_SUCCESS);
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, isValid.get(Constants.MESSAGE));
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To delete the employee
	 * 
	 * @param id provides the employee id return ModelAndView provides both model
	 *           and view
	 */
	@RequestMapping(value = Constants.DELETE_EMPLOYEE_POST_URL, method = RequestMethod.POST)
	public ModelAndView deleteEmployee(@RequestParam(Constants.ID) int id) {
		try {
			if (employeeServiceImpl.isEmployeeExist(id)) {
				boolean statusOfDeleteEmployee = employeeServiceImpl.deleteEmployee(id);
				if (statusOfDeleteEmployee) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_FAILURE);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the name of the employee
	 * 
	 * @param id provides the employee id return ModelAndView provides both model
	 *           and view
	 */
	@RequestMapping(value = Constants.UPDATE_URL, method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(Constants.ID) int id) {
		try {
			if (employeeServiceImpl.isEmployeeExist(id)) {
				modelAndview.setViewName(Constants.CREATE_EMPLOYEE);
				modelAndview.addObject(Constants.EMPLOYEE, employeeServiceImpl.getEmployeeById(id));
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the PhoneNumber of the employee
	 * 
	 * @param employee provides the employee object return ModelAndView provides
	 *                 both model and view
	 */
	@RequestMapping(value = Constants.UPDATE_POST_URL, method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute(Constants.EMPLOYEE) Employee employee) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employee.getId())) {
				boolean statusOfUpdateEmployee = employeeServiceImpl.updateAll(employee);
				if (statusOfUpdateEmployee) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_FAILURE);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To assign employee for project
	 * 
	 * @param employeeId    provides the employee id
	 * @param projectIdList provides list of project id need to assign return
	 *                      ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.ASSIGN_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView assignProject(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam(Constants.PROJECT_IDS) List<Integer> projectIdList) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employeeId)) {
				employeeServiceImpl.isAssignExist(employeeId, projectIdList);
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.ASSIGN_SUCCESS);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To unassign employee for project
	 * 
	 * @param employeeId provides the employee id
	 * @param projectI   provides project id return ModelAndView provides both model
	 *                   and view
	 */
	@RequestMapping(value = Constants.UNASSIGN_PROJECT_POST_URL, method = RequestMethod.POST)
	public ModelAndView unassignProject(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employeeId)) {
				boolean statusOfUnassignProject = employeeServiceImpl.unassignProject(employeeId, projectId);
				if (statusOfUnassignProject) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.UNASSIGN_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_ASSIGN);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get employee by employeeId
	 * 
	 * @param id provides the employee id return ModelAndView provides both model
	 *           and view
	 */
	@RequestMapping(value = Constants.DISPLAY_SINGLE_EMPLOYEE_URL, method = RequestMethod.GET)
	public ModelAndView getEmployeeById(@RequestParam(Constants.ID) int id) {
		try {
			if (employeeServiceImpl.isEmployeeExist(id)) {
				Employee employee = employeeServiceImpl.getEmployeeById(id);
				modelAndview.setViewName(Constants.DISPLAY_SINGLE_EMPLOYEE);
				modelAndview.addObject(Constants.EMPLOYEE, employee);
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get employee details
	 * 
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.DISPLAY_ALL_EMPLOYEE_URL, method = RequestMethod.GET)
	public ModelAndView getEmployeeDetails() {
		try {
			List<Employee> employeeList = employeeServiceImpl.getEmployeeDetails();
			modelAndview.setViewName(Constants.DISPLAY_EMPLOYEES);
			modelAndview.addObject(Constants.EMPLOYEES, employeeList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To retrieve project
	 * 
	 * @param id provides the employee id return ModelAndView provides both model
	 *           and view
	 */
	@RequestMapping(value = Constants.RETRIEVE_EMPLOYEES_POST_URL, method = RequestMethod.POST)
	public ModelAndView retrieveEmployee(@RequestParam(Constants.ID) int id) {
		try {
			if (false == employeeServiceImpl.isEmployeeExist(id)) {
				if (employeeServiceImpl.retrieveEmployee(id)) {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_SUCCESS);
				} else {
					modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_FAILURE);
				}
			} else {
				modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_DELETED);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get deleted employee details
	 * 
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.RETRIEVE_EMPLOYEES_GET_URL, method = RequestMethod.GET)
	public ModelAndView getDeletedEmployees() {
		try {
			List<Employee> employeeList = employeeServiceImpl.getDeletedEmployees();
			modelAndview.setViewName(Constants.DISPLAY_DELETED_EMPLOYEES);
			modelAndview.addObject(Constants.DELETED_EMPLOYEES, employeeList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get employee object
	 * 
	 * return ModelAndView provides both model and view
	 */
	@RequestMapping(value = Constants.GET_EMPLOYEE_URL)
	public ModelAndView getEmployee() {
		ModelAndView model = new ModelAndView(Constants.CREATE_EMPLOYEE);
		model.addObject(Constants.EMPLOYEE, new Employee());
		return model;
	}
}
