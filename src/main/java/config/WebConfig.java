package config;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@ComponentScan (basePackages = {"mapper", "persistence","service","controller","validator","dto"})
public class WebConfig implements WebMvcConfigurer{

	 @Bean(destroyMethod = "close")
		public DataSource mysqlDataSource() {
			ResourceBundle bundle = ResourceBundle.getBundle("db");
			HikariConfig config = new HikariConfig();
			
			config.setJdbcUrl(bundle.getString("jdbcUrl"));
			config.setDriverClassName(bundle.getString("DriverClassName"));
			config.setUsername(bundle.getString("dataSource.user"));
			config.setPassword(bundle.getString("dataSource.password"));
			
			return new HikariDataSource(config);
		}

		@Bean
		public DataSourceTransactionManager getTransactionManager(DataSource mysqlDataSource) {
			return new DataSourceTransactionManager(mysqlDataSource);
	}
	    @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver bean = 
	          new InternalResourceViewResolver();
	        bean.setPrefix("/WEB-INF/view/");
	        bean.setSuffix(".jsp");
	        return bean;
	    }
	    
	    @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	 	   registry.addResourceHandler("/static/**").addResourceLocations("/static/theme/");

	    }
	 	  @Bean("messageSource")
	 	   public MessageSource messageSource() {
	 	      ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
	 	      messageSource.setBasename("classpath:locale/messages");
	 	      messageSource.setDefaultEncoding("ISO-8859-1");
	 	      messageSource.setUseCodeAsDefaultMessage(true);
	 	      return messageSource;
	 	   }

	 	   @Bean
	 	   public LocaleResolver localeResolver() {
	 	      CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	 	      return localeResolver;
	 	   }

	 	   @Override
	 	   public void addInterceptors(InterceptorRegistry registry) {
	 	      LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	 	      localeChangeInterceptor.setParamName("lang");
	 	      registry.addInterceptor(localeChangeInterceptor);
	 	   }
	
	 	}
