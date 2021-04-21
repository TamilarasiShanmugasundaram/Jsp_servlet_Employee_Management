package com.ideas2it.employeeManagementSystem.project.dao.Impl;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;
import com.ideas2it.employeeManagementSystem.project.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.project.model.Project;
import com.ideas2it.employeeManagementSystem.sessionFactory.Sessionfactory;

/**
 * To perform CRUD operation on Project
 * 
 * @author Tamilarasi Shanmugasundaram created 03-02-2021
 */
public class ProjectDaoImpl implements ProjectDao {
	EmployeeManagementLogger employeeManagementLogger = new EmployeeManagementLogger();
	Sessionfactory sessionfactory = Sessionfactory.getInstance();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createProject(Project project) throws EmployeeManagementException {
		Session session = null;
		Transaction transaction = null;
		//try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			transaction = session.beginTransaction();
			int id = (int) session.save(project);
			transaction.commit();
			return true;
//		} catch (HibernateException exception) {
//			sessionfactory.rollbackTransaction(transaction);
//			employeeManagementLogger.logClassname("ProjectDaoImpl");
//			employeeManagementLogger.logError(exception);
//			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
//		} finally {
//			sessionfactory.closeSession(session);
//		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateProject(Project project) throws EmployeeManagementException {
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(project);
			transaction.commit();
			return true;
		} catch (HibernateException exception) {
			sessionfactory.rollbackTransaction(transaction);
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Project getProjectByProjectId(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			//Project project = (Project) session.createQuery(Constants.PROJECT_EXIST_QUERY);
			Project project = (Project) session.createQuery(Constants.PROJECT_EXIST_QUERY)
					    .setParameter(Constants.ID, id)
					    .uniqueResult();
			//query.setParameter(Constants.ID, id);
			//List<Project> project =  query.getResultList();
			return project;
		} catch (HibernateException exception) {
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally { 	 	
			sessionfactory.closeSession(session);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getDeletedProjects() throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.GET_DELETED_PROJECT_QUERY);
			List<Project> list = query.list();
			return list;
		} catch (HibernateException exception) {
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getProjectDetails() throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.PROJECT_SELECT_QUERY);
			List<Project> list = query.list();
			return list;
		} catch (HibernateException exception) {
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isProjectExist(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.PROJECT_EXIST_QUERY);
			query.setParameter(Constants.ID, id);
			List<Project> list = query.list();
			return (0 < list.size());
		} catch (HibernateException exception) {
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	} 

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getDeletedProjectById(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.GET_DELETED_PROJECT_BY_ID_QUERY);
			query.setParameter(Constants.ID, id);
			List<Project> list = query.list();
			return list;
		} catch (HibernateException exception) {
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}
	
	public List<Project> getProjects() throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery("from Project where is_delete = false");
			List<Project> list = query.list();
			return list;
		} catch (HibernateException exception) {
			employeeManagementLogger.logClassname("ProjectDaoImpl");
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeSessionFactory() {
		sessionfactory.closeSessionFactory();
	}
}