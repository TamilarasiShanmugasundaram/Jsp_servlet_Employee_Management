//package com.ideas2it.employeeManagementSystem.employee.view;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ideas2it.employeeManagementSystem.constants.Constants;
//import com.ideas2it.employeeManagementSystem.employee.model.Address;
//import com.ideas2it.employeeManagementSystem.employee.model.Employee;
//import com.ideas2it.employeeManagementSystem.employee.controller.EmployeeController;
//import com.ideas2it.employeeManagementSystem.project.model.Project;
//
///**
// * To perform CRUD operation on employee details
// * @author TamilarasiShanmugasundaram
// * created 23-02-2021
// */
//public class EmployeeView {
//    EmployeeController employeeController = new EmployeeController();
//    BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));	
//	
//    /**
//     * To select the operation you want to perform on employee details
//     */
//    public void selectOperation(String option, HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
//        boolean flag = true;
//        while(flag) {
//            //System.out.println(Constants.EMPLOYEE_OPTION_BANNER);
//           // System.out.println(Constants.ENTER_CHOICE);
//            //String choice = reader.readLine();
//            //int option = isValidNumber(choice);
//        	System.out.println("id view : "+option);
//            switch (option) {
//                case "create":
//                	System.out.println("id view : "+option);
//                    createEmployee(request);
//                    break;
//                case "update":
//                    selectFieldForUpdation();
//                    break;
//                case "delete":
//                    deleteEmployee();
//                    break;
//                case "display":
//                    selectChoiceForDisplay();
//                    break;
//                case "assign":
//                    assignProject();
//                    break;
//                case "retrieve":  
//                    retrieveEmployee();
//                    break;
//	        case "unassign":
//                    unassignProject();
//                    break;
//                default:
//                    System.out.println(Constants.ENTER_VALID_CHOICE);
//            }
//        }
//    }
// 
//    /**
//     * To check the phone number is already exist or not
//     */
//    public long isPhoneNumberExist(long phoneNumber) throws IOException {
//        String error = null;
//        boolean flag = true;
//        while(flag) {
//            Map<String, String> resultMap = employeeController.isPhoneNumberExist(phoneNumber);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(null == resultMap.get(Constants.EXCEPTION)) {
//                if(result) {
//                    System.out.println(Constants.PHONENUMBER_EXIST); 
//                    phoneNumber = getPhoneNumber();
//                } else {
//                    flag = false;
//                } 
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//                phoneNumber = getPhoneNumber();
//            }
//        }    
//        return phoneNumber;
//    }
//
//    /**
//     * To check the phone number is already exist or not
//     */
//    public String isEmailExist(String emailId) throws IOException {
//        String error = null;
//        boolean flag = true;
//        while(flag) {
//            Map<String, String> resultMap = employeeController.isEmailExist(emailId);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(null == resultMap.get(Constants.EXCEPTION)) {
//                if(result) {
//                    System.out.println(Constants.EMAIL_EXIST); 
//                    emailId = getEmailId();
//                } else {
//                    flag = false;
//                } 
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//                emailId = getEmailId();
//            }
//        }
//        return emailId;
//    }
//
//    /**
//     * To create employee
//     */
//    public void createEmployee(HttpServletRequest request) throws ParseException, IOException {  
//        String name = request.getParameter("name");
//        long phoneNumber = Long.parseLong(request.getParameter("phonenumber"));
//        phoneNumber = isPhoneNumberExist(phoneNumber);
//        String emailId = request.getParameter("emailId");
//        emailId = isEmailExist(emailId);
//        String dob =  request.getParameter("dob");
//        String doj =  request.getParameter("doj");
//	     List<Map<String, String>> addressList = new ArrayList<Map<String, String>>();
//	   // System.out.println(Constants.ENTER_PERMANENT_ADDRESS);
//        addressList = getAddressDetails(addressList);         
//        addressList = selectChoiceForAddressAddition(addressList); 
//        Map<String, String> resultMap = employeeController.createEmployee(name, emailId, dob, doj, phoneNumber, addressList);	
//        boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//        if(result) {
//            System.out.println(Constants.ADD_SUCCESS);
//        } else {
//            System.out.println(resultMap.get(Constants.EXCEPTION));
//        }		
//    }
//
//    /**
//     * To get name of the employee
//     * return name
//     *        provides the employee name
//     */
//    public String getName() throws IOException {
//        boolean flag = true;
//	String name = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_NAME);
//            name = reader.readLine();
//            if(employeeController.validateName(name)) {
//	        flag = false;
//            } 
//	}
//	return name;
//    }
//
//    /**
//     * To get employee phone number
//     * return phoneNumber
//     *        provides the employee phone number
//     */
//    public long getPhoneNumber() throws IOException {
//        boolean flag = true;
//	String phoneNumberString = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_PHONENUMBER);
//            phoneNumberString = reader.readLine();
//            if(employeeController.validatePhoneNumber(phoneNumberString)) {
//	        flag = false;
//            } 
//	}
//	return Long.parseLong(phoneNumberString);
//    }
//
//    /**
//     * To get employee email id
//     * return emailId
//     *        provides the employee email id
//     */
//    public String getEmailId() throws IOException {
//        boolean flag = true;
//	String emailId = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_EMAILID);
//            emailId = reader.readLine();
//            if(employeeController.validateEmailId(emailId)) {
//		flag = false;
//            } 
//	}
//	return emailId;
//    }
//
//    /**
//     * To get employee dob
//     * return dob
//     *        provides the employee dob
//     */
//    public String getDOB() throws IOException {
//	boolean flag = true;
//	String dobString = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_DOB);
//            dobString = reader.readLine();
//            if(employeeController.validateDate(dobString)) {
//		flag = false;
//            } 
//	}
//	return dobString;
//    }
//
//    /**
//     * To get employee doj
//     * return doj
//     *        provides the employee doj
//     */
//    public String getDOJ() throws IOException {
//	boolean flag = true;
//	String dojString = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_DOJ);
//            dojString = reader.readLine();
//            if(employeeController.validateDate(dojString)) {
//		flag = false;
//            } 
//	}
//	return dojString;
//    }
//	
//    /**
//     * To get address details
//     * return address
//     *        provides the address object
//     */
//    public List<Map<String, String>> getAddressDetails(List<Map<String, String>> addressList) throws IOException {
//	System.out.println(Constants.ENTER_DOORNUMBER);
//        String doorNumber = reader.readLine();
//	System.out.println(Constants.ENTER_STREET_NUMBER);
//        String streetNumber = reader.readLine();
//        isValidNumber(streetNumber);
//        System.out.println(Constants.ENTER_CITY);
//        String city = reader.readLine();
//        System.out.println(Constants.ENTER_DISTRICT);
//        String district = reader.readLine();
//        System.out.println(Constants.ENTER_STATE);
//        String state = reader.readLine();
//        System.out.println(Constants.ENTER_COUNTRY);
//        String country = reader.readLine();
//	System.out.println(Constants.ENTER_PINCODE);
//        String pincode = reader.readLine(); 
//        int validPincode = isValidNumber(pincode);
//	addressList = addAddressInList(doorNumber, streetNumber, city, district, state, country, String.valueOf(validPincode), addressList);
//	return addressList;
//    }
//
//    /**
//     * To validate the given number is valid or not
//     */ 
//    public int isValidNumber(String number) throws IOException {
//        String error = null;
//        boolean flag = true;
//        while(flag) {
//            error = employeeController.isValidNumber(number); 
//            if(null != error) {
//                System.out.println(Constants.ERROR + error);
//	        System.out.println(Constants.ENTER_VALID_NUMBER);
//                number = reader.readLine();
//            } else {
//                flag = false;
//            }
//        }
//        return Integer.parseInt(number);
//    }
//
//    /**
//     * To add all address in map of list
//     * return List<Map<String, String>>
//     *        provides list of map of addresses
//     */
//    public List<Map<String, String>> addAddressInList(String doorNumber, String streetNumber,String city, String district, 
//	String state, String country, String pincode, List<Map<String, String>> addressList) {	
//	Map<String, String> addressMap = new HashMap<String, String>();
//	addressMap.put(Constants.DOORNUMBER, doorNumber);
//	addressMap.put(Constants.STREETNUMBER, streetNumber);
//	addressMap.put(Constants.CITY, city);
//	addressMap.put(Constants.DISTRICT, district);
//	addressMap.put(Constants.STATE, state);
//	addressMap.put(Constants.COUNTRY, country);
//	addressMap.put(Constants.PINCODE, pincode);
//	addressList.add(addressMap);
//	return addressList;
//    }
//		
//    /**
//     * To select the choice for adding another address of employee
//     * @param employeeId
//     *        provides the id of the employee
//     */
//    public List<Map<String, String>> selectChoiceForAddressAddition(List<Map<String, String>> addressList) throws IOException {
//        int flag = 0;
//        while (Constants.ZERO == flag) {
//            System.out.println(Constants.ENTER_ANOTHER_ADDRESS);
//            System.out.println(Constants.YES_NO);
//            int addressCountOption = Integer.parseInt(reader.readLine());
//            switch (addressCountOption) {
//                case 1:
//                    addressList = getAddressDetails(addressList);  
//                    break;					
//                case 2:
//                    flag = 1;
//		    break;
//            }
//        }
//	return addressList;
//    }
//
//    /**
//     * To delete the employee
//     */
//    public void deleteEmployee() throws IOException {
//        System.out.println(Constants.ENTER_EMPLOYEE_ID);
//        String employeeId = reader.readLine();
//        int id = isValidNumber(employeeId);
//	if(employeeController.isEmployeeExist(id)) {
//            Map<String, String> resultMap = employeeController.deleteEmployee(id);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.DELETE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//  	} else {
//            System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//
//    /**
//     * To retrieve the Project
//     */
//    public void retrieveEmployee() throws IOException {
//	List<Employee> deletedEmployees = employeeController.getDeletedEmployees();
//	for (Employee tempEmployeeList : deletedEmployees){
//            System.out.println(tempEmployeeList);
//	    System.out.println();
//        } 
//	if(Constants.ZERO < deletedEmployees.size()) {
//	    System.out.println(Constants.ENTER_EMPLOYEE_ID);
//            String employeeId = reader.readLine();
//            int id = isValidNumber(employeeId);
//	    if(employeeController.retrieveEmployee(id)) {
//	        System.out.println(Constants.RETRIEVE_SUCCESS);
//            } else {
//                System.out.println(Constants.RETRIEVE_FAILURE);
//            }
//	} else {
//	    System.out.println(Constants.NO_EMPLOYEES);	
//	}
//    }
//	
//    /**
//     * To select the field to update employee details
//     */
//    public void selectFieldForUpdation() throws IOException {
//        System.out.println(Constants.ENTER_FIELD_FOR_UPDATE );
//        System.out.println(Constants.FIELD_FOR_UPDATE);
//        String choice = reader.readLine();
//        int fieldOption = isValidNumber(choice);
//        System.out.println(Constants.ENTER_EMPLOYEE_ID);
//        String employeeId = reader.readLine();
//        int id = isValidNumber(employeeId);
//	if(employeeController.isEmployeeExist(id)) {
//	    switch (fieldOption) {
//		case 1:
//		    updateName(id);
//		    break;
//		case 2:
//	    	    selectChoiceForAddressUpdation(id);
//	            break;
//	        case 3:
//		    updateEmailId(id);
//		    break;
//		case 4:
//		    updatePhoneNumber(id);
//		    break;
//	    }
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//
//    /**
//     * To select choice to update the address of employee
//     */
//    public void selectChoiceForAddressUpdation(int id) throws IOException {
//        System.out.println(Constants.FIELD_FOR_ADDRESS_UPDATE );
//        String choice = reader.readLine();
//        int fieldOption = isValidNumber(choice);
//        switch (fieldOption) {
//            case 1:
//                updatePermanentAddress(id);
//                break;
//            case 2:
//                updateTemporaryAddress(id);
//                break;
//        }   
//    }
// 
//    /**
//     * To get the details to update the temporary address
//     * @param id 
//     *        provides the employee id 
//     */
//    public void updateTemporaryAddress(int id) throws IOException {
//        if(employeeController.isEmployeeExist(id)) {
//	    Employee employee = employeeController.getEmployeeById(id);
//	    List<Address> addresses = employeeController.getTemporaryAddressByEmployeeId(id);
//	    int serialNumber = 1;
//	    if(Constants.ZERO < addresses.size()) {
//		for (Address tempAddressList : addresses) {
//		    System.out.println(Constants.ADDRESS + serialNumber);
//		    System.out.println(tempAddressList);
//		    serialNumber++;
//		}  
//		System.out.println(Constants.ENTER_ADDRESS_NUMBER);
//		int addressNumber = Integer.parseInt(reader.readLine());
//		List<Map<String, String>> addressList = new ArrayList<Map<String, String>>();
//		addressList = getAddressDetails(addressList); 
//		Map<String, String> resultMap = employeeController.updateTemporaryAddress(addressNumber, id, addressList);	
//                boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//                if(result) {
//                    System.out.println(Constants.UPDATE_SUCCESS);
//                } else {
//                    System.out.println(resultMap.get(Constants.EXCEPTION));
//                }
//	    } else {
//                System.out.println(Constants.NO_PERMANENT_ADDRESS);
//   	    }	
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//
//    /**
//     * To update the name of the employee
//     * @param id
//     *        provides the id of the employee you want to update
//     */
//    public void updatePermanentAddress(int id) throws IOException {
//	if(employeeController.isEmployeeExist(id)) {
//	    List<Map<String, String>> addressList = new ArrayList<Map<String, String>>();
//	    addressList = getAddressDetails(addressList);
//	    Map<String, String> resultMap = employeeController.updatePermanentAddress(id, addressList);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//
//    /**
//     * To update the name of the employee
//     * @param id
//     *        provides the id of the employee you want to update
//     */
//    public void updateName(int id) throws IOException {     
//	if(employeeController.isEmployeeExist(id)) {
//	    System.out.println(Constants.ENTER_NAME);
//	    String name = reader.readLine();
//	    Map<String, String> resultMap = employeeController.updateName(id, name);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            } 
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//        }		
//    }
//
//    
//    /**
//     * To update the email id of the employee
//     */
//    public void updateEmailId(int id) throws IOException {   
//	if(employeeController.isEmployeeExist(id)) {
//	    String emailId = getEmailId();
//            emailId = isEmailExist(emailId);
//	    Map<String, String> resultMap = employeeController.updateEmailId(id, emailId);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//        }
//    }
//
//    /**
//     * To update the PhoneNumber of the employee
//     */
//    public void updatePhoneNumber(int id) throws IOException{     
//        if(employeeController.isEmployeeExist(id)) {
//	    Long phoneNumber = getPhoneNumber();
//            phoneNumber = isPhoneNumberExist(phoneNumber);
//	    Map<String, String> resultMap = employeeController.updatePhoneNumber(id, phoneNumber);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//
//    /**
//     * To assign employee for project 
//     */
//    public void assignProject() throws IOException {         
//        System.out.println(Constants.ENTER_EMPLOYEE_ID);
//        String  id = reader.readLine();
//        int employeeId = isValidNumber(id);
//	if(employeeController.isEmployeeExist(employeeId)) {
//	    System.out.println(Constants.ENTER_PROJECT_ID);
//            id = reader.readLine();
//            int projectId = isValidNumber(id); 
//	    Map<String, String> resultMap = employeeController.assignProject(projectId, employeeId);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.ASSIGN_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//	
//    /**
//     * To Unassign employee for project 
//     */
//    public void unassignProject() throws IOException {
//        System.out.println(Constants.ENTER_EMPLOYEE_ID);
//        String  id = reader.readLine();
//        int employeeId = isValidNumber(id);
//	if(employeeController.isEmployeeExist(employeeId)) {
//	    Employee employee = employeeController.getEmployeeById(employeeId);
//	    List<Project> projectList = employee.getProjectList(); 
//	    for (Project tempProjectList : projectList){
//		System.out.println(tempProjectList);
//		System.out.println();
//	    } 
//	    System.out.println(Constants.ENTER_PROJECT_ID );
//	    int projectId = Integer.parseInt(reader.readLine()); 
//	    Map<String, String> resultMap = employeeController.unassignProject(employeeId, projectId);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UNASSIGN_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//	}
//    }
//
//    /**
//     * To display the employee
//     */
//    public void selectChoiceForDisplay() throws IOException {
//        System.out.println(Constants.FIELDS_FOR_DISPLAY_EMPLOYEE);
//        int choice = Integer.parseInt(reader.readLine());
//        switch (choice) {
//            case 1:
//                displayAllEmployee();
//                break;
//            case 2:
//                displaySingleEmployee();
//                break;
//        }
//    }
//
//    /**
//     * To display all employee
//     */
//    public void displayAllEmployee() throws IOException {      
//        List<Employee>  employeeList = employeeController.getEmployeeDetails();          
//        for (Employee tempEmployeeList  : employeeList ) {
//            System.out.println(tempEmployeeList);   
//            List<Address> addressList = tempEmployeeList.getAddressList();
//            for (Address tempAddressList : addressList) {
//                System.out.println(tempAddressList);
//            }  
//	    System.out.println();          
//        }  
//    }
//
//   /**
//    * To display single employee
//    */
//    public void displaySingleEmployee() throws IOException {
//        System.out.println(Constants.ENTER_EMPLOYEE_ID);
//        String  employeeId = reader.readLine();
//        int id = isValidNumber(employeeId);
//	if(employeeController.isEmployeeExist(id)) {
//	    Employee employee = employeeController.getEmployeeById(id);
//	    List<Address> addressList = employee.getAddressList();
//	    List<Project> projectList = employee.getProjectList();
//	    System.out.println(employee);  
//	    System.out.println(); 
//	    for (Address tempAddressList : addressList) {
//		System.out.println(tempAddressList);
//	    }  
//	    System.out.println(); 
//	    for (Project tempProjectList : projectList) {
//		System.out.println(tempProjectList);
//		System.out.println();
//	    }  
//	} else {
//	    System.out.println(Constants.EMPLOYEE_NOT_EXIST);
//        }		
//    }
//}