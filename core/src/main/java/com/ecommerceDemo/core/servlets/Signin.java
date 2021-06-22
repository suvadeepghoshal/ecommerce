package com.mindtree.orchbook.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
@SlingServletPaths(value = { "/bin/signin" })
public class Signin extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 6821868195401270237L;

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String data = br.readLine();
		String[] credentials = data.split(",");
		String email = credentials[0];
		String pass = credentials[1];
		HttpSession session = null;
		if (email.equals("sgzoid97@gmail.com") && pass.equals("Argha@2021")) {
			/* out.println("Checking for session"); */
			session = request.getSession();
			if (session != null) {
				session.setAttribute("user_email", email);
				out.println("http://localhost:4502/content/Orch-Book/language-masters/en/test/user.html");
			}
		} else {
			if (session == null)
				out.println("http://localhost:4502/content/Orch-Book/language-masters/en/test.html");
		}
		out.close();
	}
}
