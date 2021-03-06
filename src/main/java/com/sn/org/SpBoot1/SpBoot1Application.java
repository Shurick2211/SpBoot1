package com.sn.org.SpBoot1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication

public class SpBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpBoot1Application.class, args);


	}
	@Bean
	public FilterRegistrationBean hiddenHttpMethodFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		return filterRegistrationBean;
	}
	@Value("${DB_URL}")
	private  String DB_URL ;
	@Value("${USER}")
	private  String USER;
	@Value("${PASS}")
	private  String PASS;
	@Bean
	public DataSource dataSource(){

		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASS);
		return dataSource;
	}
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
}
