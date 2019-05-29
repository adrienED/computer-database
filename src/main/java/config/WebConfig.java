package config;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "repository")
@ComponentScan (basePackages = {"mapper","service","controller","validator","dto","repository","model"})
public class WebConfig implements WebMvcConfigurer{

	 @Bean(destroyMethod = "close")
		public DataSource dataSource() {
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
	   
	 	 @Bean
	 	 public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	 	 LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	 	 em.setDataSource(dataSource());
	 	 em.setPackagesToScan("model");
	 	 JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	 	 em.setJpaVendorAdapter(vendorAdapter);
	 	 em.setJpaProperties(additionalProperties());
	 	 return em;
	 	 }
	 	  
	 	 Properties additionalProperties() {
	 		Properties properties = new Properties();
	 		properties.setProperty("hibernate.hbm2ddl.auto", "update");
	 		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	 		properties.setProperty("hibernate.show_sql", "true");
	 		return properties;
	 		}

	 	@Bean
	 	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	 	JpaTransactionManager transactionManager = new JpaTransactionManager();
	 	transactionManager.setEntityManagerFactory(emf);
	 	return transactionManager;
	 	}
	 	}
