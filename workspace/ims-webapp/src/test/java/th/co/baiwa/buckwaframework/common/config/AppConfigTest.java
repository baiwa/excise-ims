package th.co.baiwa.buckwaframework.common.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@Configuration
public class AppConfigTest {
	
	@Bean
	@Profile(value = PROFILE.UNITTEST_MOCK)
	public DataSource dataSource() {
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2)
			.addScript("embedded-db/create-db.sql")
			.addScript("embedded-db/insert-data.sql")
			.build();
		return db;
	}
	
}
