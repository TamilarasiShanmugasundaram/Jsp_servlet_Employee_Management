package com.ideas2it.employeeManagementSystem.employee.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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
	 * To get employee details from client side
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 * @throws EmployeeManagementException
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/CreateEmployee", method = RequestMethod.POST)
	public ModelAndView getEmployeeFromClient(@RequestParam(Constants.NAME) String name,
			@RequestParam(Constants.PHONENUMBER) String phoneNumber, @RequestParam(Constants.EMAIL_ID) String emailId,
			@RequestParam(Constants.DOB) String dateOfBirth, @RequestParam(Constants.DOJ) String dateOfJoining,
			HttpServletRequest request) {
		try {
			Map<String, String> isValid = employeeServiceImpl.validate(phoneNumber, emailId);
			if ("true" == isValid.get("Boolean")) {
				createEmployee(name, phoneNumber, emailId, dateOfBirth, dateOfJoining, request);
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
	 * To create the employee
	 * 
	 * @param name          provides the employee name
	 * @param phoneNumber   provides the employee phoneNumber
	 * @param emailId       provides the employee emailId
	 * @param dateOfBirth   provides the employee dob
	 * @param dateOfJoining provides the employee doj
	 * @param request       provides the http servlet request
	 * @param response      provides the http servlet response
	 */
	public ModelAndView createEmployee(String name, String phoneNumber, String emailId, String dateOfBirth,
			String dateOfJoining, HttpServletRequest request) {
		try {
			List<Map<String, String>> addressList = new ArrayList<Map<String, String>>();
			addressList = getAddressDetails(addressList, Constants.PERMANENT, request);
			addressList = getAddressDetails(addressList, Constants.TEMPORARY, request);
			boolean statusOfCreateEmployee = employeeServiceImpl.createEmployee(name, emailId, dateOfBirth,
					dateOfJoining, Long.parseLong(phoneNumber), addressList);
			if (statusOfCreateEmployee) {
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

	/**
	 * To get the address of the employee
	 * 
	 * @param addressList provides the list of address
	 * @param request     provides the http servlet request
	 * @param request     provides the http servlet response
	 * @param addressType provides the type of the address
	 */
	public List<Map<String, String>> getAddressDetails(List<Map<String, String>> addressList, String addressType,
			HttpServletRequest request) {
		Map<String, String> addressMap = new HashMap<String, String>();
		String doorNumber = request.getParameter(addressType + Constants.DOORNUMBER);
		String streetNumber = request.getParameter(addressType + Constants.STREETNUMBER);
		String city = request.getParameter(addressType + Constants.CITY);
		String district = request.getParameter(addressType + Constants.DISTRICT);
		String state = request.getParameter(addressType + Constants.STATE);
		String country = request.getParameter(addressType + Constants.COUNTRY);
		String pincode = request.getParameter(addressType + Constants.PINCODE);
		addressMap.put(Constants.DOORNUMBER, doorNumber);
		addressMap.put(Constants.STREETNUMBER, streetNumber);
		addressMap.put(Constants.CITY, city);
		addressMap.put(Constants.DISTRICT, district);
		addressMap.put(Constants.STATE, state);
		addressMap.put(Constants.COUNTRY, country);
		addressMap.put(Constants.PINCODE, pincode);
		addressList.add(addressMap);
		return addressList;
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
				List<Employee> employeeList = employeeServiceImpl.getEmployeeById(id);
				Employee employee = employeeList.get(0);
				modelAndview.setViewName("updateEmployee");
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
	 * To update the PhoneNumber of the employee
	 * 
	 * @param request provides the http servlet request
	 * @param request provides the http servlet response
	 * @throws EmployeeManagementException
	 */
	@RequestMapping(value = "/UpdateEmployee", method = RequestMethod.POST)
	public ModelAndView updateAll(@RequestParam(Constants.ID) int id, @RequestParam(Constants.NAME) String name,
			@RequestParam(Constants.PHONENUMBER) long phoneNumber, @RequestParam(Constants.EMAIL_ID) String emailId,
			@RequestParam(Constants.DOB) String dateOfBirth, @RequestParam(Constants.DOJ) String dateOfJoining,
			HttpServletRequest request) {
		try {
			List<Map<String, String>> addressList = new ArrayList<Map<String, String>>();
			addressList = getAddressDetails(addressList, Constants.PERMANENT, request);
			addressList = getAddressDetails(addressList, Constants.TEMPORARY, request);
			if (employeeServiceImpl.isEmployeeExist(id)) {
				boolean statusOfUpdateEmployee = employeeServiceImpl.updateAll(id, emailId, phoneNumber, name,
						dateOfBirth, dateOfJoining, addressList);
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
			@RequestParam(Constants.PROJECT_ID) int projectId) {
		try {
			if (employeeServiceImpl.isEmployeeExist(employeeId)) {
				ProjectService projectServiceImpl = new ProjectServiceImpl();
				if (projectServiceImpl.isProjectExist(projectId)) {
					if (employeeServiceImpl.isAssignExist(employeeId, projectId)) {
						modelAndview.setViewName("displayMessages");
						modelAndview.addObject(Constants.MESSAGE, Constants.ALREADY_ASSIGN);
					} else {
						boolean statusOfAssignProject = employeeServiceImpl.assignProject(projectId, employeeId);
						if (statusOfAssignProject) {
							modelAndview.setViewName("displayMessages");
							modelAndview.addObject(Constants.MESSAGE, Constants.ASSIGN_SUCCESS);
						} else {
							modelAndview.setViewName("displayMessages");
							modelAndview.addObject(Constants.MESSAGE, Constants.EMPLOYEE_NOT_ASSIGN);
						}
					}
				} else {
					modelAndview.setViewName("displayMessages");
					modelAndview.addObject(Constants.MESSAGE, Constants.PROJECT_NOT_EXIST);
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
				List<Employee> employeeList = employeeServiceImpl.getEmployeeById(id);
				modelAndview.setViewName("displaySingleEmployee");
				modelAndview.addObject(Constants.EMPLOYEE, employeeList);
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
	public ModelAndView retrieveEmployee(@RequestParam(Constants.ID) int projectId) {
		try {
			if (employeeServiceImpl.retrieveEmployee(projectId)) {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_SUCCESS);
			} else {
				modelAndview.setViewName("displayMessages");
				modelAndview.addObject(Constants.MESSAGE, Constants.RETRIEVE_FAILURE);
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
}
