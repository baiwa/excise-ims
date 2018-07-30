package th.co.baiwa.buckwaframework.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
	useDefaultFilters = false,
	basePackages = {
		"th.co.baiwa.buckwaframework",
		"th.go.excise.ims"
	},
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.ANNOTATION,
			value = {
				Service.class,
				Repository.class,
				Component.class
			}
		)
	},
	excludeFilters = {
		@ComponentScan.Filter(
			type = FilterType.ANNOTATION,
			value = Configuration.class
		)
	}
)
@PropertySource(value = {
	"classpath:/application.properties"
})
public class AppConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		logger.info("placeHolderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}
