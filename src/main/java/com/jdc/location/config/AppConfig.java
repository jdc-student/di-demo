package com.jdc.location.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan(basePackages = "com.jdc.location.dao")
@PropertySource("database.properties")
public class AppConfig {

	@Bean
	DataSource dataSource(
			@Value("${db.url}") String url,
			@Value("${db.user}") String user,
			@Value("${db.password}") String password
			)
	{
		var ds = new MysqlDataSource();
		ds.setUrl(url);
		ds.setUser(user);
		ds.setPassword(password);
		
		return ds;
	}
}
