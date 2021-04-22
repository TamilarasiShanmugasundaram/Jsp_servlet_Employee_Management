package com.ideas2it.employeeManagementSystem.sessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;   
import org.hibernate.Transaction; 

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.employee.service.Impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;

/**
 * To create session factory
 * @author TamilarasiShanmugasundaram
 * created 05-02-2021
 */
public class Sessionfactory { 
	EmployeeManagementLogger employeeManagementLogger = new EmployeeManagementLogger(Sessionfactory.class);
    private static Sessionfactory sessionFactory = null; 
    private static SessionFactory factory = null;

    /**
     * To create instance for session factory creation class
     */
    public static Sessionfactory getInstance() { 
        if (null == sessionFactory) {
            return sessionFactory = new Sessionfactory();    
        } else {
            return sessionFactory;
        }
    } 

    /**
     * To create session factory
     * return SessionFactory
     *        provides the SessionFactory
     */
    public SessionFactory openSessionFactory() { 
        try { 
            if (null == factory) {
                factory = new Configuration().configure(Constants.CONFIGURATION_FILE_PATH).buildSessionFactory();
            }
            return factory;
        } catch(Exception exception) { 
            employeeManagementLogger.logError(exception);  
            return null; 
        } 
    } 

   /**
    * To close the session factory
    */
    public void closeSessionFactory() {
        if(null != factory) {
            factory.close();
        }
    }

   /**
    * To close the session factory
    * @param session
    *        provides the session object
    */
    public void closeSession(Session session) {
        if(null != session) {
            session.close();
        }
    }

   /**
    * To close the session factory
    * @param session
    *        provides the session object
    */
    public void rollbackTransaction(Transaction transaction) {
        if(null != transaction) {
            transaction.rollback();
        }
    }
} 