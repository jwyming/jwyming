package com.jwyming.common.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class DBConfig {

	@Bean
//	@Profile("production")
	public DataSource jndiDataSource() throws Exception{
		JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		DataSource dataSource = dsLookup.getDataSource("jdbc/babyDS");
		dataSource.getConnection().setAutoCommit(false);
		return dataSource;
	}

}
