package net.t6.olmedorest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	 @RequestMapping(value= {"/", "index"})
	    public String index(){
	        return "index";
	    }
	  
	    @RequestMapping(value="/administrador")
	    public String admin(){
	        return "administrador";
	    }
	   
	    @RequestMapping(value="/login")
	    public String login(){
	        return "login";
	    }
	   

	

}
