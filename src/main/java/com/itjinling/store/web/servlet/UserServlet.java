package com.itjinling.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.itjinling.store.domain.User;
import com.itjinling.store.service.UserService;
import com.itjinling.store.service.impl.UserServiceImpl;
import com.itjinling.store.utils.BaseServlet;
import com.itjinling.store.utils.MyDateConverter;

/**
 * 用户模块的Servlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 跳转到注册页面的执行方法：registUI
	 * */
	
	public String registUI(HttpServletRequest req,HttpServletResponse resp){
		
		return "/jsp/regist.jsp";
	}
	
	
	/**
	 * 异步校验用户名的执行的方法: checkUsername
	 */
	public String checkUsername(HttpServletRequest req,HttpServletResponse resp){
		try{
			// 接收参数:
			String username = req.getParameter("username");
			// 调用业务层:
			UserService userService = new UserServiceImpl();
			User existUser = userService.findByUsername(username);
			if(existUser == null){
				// 用户名可以使用:
				resp.getWriter().println("1");
			}else{
				// 用户名已经存在:
				resp.getWriter().println("2");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		return null;
	}
	
	
	
	

	/**
	 * 用户注册的执行的方法:regist
	 */
	public String regist(HttpServletRequest req,HttpServletResponse resp){
		try{
			// 接收数据:
			Map<String,String[]> map = req.getParameterMap();
			// 封装数据:
			User user = new User();
			ConvertUtils.register(new MyDateConverter(), Date.class);
			BeanUtils.populate(user, map);
			// 调用业务层:
			UserService userService = new UserServiceImpl();
			userService.save(user);
			// 页面跳转:
			req.setAttribute("msg", "注册成功！请去邮箱激活!");
			return "/jsp/msg.jsp";
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	
	
	
	/**
	 * 用户激活的方法:
	 * @param req
	 * @param resp
	 * @return
	 */
	/*public String active(HttpServletRequest req,HttpServletResponse resp){
		// 接收激活码：
		String code = req.getParameter("code");
		// 根据激活码查询:
		UserService userService = new UserServiceImpl();
		User existUser;
		try {
			existUser = userService.findByCode(code);
			// 判断
			if(existUser == null){
				// 激活码有误:
				req.setAttribute("msg", "激活码错误！请重新激活！");
			}else{
				// 激活操作:
				existUser.setState(2); // 设置激活的状态为已经激活.
				existUser.setCode(null);
				userService.update(existUser);
				req.setAttribute("msg", "激活成功！请去登录！");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/jsp/msg.jsp";
	}*/
	
	/**
	 * 跳转到登录页面的执行的方法:loginUI
	 */
	public String loginUI(HttpServletRequest req,HttpServletResponse resp){
		return "/jsp/login.jsp";
	}
	
	/**
	 * 用户登录的执行的方法:login
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp){
		try{
			// 接收参数:
			Map<String,String[]> map = req.getParameterMap();
			// 封装数据：
			User user = new User();
			BeanUtils.populate(user, map);
			// 调用业务层:
			UserService userService = new UserServiceImpl();
			User existUser = userService.login(user);
			if(existUser == null){
				req.setAttribute("msg", "用户名或密码或用户未激活!");
				return "/jsp/login.jsp";
			}else{
				req.getSession().setAttribute("existUser", existUser);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				return null;
			}
			// 页面跳转
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * 用户退出功能的方法:logOut
	 * @param req
	 * @param resp
	 * @return
	 */
	public String logOut(HttpServletRequest req,HttpServletResponse resp){
		req.getSession().invalidate();
		try {
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}


