
package com.excilys.CDB.persistence.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.excilys.CDB.persistence.repository")
@ComponentScan("com.excilys.CDB")
public class PersistenceConfig {

}