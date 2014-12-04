package com.hsx.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@Path("")
public class HomeController {
	
	
	@Get
	public String home(){
		System.out.println(this.hashCode());
		return "home";
	}
	
	@Get("test")
	public String test(){
		return "test";
	}
	
}
