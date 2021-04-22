package com.ideas2it.employeeManagementSystem.employee.dao.Impl;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.customException.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employee.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.employee.model.Address;
import com.ideas2it.employeeManagementSystem.employee.model.Employee;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;
import com.ideas2it.employeeManagementSystem.project.model.Project;
import com.ideas2it.employeeManagementSystem.sessionFactory.Sessionfactory;

/**
 * To perform CRUD operation on employee and store thr details in database
 * 
 * @author TamilarasiShanmugasundaram created 23-02-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
	EmployeeManagementLogger employeeManagementLogger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
	Sessionfactory sessionfactory = Sessionfactory.getInstance();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createEmployee(Employee employee) throws EmployeeManagementException {
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
			return true;
		} catch (HibernateException exception) {
			sessionfactory.rollbackTransaction(transaction);
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
	public boolean updateEmployee(Employee employee) throws EmployeeManagementException {
		Session session = null;
		Transaction transaction = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(employee);
			transaction.commit();
			return true;
		} catch (HibernateException exception) {
			sessionfactory.rollbackTransaction(transaction);
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
	public Employee getEmployeeById(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			return (Employee) session.createQuery(Constants.EMPLOYEE_EXIST_QUERY).setParameter(Constants.ID, id).uniqueResult();
		} catch (HibernateException exception) {
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
	public Employee getDeletedEmployeeById(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			return  (Employee) session.createQuery(Constants.GET_DELETED_EMPLOYEE_BY_ID_QUERY).setParameter(Constants.ID, id).uniqueResult();
		} catch (HibernateException exception) {
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
	public List<Employee> getEmployeeDetails() throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery("From Employee");
			List<Employee> list = query.list();
			return list;
		} catch (HibernateException exception) {
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
	public List<Address> getPermanentAddressByEmployeeId(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.GET_PERMANENT_ADDRESS_QUERY);
			query.setParameter(Constants.ID, id);
			List<Address> list = query.list();
			return list;
		} catch (HibernateException exception) {
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
	public List<Address> getTemporaryAddressByEmployeeId(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.GET_TEMPRARORY_ADDRESS_QUERY);
			query.setParameter(Constants.ID, id);
			List<Address> list = query.list();
			return list;
		} catch (HibernateException exception) {
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
	public List<Employee> getDeletedEmployees() throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.GET_DELETED_EMPLOYEE_QUERY);
			List<Employee> list = query.list();
			return list;
		} catch (HibernateException exception) {
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
	public boolean isEmployeeExist(int id) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(Constants.EMPLOYEE_EXIST_QUERY);
			query.setParameter(Constants.ID, id);
			List<Employee> list = query.list();
			return (0 < list.size());
		} catch (HibernateException exception) {
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
	public boolean isEmailExist(String emailId) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(Constants.EMAIL_ID_EXIST_QUERY);
			query.setParameter(Constants.EMAIL_ID, emailId);
			List<Employee> employeeList = query.list();
			return (0 < employeeList.size());
		} catch (HibernateException exception) {
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
	public boolean isPhoneNumberExist(long phoneNumber) throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery(Constants.PHONENUMBER_EXIST_QUERY);
			query.setParameter(Constants.PHONENUMBER_DB, phoneNumber);
			List<Employee> list = query.list();
			return (0 < list.size());
		} catch (HibernateException exception) {
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}
	
	public List<Employee> getEmployees() throws EmployeeManagementException {
		Session session = null;
		try {
			SessionFactory factory = sessionfactory.openSessionFactory();
			session = factory.openSession();
			Query query = session.createQuery("from Employee where is_delete = false");
			List<Employee> list = query.list();
			return list;
		} catch (HibernateException exception) {
			employeeManagementLogger.logError(exception);
			throw new EmployeeManagementException(Constants.EMPLOYEE_MANAGEMENT_EXCEPTION);
		} finally {
			sessionfactory.closeSession(session);
		}
	}
}