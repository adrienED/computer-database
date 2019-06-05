package com.excilys.CDB.persistence.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.excilys.CDB.persistence.repository")
public class PersistenceConfig {

}