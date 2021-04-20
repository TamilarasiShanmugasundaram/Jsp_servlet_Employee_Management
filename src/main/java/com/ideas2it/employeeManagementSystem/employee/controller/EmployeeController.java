package com.ideas2it.employeeManagementSystem.employee.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import com.ideas2it.employeeManagementSystem.project.model.Project;
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
	 * To get employee details from client side
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 * @throws EmployeeManagementException
	 * @throws IOException
	 * @throws ServletException
	 */

	@GetMapping(value = "/Employee")
	public ModelAndView requestProject() {
		modelAndview.setViewName("employee");
		return modelAndview;
	}

	@GetMapping(value = "/deleteEmployee")
	public ModelAndView deleteEmployee() {
		modelAndview.setViewName("deleteEmployee");
		return modelAndview;
	}

	@GetMapping(value = "/getIdForEmployeeUpdate")
	public ModelAndView getIdForEmployeeUpdate() {
		modelAndview.setViewName("getIdForEmployeeUpdate");
		return modelAndview;
	}

	@GetMapping(value = "/assignProject")
	public ModelAndView assignProject() {
		try {
			ProjectService projectServiceImpl = new ProjectServiceImpl();
			modelAndview.setViewName("assignProject");
			modelAndview.addObject("Projects",projectServiceImpl.getProjects());			
		} catch(EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	@GetMapping(value = "/unassignProject")
	public ModelAndView unassignProject() {
		modelAndview.setViewName("unassignProject");
		return modelAndview;
	}

	@GetMapping(value = "/retrieveEmployee")
	public ModelAndView retrieveEmployee() {
		modelAndview.setViewName("retrieveEmployee");
		return modelAndview;
	}

	@GetMapping(value = "/getEmployeeId")
	public ModelAndView getEmployeeId() {
		modelAndview.setViewName("getEmployeeId");
		return modelAndview;
	}

	@RequestMapping(value = "/CreateEmployee", method = RequestMethod.POST)
	public ModelAndView createEmployee(@ModelAttribute("employee") Employee employee) {
		try {
			Map<String, String> isValid = employeeServiceImpl.validate(String.valueOf(employee.getPhoneNumber()), employee.getEmailId());
			if ("true" == isValid.get("Boolean")) {
				employeeServiceImpl.createEmployee(employee, employee.getAddressList());
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.ADD_SUCCESS);
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, isValid.get(Constants.MESSAGE));
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To delete the employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/DeleteEmployee", method = RequestMethod.POST)
	public ModelAndView deleteEmployee(@RequestParam(Constants.ID) int id) {
		try {
			if (employeeServiceImpl.isEmployeeExist(id)) {
				boolean statusOfDeleteEmployee = employeeServiceImpl.deleteEmployee(id);
				if (statusOfDeleteEmployee) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.DELETE_FAILURE);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the name of the employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/getEmployeeForUpdate", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(Constants.ID) int id) {
		try {
			if (employeeServiceImpl.isEmployeeExist(id)) {
				modelAndview.setViewName("createEmployee");
				modelAndview.addObject(Constants.EMPLOYEE, employeeServiceImpl.getEmployeeById(id));
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To update the PhoneNumber of the employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 * @throws EmployeeManagementException
	 */
	@RequestMapping(value = "/UpdateEmployee", method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute("employee") Employee employee) {
		try {
			//List<Map<String, String>> addressList = new ArrayList<Map<String, String>>();
			//addressList = getAddressDetails(addressList, Constants.PERMANENT, request);
			//addressList = getAddressDetails(addressList, Constants.TEMPORARY, request);
			if (employeeServiceImpl.isEmployeeExist(employee.getId())) {
				boolean statusOfUpdateEmployee = employeeServiceImpl.updateAll(employee);
				if (statusOfUpdateEmployee) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.UPDATE_FAILURE);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To assign employee for project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/AssignProject", method = RequestMethod.POST)
	public ModelAndView assignProject(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam("projectIds") List<Integer> projectIdList) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employeeId)) {
				ProjectService projectServiceImpl = new ProjectServiceImpl();
				
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, projectIdList.size());
			//	if (projectServiceImpl.isProjectExist(projectId)) {
					//if (employeeServiceImpl.isAssignExist(employeeId, projectIdList)) {
//						modelAndview.setViewName("displayMessages");
//						modelAndview.addObject(Constants.MESSAGE, Constants.ALREADY_ASSIGN);
//					} else {
						//boolean statusOfAssignProject = employeeServiceImpl.assignProject(projectId, employeeId);
//						if (statusOfAssignProject) {
//							modelAndview.setViewName("displayMessages");
//							modelAndview.addObject(Constants.MESSAGE, Constants.ASSIGN_SUCCESS);
//						} else {
//							modelAndview.setViewName("displayMessages");
//							modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_ASSIGN);
//						}
					}
//				} else {
//					modelAndview.setViewName("displayMessages");
//					modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
//				}
//			} else {
//				modelAndview.setViewName("displayMessages");
//				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
//			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To unassign employee for project
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/UnassignProject", method = RequestMethod.POST)
	public ModelAndView unassignProject(@RequestParam(Constants.EMPLOYEE_ID) int employeeId,
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employeeId)) {
				boolean statusOfUnassignProject = employeeServiceImpl.unassignProject(employeeId, projectId);
				if (statusOfUnassignProject) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.UNASSIGN_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_ASSIGN);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get employee by employeeId
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/displaySingleEmployee", method = RequestMethod.GET)
	public ModelAndView getEmployeeById(@RequestParam(Constants.ID) int id) {
		try {
			if (employeeServiceImpl.isEmployeeExist(id)) {
				Employee employee = employeeServiceImpl.getEmployeeById(id);
				modelAndview.setViewName("displaySingleEmployee");
				modelAndview.addObject(Constants.EMPLOYEE, employee);
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_EXIST);
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get employee details return List<Employee> provides the list of employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/displayAllEmployee", method = RequestMethod.GET)
	public ModelAndView getEmployeeDetails() {
		try {
			List<Employee> employeeList = employeeServiceImpl.getEmployeeDetails();
			modelAndview.setViewName("displayEmployees");
			modelAndview.addObject(Constants.EMPLOYEES, employeeList);
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
	@RequestMapping(value = "/retrieveEmployee", method = RequestMethod.POST)
	public ModelAndView retrieveEmployee(@RequestParam(Constants.ID) int id) {
		try {
			if (false == employeeServiceImpl.isEmployeeExist(id)) {
				if (employeeServiceImpl.retrieveEmployee(id)) {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_SUCCESS);
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_FAILURE);
				}
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, "This employee is not deleted!!");
			}
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	/**
	 * To get deleted employee details
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 */
	@RequestMapping(value = "/RetrieveEmployees", method = RequestMethod.GET)
	public ModelAndView getDeletedEmployees() {
		try {
			List<Employee> employeeList = employeeServiceImpl.getDeletedEmployees();
			modelAndview.setViewName("displayDeletedEmployees");
			modelAndview.addObject(Constants.DELETED_EMPLOYEES, employeeList);
		} catch (EmployeeManagementException exception) {
			modelAndview.setViewName("displayMessages");
			modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		}
		return modelAndview;
	}

	@RequestMapping(value = "/getEmployee")
	public ModelAndView getEmployee() {
		ModelAndView model = new ModelAndView("createEmployee");
		model.addObject("employee", new Employee());
		return model;
	}
}
