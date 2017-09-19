//package th.co.baiwa.framework.common.config;
//
//import javax.xml.ws.Endpoint;
//
//import org.apache.cxf.Bus;
//import org.apache.cxf.bus.spring.SpringBus;
//import org.apache.cxf.interceptor.LoggingInInterceptor;
//import org.apache.cxf.interceptor.LoggingOutInterceptor;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.apache.cxf.transport.servlet.CXFServlet;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebServiceConfig {
//	
//	@Bean
//	public ServletRegistrationBean cxfServlet() {
//		return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
//	}
//
//	@Bean(name = Bus.DEFAULT_BUS_ID)
//	public SpringBus springBus() {
//		SpringBus bus = new SpringBus();
//		bus.getInInterceptors().add(loggingInInterceptor());
//		bus.getOutInterceptors().add(loggingOutInterceptor());
//		return bus;
//	}
//
//	@Bean
//	public Endpoint endpoint(SpringBus springBus) {
//		EndpointImpl endpoint = new EndpointImpl(springBus, null);
//		endpoint.publish("/Payment");
//		return endpoint;
//	}
//	
////	@Bean
////	public PaymentOrderProvider sayHiProvider(){
////		return new SayHiProvider();
////	}
//
//	@Bean
//	public LoggingInInterceptor loggingInInterceptor() {
//		return new LoggingInInterceptor();
//	}
//
//	@Bean
//	public LoggingOutInterceptor loggingOutInterceptor() {
//		return new LoggingOutInterceptor();
//	}
//	
//}
