package com.ideas2it.employeeManagementSystem.logger;

import org.apache.log4j.Logger;  

/**
 * To log the errors and info in file
 * @author TamilarasiShanmugasundaram
 * created 24-03-2021
 */
public class EmployeeManagementLogger { 
    Logger log = null;

    /**
     * To log the class name in file
     * @param String
     *        provides the classname
     */       
    public void logClassname(String classname) {
        log = Logger.getLogger(classname); 
    }
 
    /**
     * To log the debug in file
     * @param String
     *        provides the exception
     */       
    public void logDebug(String message) {
        log.debug(message);
    }

    /**
     * To log the error in file
     * @param String
     *        provides the exception
     */       
    public void logError(Exception exception) {
        log.error(exception);
    }

    /**
     * To log the fatal in file
     * @param Exception
     *        provides the exception
     */       
    public void logFatal(Exception exception) {
        log.fatal(exception);
    }

    /**	
     * To log the info in file
     * @param String
     *        provides the exception
     */       
    public void logInfo(String message) { 
        log.info(message);
    }

    /**
     * To log the trace in file
     * @param String
     *        provides the exception
     */       
    public void logTrace(String message) {
        log.trace(message);
    }

    /**
     * To log the warning in file
     * @param String
     *        provides the warning
     */       
    public void logWarning(String message) {
        log.warn(message);
    }
}