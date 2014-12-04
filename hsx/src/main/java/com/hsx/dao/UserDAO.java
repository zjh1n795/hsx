package com.hsx.dao;

import com.hsx.entity.User;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
public interface UserDAO {

	String table="user";
	
	String field="id,login_name,pwd,flag,createtime";
	
	@ReturnGeneratedKeys
	@SQL("insert into $table(login_name,pwd,createtime)"
			+ "values(:u.login_name,:u.pwd,0,now())")
	int insert(@SQLParam("u")User u);
	
	@SQL("")
	int gets();
	
	
}
