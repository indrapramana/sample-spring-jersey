package id.web.resource;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import id.web.bo.CustomerBo;
import id.web.service.Customer;

@Path("customer")
@Component
public class CustomerResource {
	
	@Autowired
	private CustomerBo customerBo;
	
	private static final Logger logger = Logger
			.getLogger(CustomerResource.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllCustomers() {
		logger.info("Retrieve all customers");
		List<Customer> customers = customerBo.findAllCustomers();
		return Response.status(Response.Status.OK).entity(customers).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCustomer(@PathParam("id") int id) {
		logger.info("Fetching Customer with id " + id);
		Customer customer = customerBo.findByCustomerId(id);
		
		if (customer == null) {
			logger.info("Customer with id " + id);
			return Response.status(Response.Status.NOT_FOUND).entity(null).build();
		}
		return Response.status(Response.Status.OK).entity(customer).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer cust) {
		logger.info("Creating Customer " + cust.getFullName());
		Customer customer = new Customer(
				cust.getCustId(), cust.getFullName(), 
				cust.getAddress(), cust.getEmail());
		
		try {
			customerBo.insert(customer);
			return Response.status(Response.Status.CREATED)
					.entity(customer).build();
		} catch (Exception e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(customer).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Customer cust) {
		logger.info("Updating Customer " + cust.getCustId());
		Customer customer = customerBo.findByCustomerId(cust.getCustId());
		
		if (customer == null) {
			logger.info("Customer with id " + cust.getCustId() + " not found");
			return Response.status(Response.Status.NOT_FOUND).entity(null).build();
		}
		
		customerBo.update(customer);
		return Response.status(Response.Status.OK).entity(cust).build();
	}

}
