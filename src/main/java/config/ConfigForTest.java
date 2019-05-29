package config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "repository")
@ComponentScan(basePackages = { "mapper", "persistence", "service", "controller", "validator","repository" })
public class ConfigForTest {

	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}
	
	
	@Bean
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	 LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	 em.setDataSource(mysqlDataSource());
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
