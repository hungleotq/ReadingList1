package readinglist.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@Data
@ConfigurationProperties (prefix = "spring.datasource")
public class DBConfig {

	String driverClassName;
	String username;
	String password;
	String url;
	
	@Profile("dev")
	@Bean
	public DataSource dataSourceInDev() {
		log.info("dataSourceInDev {} {} {} {}", driverClassName, username, password, url);
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClassName);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		dataSourceBuilder.url(url);
		return dataSourceBuilder.build();
	}
	
	@Profile("prod")
	@Bean
	public DataSource dataSourceInProd() {
		log.info("dataSourceInProd {} {} {} {}", driverClassName, username, password, url);
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClassName);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		dataSourceBuilder.url(url);
		return dataSourceBuilder.build();
	}
}
