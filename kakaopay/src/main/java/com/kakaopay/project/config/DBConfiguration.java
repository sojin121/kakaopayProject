package com.kakaopay.project.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.yml")
public class DBConfiguration {

	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(dataSource());
//		return factoryBean.getObject();
//	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		 
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(resolver.getResources("classpath:/mappers/*.xml"));
		factoryBean.setTypeAliasesPackage("com.kakaopay.project.model");
		factoryBean.setConfiguration(mybatisConfg());
		
		System.out.println("sqlSessionFactory!!!!");
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        // underscore를 camelCase로 매칭 : 예) user_id -> userId
        sqlSessionTemplate.getConfiguration().setMapUnderscoreToCamelCase(true);
        // Insert시 생성되는 pk를 bean으로 반환
        sqlSessionTemplate.getConfiguration().setUseGeneratedKeys(true);
        
        System.out.println("sqlSession!!!!");
        return sqlSessionTemplate;
	}

	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfg() {
		return new org.apache.ibatis.session.Configuration();
	}
}
