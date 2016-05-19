package com.action;
/**
 * Created by 米泽双 on 2015/12/13.
 */
import com.dao.AdminDao;
import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class GoLogin extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String Type;
	private String Username;
	private String Password;
	private String Msg;

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	//处理用户请求的execute方法
	public String execute() throws Exception {

		if(Type.equals("系统管理员")) {
			if (null == new AdminDao().CheckLogin(Username, Password)) {
				Msg = "用户名或者密码错误";
				return INPUT;
			}
			else {

				//获取ID
				String Admin_ID = new AdminDao().CheckLogin(Username, Password);
				//创建session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Admin_ID);
				session.setAttribute("type", "1");
				return SUCCESS;
			}
		}
		else if(Type.equals("学生"))
		{
			if (null == new StudentDao().CheckLogin(Username, Password)) {
				Msg = "用户名或者密码错误";
				return INPUT;
			}
			else
			{
				//获取ID
				String Student_ID=new StudentDao().CheckLogin(Username, Password);
				//创建session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Student_ID);
				session.setAttribute("type", "3");
				return SUCCESS;
			}
		}
		else
		{
			Msg = "身份类型错误";
			return INPUT;
		}
		
	}
}