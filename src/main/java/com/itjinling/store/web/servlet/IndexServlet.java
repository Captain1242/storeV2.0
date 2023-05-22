package com.itjinling.store.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itjinling.store.utils.BaseServlet;

/**
 * 首页的Servlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String index(HttpServletRequest req,HttpServletResponse resp){
		
		return "/jsp/index.jsp";
	}
}
