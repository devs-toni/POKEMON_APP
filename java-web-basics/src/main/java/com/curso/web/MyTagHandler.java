package com.curso.web;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Servlet implementation class MyTagHandler
 */
public class MyTagHandler extends TagSupport {
	

	public int doStartTag() throws JspException {
		JspWriter out=pageContext.getOut();
		try {
			out.print(Calendar.getInstance().getTime());	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
