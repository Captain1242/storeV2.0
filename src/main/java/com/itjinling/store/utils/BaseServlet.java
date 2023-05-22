package com.itjinling.store.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 通用的Servlet的编写
 * */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//http://localhost:8080/store_2.0/UserServlet?method=regist
		//接收参数
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String methodName = req.getParameter("method");
		if(methodName == null || "".equals(methodName)){
			resp.getWriter().println("method参数为null!!!");
			return;
		}
		//获得子类的class对象
		Class clazz = this.getClass();
		//获得子类中的方法
		try {
			Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//使方法执行
			String path = (String) method.invoke(this, req,resp);
			if(path != null){
				req.getRequestDispatcher(path).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
