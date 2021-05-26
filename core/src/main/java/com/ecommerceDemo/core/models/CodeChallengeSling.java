package com.ecommerceDemo.core.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.REQUIRED)
public class CodeChallengeSling {

    private static final Logger LOG = LoggerFactory.getLogger(CodeChallengeSling.class);


	@SlingObject
	SlingHttpServletRequest slingHttpServletRequest;

	@ValueMapValue
	@Default(values = "sgzoid")
	private String name;

	@ValueMapValue
	@Default(booleanValues = false)
	private boolean gender;

	@ValueMapValue
	private List<String> books;

	@ValueMapValue
	private String selectit;

	public String getName() {
        LOG.info(name);
		return name;
	}

	public boolean getGender() {
		return gender;
	}

	public List<String> getBooks() {
		if (books != null) {
			return new LinkedList<>(books);
		} else {
			return Collections.emptyList();
		}
	}

	public String getGenre() {
		return selectit;
	}
}