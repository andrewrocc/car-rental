package infrastructure.servlet;

import infrastructure.config.RepositoryConfig;
import infrastructure.config.ServiceConfig;
import infrastructure.config.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class SpringContextServletInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext ctx) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class, RepositoryConfig.class, ServiceConfig.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

		final ServletRegistration.Dynamic servletRegistration = ctx.addServlet("dispatcherServlet", dispatcherServlet);
		servletRegistration.setLoadOnStartup(1);
		servletRegistration.addMapping("*.html");
		servletRegistration.addMapping("*.jpg");
	}
}
