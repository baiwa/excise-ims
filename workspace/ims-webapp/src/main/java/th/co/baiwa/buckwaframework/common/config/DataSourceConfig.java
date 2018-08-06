package th.co.baiwa.buckwaframework.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.repository.support.CommonSimpleJpaRepository;

@Configuration
@EnableJpaRepositories(
	basePackages = {
		"th.co.baiwa.buckwaframework",
		"th.go.excise.ims",
		"th.co.baiwa.excise"
	},
	repositoryBaseClass = CommonSimpleJpaRepository.class
)
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Autowired
	private DataSource dataSource;
	
	// FIXME Change to commonJdbcTemplate instead
	@Bean(name = "commonJdbcDao")
	public CommonJdbcDao commonJdbcDao(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new CommonJdbcDao(jdbcTemplate);
	}
	
	@Bean(name = "commonJdbcTemplate")
	public CommonJdbcTemplate commonJdbcTemplate(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new CommonJdbcTemplate(jdbcTemplate);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan(
    		"th.co.baiwa.buckwaframework",
    		"th.go.excise.ims",
    		"th.co.baiwa.excise"
	    );
	    factory.setDataSource(dataSource);
	    
	    return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setDataSource(dataSource);
		return txManager;
	}
	
}
