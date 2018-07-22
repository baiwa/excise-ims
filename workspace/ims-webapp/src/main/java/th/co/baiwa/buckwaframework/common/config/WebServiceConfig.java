package th.co.baiwa.buckwaframework.common.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.co.baiwa.exampleproject.ws.consumer.currentdate.operation.CurrentDate;

@Configuration
public class WebServiceConfig {
	public class ClientConfig {
	
	@Bean(name = "currentDateProxy")
	public CurrentDate currentDateProxy() {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(CurrentDate.class);
		jaxWsProxyFactoryBean.setAddress("http://150.95.24.42:8080/SystemCurrentDate/services/CurrentDate");
		
		CurrentDate currentDateProxy = (CurrentDate) jaxWsProxyFactoryBean.create();
		
		Client client = ClientProxy.getClient(currentDateProxy);
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(36000);
		httpClientPolicy.setReceiveTimeout(36000);
		httpClientPolicy.setAllowChunking(false);
		http.setClient(httpClientPolicy);
		
		client.getInInterceptors().add(new LoggingInInterceptor());
		client.getOutInterceptors().add(new LoggingOutInterceptor());
		
		return currentDateProxy;
	}
	
}}
