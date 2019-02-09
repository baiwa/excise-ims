package th.co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
	scanBasePackages = {
		"co.th.baiwa.buckwaframework",
		"go.excise.ims"
	},
	exclude = {
//		HibernateJpaAutoConfiguration.class,
		QuartzAutoConfiguration.class
	}
)
//@EntityScan(basePackages = {"co.th.ims"})
//@EnableJpaRepositories(basePackages = {"co.th.ims"})
//@PropertySource("file:${ecert.config.location}/application.properties")
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	
}