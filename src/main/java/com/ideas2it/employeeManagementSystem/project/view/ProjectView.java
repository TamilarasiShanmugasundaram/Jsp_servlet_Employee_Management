//package com.ideas2it.employeeManagementSystem.project.view;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.HashMap;
//import java.util.List; 
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ideas2it.employeeManagementSystem.constants.Constants;
//import com.ideas2it.employeeManagementSystem.employee.model.Employee;
//import com.ideas2it.employeeManagementSystem.employee.service.EmployeeService;
//import com.ideas2it.employeeManagementSystem.employee.service.Impl.EmployeeServiceImpl;
//import com.ideas2it.employeeManagementSystem.project.model.Project;
//import com.ideas2it.employeeManagementSystem.project.controller.ProjectController;
//
///**
// * To perform CRUD operation on project details
// * @author Tamilarasi Shanmugasundaram
// * created 05-03-2021
// */
//public class ProjectView {
//   ProjectController projectController = new ProjectController();
//   BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
//   EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
//
//    /**
//     * To select the operation you want to perform on project details
//     * @throws ServletException 
//     */
//    public void selectOperation(String option, HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, ServletException {
//        boolean flag = true;
//        while(flag) {
//            // System.out.println(Constants.PROJECT_OPTION_BANNER);
//           
//            //String choice = reader.readLine();
//            //int option = isValidNumber(choice);
//            switch (option) {
//                case "create":
//                    createProject(request, response);
//                    flag = false;
//                    break;
//                case "update":
//                    selectFieldForUpdation();
//                    break;
//                case "delete":
//                    deleteProject(request, response);
//                    flag = false;
//                    break;
//                case "display":
//                    selectChoiceForDisplay();
//                    break;
//                case "assign":
//                    assignEmployee();
//                    break;
//                case "retrieve":
//                    retrieveProject(request, response);
//                    break;
//                case "unassign":
//                    unassignEmployee();
//                    break;
//                default:
//                    System.out.println(Constants.ENTER_VALID_CHOICE);
//            }
//        }
//    }
//
//    /**
//     * To validate the given number is valid or not
//     */ 
//    public int isValidNumber(String number) throws IOException {
//        String error = null;
//        boolean flag = true;
//        while(flag) {
//            error = employeeServiceImpl.isValidNumber(number); 
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
//     * To create project
//     */
//    public void createProject(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {  
//       // System.out.println(Constants.ENTER_TITLE);
//        String title = request.getParameter("title");
//        String startDate = request.getParameter("startDate");
//        String estimateEndDate = request.getParameter("estimateEndDate");
//        boolean flag = true;
//        String error = null;
//        while(flag) {
//            error = projectController.validateTimePeriodForProject(startDate, estimateEndDate);
//            if(null != error) {
//                System.out.println(Constants.ERROR + error); 
//                startDate = getStartDate();
//                estimateEndDate = getEstimateEndDate();
//            } else {
//                flag = false;
//            }
//        }
//      //  System.out.println(Constants.ENTER_STATUS);
//        String status = request.getParameter("status");
//     //   System.out.println(Constants.ENTER_CLIENT);
//        String client =request.getParameter("client");
//      //  System.out.println(Constants.ENTER_BUDGET);
//        String budget = request.getParameter("budget");
//        int budgetInt = isValidNumber(budget);
//        PrintWriter output =response.getWriter();
//        Map<String, String> resultMap = projectController.createProject(title, startDate, estimateEndDate, status, client, Long.valueOf(budgetInt));
//        boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//        if(result) {
//           // System.out.println(Constants.ADD_SUCCESS);    	
//            output.println(Constants.ADD_SUCCESS);
//        } else {
//            //System.out.println(resultMap.get(Constants.EXCEPTION));
//            output.println(resultMap.get(Constants.EXCEPTION));
//        }      
//    }
//
//    /**
//     * To get project start date
//     * return dob
//     *        provides the employee dob
//     */
//    public String getStartDate() throws IOException {
//	boolean flag = true;
//	String startDate = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_START_DATE);
//            startDate = reader.readLine();
//            if(projectController.validateDate(startDate)) {
//		flag = false;
//            } 
//	}
//	return startDate;
//    }
//
//    /**
//     * To get project estimate end date
//     * return dob
//     *        provides the employee dob
//     */
//    public String getEstimateEndDate() throws IOException {
//	boolean flag = true;
//	String estimateEndDate = null;
//	while(flag) {
//            System.out.println(Constants.ENTER_END_DATE);
//            estimateEndDate = reader.readLine();
//            if(projectController.validateDate(estimateEndDate)) {
//		flag = false;
//            } 
//	}
//	return estimateEndDate;
//    }
//
//    /**
//     * To delete the Project       
//     * @throws ServletException 
//     */
//    public void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//       // System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = request.getParameter("id");
//        int id = isValidNumber(projectId);
//        PrintWriter output =response.getWriter();
//     	if(projectController.isProjectExist(id)) {
//            Map<String, String> resultMap = projectController.deleteProject(id);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//            	output.println(Constants.DELETE_SUCCESS);
//            } else {
//                //System.out.println(resultMap.get(Constants.EXCEPTION));
//            	output.println(resultMap.get(Constants.EXCEPTION));
//            }
//        } else {
//	    //System.out.println(Constants.PROJECT_NOT_EXIST);
//        	output.println(Constants.PROJECT_NOT_EXIST);
//    	}
//     	RequestDispatcher requetsDispatcher =request.getRequestDispatcher("/Project.html");
//     	requetsDispatcher.forward(request, response);
//    }
//	
//    /**
//     * To retrieve the Project  
//     */
//    public void retrieveProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    	PrintWriter output =response.getWriter();
//	List<Project> deletedProjects = projectController.getDeletedProjects();
//	for (Project tempProjectList : deletedProjects){
//		output.println(tempProjectList);
//		output.println();
//        } 
//	if(0 < deletedProjects.size()) {
//	   // System.out.println(Constants.ENTER_PROJECT_ID);
//            String  projectId = reader.readLine();
//            int id = isValidNumber(projectId);
//            Map<String, String> resultMap =  projectController.retrieveProject(id);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.DELETE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }
//	} else {
//            System.out.println(Constants.PROJECT_NOT_EXIST );	
//	}
//    }
//
//    /**
//     * To select the field to update Project details
//     */
//    public void selectFieldForUpdation() throws IOException {
//        System.out.println(Constants.ENTER_FIELD_FOR_UPDATE);
//        System.out.println(Constants.FIELD_FOR_PROJECT_UPDATE);
//        String choice = reader.readLine();
//        int fieldOption = isValidNumber(choice);
//        switch (fieldOption) {
//            case 1:
//                updateTitle();
//                break;
//            case 2:
//                updateEstimateEndDate();
//                break;
//            case 3:
//                updateBudget();
//                break;
//            case 4:
//                updateClient();
//                break;
//            case 5:
//                updateStatus();
//                break;
//        }
//    }
//
//    /**
//     * To update the title of the project    
//     */
//    public void updateTitle() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = reader.readLine();
//        int id = isValidNumber(projectId);
//	if(projectController.isProjectExist(id)) {
//            System.out.println(Constants.ENTER_TITLE);
//            String title = reader.readLine();
//            Map<String, String> resultMap = projectController.updateTitle(id, title);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }  
//	} else {
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//	}		
//    }
//
//    /**
//     * To update the  Estimate end date of the project       
//     */
//    public void updateEstimateEndDate() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = reader.readLine();
//        int id = isValidNumber(projectId);
//	if(projectController.isProjectExist(id)) {
//            String estimateEndDateString = getEstimateEndDate();
//            Map<String, String> resultMap =  projectController.updateEstimateEndDate(id, estimateEndDateString);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }   
//        } else {
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//	}			
//    }
//
//    /**
//     * To update the budget of the project      
//     */
//    public void updateBudget() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = reader.readLine();
//        int id = isValidNumber(projectId);
//	if(projectController.isProjectExist(id)) {
//            System.out.println(Constants.ENTER_BUDGET);
//            String budget = reader.readLine();
//            int budgetInt = isValidNumber(budget);
//            Map<String, String> resultMap =  projectController.updateBudget(id, Long.valueOf(budgetInt));	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }   
//	} else {
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//	}		
//    }
//
//    /** 
//     * To update the client of the project 
//     */
//    public void updateClient() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = reader.readLine();
//        int id = isValidNumber(projectId);
//	if(projectController.isProjectExist(id)) {
//            System.out.println(Constants.ENTER_CLIENT);
//            String client = reader.readLine();
//            Map<String, String> resultMap =  projectController.updateClient(id, client);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }       
//	} else {  
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//	}			
//    }
//
//    /**
//     * To update the  status of the project     
//     */
//    public void updateStatus() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = reader.readLine();
//        int id = isValidNumber(projectId);
//	if(projectController.isProjectExist(id)) {
//            System.out.println(Constants.ENTER_STATUS);
//            String status = reader.readLine();
//            Map<String, String> resultMap =  projectController.updateStatus(id, status);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }         
//	} else {
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//	}			
//    }
//
//    /**
//     * To display the employee
//     */
//    public void selectChoiceForDisplay() throws IOException {
//        System.out.println(Constants.FIELDS_FOR_DISPLAY_PROJECT);
//        int choice = Integer.parseInt(reader.readLine());
//        switch (choice) {
//            case 1:
//                displayAllProject();
//                break;
//            case 2:
//                displaySingleProject();
//                break;
//        }
//    }
//
//    /**
//     * To assign employee for project       
//     */
//    public void assignEmployee() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String id = reader.readLine();
//        int projectId = isValidNumber(id); 
//	if(projectController.isProjectExist(projectId)) {
//	    System.out.println(Constants.ENTER_EMPLOYEE_ID);
//            id = reader.readLine();
//            int employeeId = isValidNumber(id);
//            Map<String, String> resultMap = projectController.assignEmployee(employeeId, projectId);	
//            boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//            if(result) {
//                System.out.println(Constants.UPDATE_SUCCESS);
//            } else {
//                System.out.println(resultMap.get(Constants.EXCEPTION));
//            }  
//	} else {
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//	}	
//    }
//	
//    /**
//     * To Unassign employee for project    
//     */
//    public void unassignEmployee() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String id = reader.readLine();
//        int projectId = isValidNumber(id); 
//	if(projectController.isProjectExist(projectId)) {
//	    Project project = projectController.getProjectByProjectId(projectId);
//	    List<Employee> employeeList = project.getEmployeeList();
//	    if(0 < employeeList.size()) {			 
//		for (Employee tempEmployeeList : employeeList){
//		    System.out.println(tempEmployeeList);
//		    System.out.println();
//		} 
//		System.out.println(Constants.ENTER_EMPLOYEE_ID);
//                id = reader.readLine();
//                int employeeId = isValidNumber(id);
//                Map<String, String> resultMap = projectController.unassignEmployee(employeeId, projectId);	
//                boolean result = Boolean.valueOf(resultMap.get(Constants.BOOLEAN));
//                if(result) {
//                    System.out.println(Constants.UPDATE_SUCCESS);
//                } else {
//                    System.out.println(resultMap.get(Constants.EXCEPTION));
//                }  			
//	    } else {
//		System.out.println(Constants.NO_EMPLOYEES);
//	    }
//	} else {
//	    System.out.println(Constants.PROJECT_NOT_EXIST);
//        }
//    }
//
//    /**
//     * To display all project
//     */
//    public void displayAllProject() throws IOException {   
//        List<Project> projectList = projectController.getProjectDetails();
//        for (Project tempProjectList : projectList) {
//            System.out.println(tempProjectList);
//	    System.out.println();
//        } 
//    }
//
//   /**
//    * To display single project
//    */
//    public void displaySingleProject() throws IOException {
//        System.out.println(Constants.ENTER_PROJECT_ID);
//        String  projectId = reader.readLine();
//        int id = isValidNumber(projectId);
//        Project project = projectController.getProjectByProjectId(id);
//	List<Employee> employeeList = project.getEmployeeList();
//        if(null != project) {			
//	    System.out.println(project);   
//	    for (Employee tempEmployeeList : employeeList) {
//	        System.out.println(tempEmployeeList);
//  	        System.out.println();
//	    }  
//        } else {
//            System.out.println(Constants.PROJECT_NOT_EXIST);
//        }
//    }
//
//   /**
//    * To close the session factory
//    */
//    public void closeSessionFactory() {
//        projectController.closeSessionFactory();
//    }
//}