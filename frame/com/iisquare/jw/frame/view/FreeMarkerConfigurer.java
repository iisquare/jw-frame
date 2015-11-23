package com.iisquare.jw.frame.view;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.iisquare.jw.frame.ApplicationConfiguration;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerConfigurer implements InitializingBean, ServletContextAware {
	
	private ApplicationConfiguration applicationConfiguration;
	private ServletContext servletContext;
	private Configuration configuration;
	
	private long templateUpdateDelay = 0;
	private String templateExceptionHandler = "ignore";
	
	public ApplicationConfiguration getapplicationConfiguration() {
		return applicationConfiguration;
	}

	public void setapplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
		this.applicationConfiguration = applicationConfiguration;
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(null != configuration) return;
		configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setServletContextForTemplateLoading(
				servletContext, applicationConfiguration.getTemplateLoaderPath());
		configuration.setDefaultEncoding(applicationConfiguration.getCharacterEncoding());
		configuration.setTemplateUpdateDelayMilliseconds(templateUpdateDelay);
		configuration.setLocale(Locale.forLanguageTag(applicationConfiguration.getLocale()));
		configuration.setDateFormat(applicationConfiguration.getDateTimeFormat());
		switch (templateExceptionHandler) {
		case "debug" :
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
			break;
		case "htmldebug" :
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			break;
		case "rethrow" :
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			break;
		default :
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		}
	}

	public long getTemplateUpdateDelay() {
		return templateUpdateDelay;
	}

	public void setTemplateUpdateDelay(long templateUpdateDelay) {
		this.templateUpdateDelay = templateUpdateDelay;
	}

	public String getTemplateExceptionHandler() {
		return templateExceptionHandler;
	}

	public void setTemplateExceptionHandler(String templateExceptionHandler) {
		this.templateExceptionHandler = templateExceptionHandler;
	}

}
