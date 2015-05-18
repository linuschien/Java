package linuschien.spring.batch.admin.boot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ImportResource("classpath*:/org/springframework/batch/admin/web/resources/webapp-config.xml")
public class SpringBatchAdminConfiguration {

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
	TomcatEmbeddedServletContainerFactory embeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
	embeddedServletContainerFactory.setContextPath("/batch");
	return embeddedServletContainerFactory;
    }

    @Bean
    public FilterRegistrationBean shallowEtagHeaderFilter() {
	FilterRegistrationBean filter = new FilterRegistrationBean();
	filter.setFilter(new ShallowEtagHeaderFilter());
	filter.setUrlPatterns(Arrays.asList("/*"));
	return filter;
    }

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter() {
	FilterRegistrationBean filter = new FilterRegistrationBean();
	filter.setFilter(new HiddenHttpMethodFilter());
	filter.setUrlPatterns(Arrays.asList("/*"));
	return filter;
    }

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
	ServletRegistrationBean servlet = new ServletRegistrationBean();
	servlet.setName("batchAdminServlet");
	servlet.setServlet(new DispatcherServlet());
	Map<String, String> initParameters = new HashMap<String, String>();
	initParameters.put("contextConfigLocation", "classpath*:/org/springframework/batch/admin/web/resources/servlet-config.xml");
	servlet.setInitParameters(initParameters);
	servlet.setLoadOnStartup(1);
	servlet.setUrlMappings(Arrays.asList("/*"));
	return servlet;
    }

}
