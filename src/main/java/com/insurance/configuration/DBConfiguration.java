package com.insurance.configuration;

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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Configuration  :: 지정된 클래스를 자바 기반의 설정 파일로 인식 
@Configuration
//@PropertySource :: 해당 클래스에서 참조할 properties 파일 위치 지정 
@PropertySource("classpath:/application.properties")
//데이터베이스 구성 클래스
public class DBConfiguration
{
	// @Autowired :: Bean으로 등록된 객체를 클래스에 주입할 때 사용                                             
	@Autowired
	// ApplicationContext :: 스프링 컨테이너 (Bean의 생명주기 등을 관리)
	private ApplicationContext applicationContext;
	
	// @Bean :: 객체
	@Bean
	// @ConfigurationProperties :: 접두사(prefix) 지정, 'spring.datasource.hikari'로 시작하는 설정을 메서드에 매핑
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	// HikariConfig :: Connection Pool 라이브러리
	public HikariConfig hikariConfig()
	{
		return new HikariConfig();
    }
	
	@Bean
	// DataSource :: 데이터 소스 객체 생성 (순수 JDBC 실행 ↔ 리소스 절약)
	public DataSource dataSource()
	{
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	// SqlSession :: 데이터베이스 커넥션과 SQL실행을 제어하는 객체 생성
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setDataSource(dataSource());
		// setMapperLocations  ::  XML Mapper에 인식하도록 하는 역할
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		// setTypeAliasesPackage  ::  클래스 패키지 경로 지정
		factoryBean.setTypeAliasesPackage("com.insurance.*");
		// setConfiguration  ::  Bean을 설정 파일로 지정
		// mybatisConfg  ::  application.properties 에서 mybatis.configuration 으로 시작하는 모든 설정을 읽어 Bean 으로 등록
		factoryBean.setConfiguration(mybatisConfg());

		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception
	{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfg()
	{
		return new org.apache.ibatis.session.Configuration();
	}
}
