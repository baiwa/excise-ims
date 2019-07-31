package th.go.excise.ims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

import th.go.excise.ims.scheduler.service.MainScheduleService;

@SpringBootApplication(
	scanBasePackages = {
		"th.co.baiwa.buckwaframework",
		"th.go.excise.ims"
	},
	exclude = {
		ThymeleafAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		JmxAutoConfiguration.class
	}
)
@PropertySource(value = {
	"file:application.properties"
})
public class Application implements CommandLineRunner {
	
	@Autowired
	private MainScheduleService mainScheduleService;
	
	public static void main(String[] args) throws Exception {
        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
	
	public void run(String... args) throws Exception {
		mainScheduleService.run(args);
		System.exit(0);
	}
	
}