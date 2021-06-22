package com.mindtree.orchbook.core.models;

import javax.servlet.http.HttpSession;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = SlingHttpServletRequest.class)
public class SigninModel {
	@Self
	SlingHttpServletRequest request;

	public String getSessionAttribute() {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("user_email");
	}
}