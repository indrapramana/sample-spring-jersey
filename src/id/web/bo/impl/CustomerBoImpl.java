package id.web.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import id.web.bo.CustomerBo;
import id.web.dao.CustomerDao;
import id.web.service.Customer;

public class CustomerBoImpl implements CustomerBo {

	@Autowired
	private CustomerDao custDao;
	
	public CustomerDao getCustDao() {
		return custDao;
	}

	public void setCustDao(CustomerDao custDao) {
		this.custDao = custDao;
	}

	@Override
	public void insert(Customer customer) {
		custDao.insert(customer);
	}

	@Override
	public Customer findByCustomerId(int id) {
		return custDao.findByCustomerId(id);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return custDao.findAllCustomers();
	}

	@Override
	public void update(Customer customer) {
		custDao.update(customer);
	}

	@Override
	public void deleteAll() {
		custDao.deleteAll();
	}

	@Override
	public void delete(int id) {
		custDao.delete(id);
	}

}
