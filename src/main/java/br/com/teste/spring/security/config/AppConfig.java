package br.com.teste.spring.security.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@EnableWebMvc
@EnableSwagger
@EnableAspectJAutoProxy
@Configuration
@ComponentScan({ "br.com.teste.spring.security.controller",
		"br.com.teste.spring.security.rest",
		"br.com.teste.spring.security.business"})
@Import({ SecurityConfig.class })
@PropertySource({ "classpath:cred.properties"})
public class AppConfig extends WebMvcConfigurerAdapter {
	private static final String POINTCUT_EXECUTION_REPOSITORY = "execution(public * br.com.teste.spring.security.business.*.*.*(..))";
	private static final String EXCEPTION_MESSAGE = "Exception thrown: "
			+ CustomizableTraceInterceptor.PLACEHOLDER_EXCEPTION;
	private static final String EXIT_METHOD_MESSAGE = "Exiting method: "
			+ CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME+"."
			+ CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME
			+ " having return value "
			+ CustomizableTraceInterceptor.PLACEHOLDER_RETURN_VALUE
			+ ", execution time: "
			+ CustomizableTraceInterceptor.PLACEHOLDER_INVOCATION_TIME + " ms";
	private static final String ENTER_METHOD_MESSAGE = "Entering method: "
			+ CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME+"."
			+ CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME + "("
			+ CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENTS + ")";
	
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public CustomizableTraceInterceptor interceptor() {
		CustomizableTraceInterceptor interceptor = new CustomizableTraceInterceptor();
		interceptor.setEnterMessage(ENTER_METHOD_MESSAGE);
		interceptor.setExceptionMessage(EXCEPTION_MESSAGE);
		interceptor.setExitMessage(EXIT_METHOD_MESSAGE);
		return interceptor;
	}

	@Bean
	public Advisor traceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(POINTCUT_EXECUTION_REPOSITORY);
		return new DefaultPointcutAdvisor(pointcut, interceptor());
	}
	
	
	   @Autowired
	   public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
	      this.springSwaggerConfig = springSwaggerConfig;
	   }

	   @Bean //Don't forget the @Bean annotation
	   public SwaggerSpringMvcPlugin customImplementation(){
	      return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
	            .apiInfo(apiInfo());
	            //.includePatterns(".*pet.*");
	   }

	    private ApiInfo apiInfo() {
	      ApiInfo apiInfo = new ApiInfo(
	              "My Apps API Title",
	              "My Apps API Description",
	              "My Apps API terms of service",
	              "My Apps API Contact Email",
	              "My Apps API Licence Type",
	              "My Apps API License URL"
	        );
	      return apiInfo;
	    }

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
		driverManagerDataSource.setUsername("med");
		driverManagerDataSource.setPassword("med");
		return driverManagerDataSource;
	}
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix("");
		return viewResolver;
	}

}