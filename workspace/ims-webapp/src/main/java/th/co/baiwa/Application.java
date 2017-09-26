package th.co.baiwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {
	HibernateJpaAutoConfiguration.class,
	ThymeleafAutoConfiguration.class
})
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
//	private void configListener(ServletContext servletContext) {
//		// HttpSessionEventPublisher for checking duplicate login
//		//servletContext.addListener(HttpSessionEventPublisher.class);
//	}
	
}