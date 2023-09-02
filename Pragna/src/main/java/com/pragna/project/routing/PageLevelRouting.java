package com.pragna.project.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageLevelRouting {

	
	
	@RequestMapping("/page/*")
	public String pageDisplay()
	{
		return "pageDisplay.html";
	}
	
	@RequestMapping("/adminPage")
	public String adminPage()
	{
		return "adminPage.html";
	}
	
	@RequestMapping("/admin")
	public String admin()
	{
		return "admin.html";
	}
	
	@RequestMapping("/adminPageRoting/*")
	public String adminPageRouting()
	{
		return "admin_routing.html";
	}
	
	@RequestMapping("/loginRegister/*")
	public String loginRegister()
	{
		return "login_register.html";
	}
	
	@RequestMapping("/loginRegister")
	public String loginRegisterWithMail()
	{
		return "login_register.html";
	}
	
	@RequestMapping("/add_child/*")
	public String addChildern()
	{
		return "add_children.html";
	}
}
