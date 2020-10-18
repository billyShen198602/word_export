package com.xinyin.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author billy
 * 数据源
 */
@Configuration
@MapperScan(basePackages = "com.xinyin.mapper", sqlSessionFactoryRef = "DBDataSqlSessionFactory")
public class DataSourceConfig {
	@Bean(name = "DBDataSource")	
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "DBDataSqlSessionFactory")
	public SqlSessionFactory  sqlSessionFactory(@Qualifier("DBDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:com/xinyin/mapper/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "DBDataTransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("DBDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "DBDataSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("DBDataSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
