package th.co.baiwa.buckwaframework.common.config;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import springfox.documentation.spring.web.json.Json;
import th.co.baiwa.starter.thymeleaf.stsm.web.conversion.DateFormatter;
import th.co.baiwa.starter.thymeleaf.stsm.web.conversion.VarietyFormatter;

@Configuration
@EnableWebMvc
@ComponentScan(
	useDefaultFilters = false,
	basePackages = {
		"th.co.baiwa.buckwaframework",
		"th.co.baiwa.starter"
	},
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.ANNOTATION,
			value = {
				Controller.class,
				RestController.class
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
@Import({
	ThymeleafConfig.class
})
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Configure from WebMvcAutoConfiguration.addResourceHandlers()
		registry.addResourceHandler("/**").addResourceLocations(new String[] {
			"classpath:/META-INF/resources/",
			"classpath:/resources/",
			"classpath:/static/",
			"classpath:/public/",
			"/"
		});
		// For Thymeleaf
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		// For Swagger2
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		logger.info("Multipart Resolver");

		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
		
		return resolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Gson gson = new GsonBuilder()
			.setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss")
			.serializeNulls()
			.registerTypeAdapter(Json.class, new JsonSerializer<Json>() {
				@Override
				public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
					final JsonParser parser = new JsonParser();
					return parser.parse(src.value());
				}
			})
			.create();

		GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
		gsonConverter.setGson(gson);
		
		converters.add(gsonConverter);
	}
	
	// Thymeleaf Example
	
	/*
	 *  Message externalization/internationalization
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	/*
	 * Add formatter for class {@link
	 * thymeleafexamples.stsm.business.entities.Variety} and {@link
	 * java.util.Date} in addition to the one registered by default
	 */
	@Override
	public void addFormatters(final FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addFormatter(varietyFormatter());
		registry.addFormatter(dateFormatter());
	}

	@Bean
	public VarietyFormatter varietyFormatter() {
		return new VarietyFormatter();
	}

	@Bean
	public DateFormatter dateFormatter() {
		return new DateFormatter();
	}
	
}
