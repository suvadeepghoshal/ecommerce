package com.mindtree.orchbook.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/signout" })
public class Signout extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 3367153342770083834L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try (PrintWriter out = response.getWriter();) {
			session.invalidate();
			out.println("Signed out");
			response.sendRedirect("/content/Orch-Book/language-masters/en/test.html");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
