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
	 * To map the assignProject jsp
	 * 
	 * return String provides the view name
	 */
	@GetMapping(Constants.GET_EMPLOYEEID_URL)
	public String getEmployeeId() {
		return Constants.GET_EMPLOYEEID;
	}
	
	/**
	 * To map the assignProject jsp
	 * 
	 * return String provides the view name
	 */
	@RequestMapping(value = Constants.ASSIGN_PROJECT_URL, method = RequestMethod.POST)
	public ModelAndView assignProject(@RequestParam(Constants.ID) int employeeId) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employeeId)) {
				ProjectService projectServiceImpl = new ProjectServiceImpl();
				modelAndview.setViewName(Constants.ASSIGN_PROJECT);
				modelAndview.addObject(Constants.PROJECTS, projectServiceImpl.getProjects());
				modelAndview.addObject(Constants.EMPLOYEE, employeeServiceImpl.getEmployeeById(employeeId));
			} else {
				displayMessages(Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To create the employee
	 * 
	 * @param employee provides the employee object return ModelAndView provides
	 *                 both model and view
	 */
	@RequestMapping(value = Constants.CREATE_EMPLOYEE_URL, method = RequestMethod.POST)
	public String createEmployee(@ModelAttribute(Constants.EMPLOYEE) Employee employee) {
		try {
			Map<String, String> isValid = employeeServiceImpl.validate(String.valueOf(employee.getPhoneNumber()),
					employee.getEmailId());
			if (Constants.TRUE == isValid.get(Constants.BOOLEAN)) {
				if (false == employeeServiceImpl.createEmployee(employee, employee.getAddressList())) {
					displayMessages(Constants.ADD_FAILURE);
				} else {
					displayMessages(isValid.get(Constants.MESSAGE));
				}
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return "redirect:/displayAllEmployee";
	}

	/**
	 * To display messages to UI
	 * 
	 * @param message provides the message dispaly to UI return ModelAndView
	 *                provides both model and view
	 */
	public ModelAndView displayMessages(String message) {
		modelAndview.setViewName(Constants.DISPLAY_MESSAGES);
		modelAndview.addObject(Constants.MESSAGE, message);
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
					List<Employee> employeeList = employeeServiceImpl.getEmployeeDetails();
					modelAndview.setViewName(Constants.DISPLAY_EMPLOYEES);
					modelAndview.addObject(Constants.EMPLOYEES, employeeList);
				} else {
					displayMessages(Constants.DELETE_FAILURE);
				}
			} else {
				displayMessages(Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
				displayMessages(Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
					Employee employeeData = employeeServiceImpl.getEmployeeById(employee.getId());
					modelAndview.setViewName(Constants.DISPLAY_SINGLE_EMPLOYEE);
					modelAndview.addObject(Constants.EMPLOYEE, employeeData);
				} else {
					displayMessages(Constants.UPDATE_FAILURE);
				}
			} else {
				displayMessages(Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
				if (employeeServiceImpl.isProjectAssignExist(employeeId, projectIdList)) {
					Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
					modelAndview.setViewName(Constants.DISPLAY_SINGLE_EMPLOYEE);
					modelAndview.addObject(Constants.EMPLOYEE, employee);
				}
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
				displayMessages(Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
					List<Employee> employeeList = employeeServiceImpl.getEmployeeDetails();
					modelAndview.setViewName(Constants.DISPLAY_EMPLOYEES);
					modelAndview.addObject(Constants.EMPLOYEES, employeeList);
				} else {
					displayMessages(Constants.RETRIEVE_FAILURE);
				}
			} else {
				displayMessages(Constants.EMPLOYEE_NOT_DELETED);
			}
		} catch (EmployeeManagementException exception) {
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
			displayMessages(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
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
