package th.co.baiwa.buckwaframework.common.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.go.excise.ims.ws.provider.ldaplogin.endpoint.LoginLdapServiceEndpoint;

@Configuration
public class WebServiceConfig {
	
	/*
	 * CXF Endpoint (Provider)
	 */
	@Configuration
	public class EndpointConfig {
		
		@Bean(name = Bus.DEFAULT_BUS_ID)
		public SpringBus springBus() {
			SpringBus bus = new SpringBus();
			
			return bus;
		}
		
		@Bean
		public Endpoint accountsEndpoint(@Qualifier("loginLdapServiceEndpoint") LoginLdapServiceEndpoint loginLdapServiceEndpoint) {
			EndpointImpl endpoint = new EndpointImpl(springBus(), loginLdapServiceEndpoint);
			endpoint.publish("/LoginLdap");
			endpoint.setWsdlLocation("LoginLdap.wsdl");
			
			return endpoint;
		}
		
	}
	
}
