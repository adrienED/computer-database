package config;
	
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = {"java"}) // Scans the following packages for classes with @Controller annotations
	public class DispatcherConfig extends WebMvcConfigurerAdapter {
	    @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

	    @Bean
	    public ViewResolver getViewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/view/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;
	    }
	}

