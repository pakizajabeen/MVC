package com.luv2code.springdemo.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller 
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject the DAO and later customer service
	
	@Autowired
	private CustomerService customerService;;
	
	
	@GetMapping("/list")
	public String listCustomer(Model model)
	{
		// get customers from DAO
			
		List<Customer> customers = customerService.getCustomers();
		
		
		// add the customers to model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showForm(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
	
	return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute ("customer") Customer customer)
	{
		
		customerService.saveCustomer(customer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateForm(@RequestParam("customerId") int id, Model model) {
		
		Customer customer = customerService.getCustomer(id);
		
		model.addAttribute("customer", customer); 
		
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteLink(@RequestParam("customerId") int id)
	{
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("name") String name, Model model)
	{
		List <Customer> customer =customerService.searchCustomer(name);
		
		model.addAttribute("customer", customer);
		
		return "list-customers";
	}
	
}

