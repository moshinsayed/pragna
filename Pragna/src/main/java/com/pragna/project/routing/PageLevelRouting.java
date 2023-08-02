package com.pragna.project.routing;

import org.springframework.stereotype.Controller;
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
}
