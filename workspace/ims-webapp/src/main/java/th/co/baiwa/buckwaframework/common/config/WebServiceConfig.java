package th.co.baiwa.buckwaframework.common.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import baiwa.co.th.ws.LoginLdap;
import th.co.baiwa.exampleproject.ws.consumer.currentdate.operation.CurrentDate;

@Configuration
public class WebServiceConfig {

	public class ClientConfig {

		@Value("${wsdl.endpoint.ws.lpap}")
		private String exciseLdap;
		
		
		@Value("${wsdl.endpoint.ws.current.date}")
		private String currentDate;
		
		@Value("${wsdl.endpoint.ws.lpap.dev}")
		private String ldapDev;

		@Bean(name = "currentDateProxy")
		public CurrentDate currentDateProxy() {
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setServiceClass(CurrentDate.class);
			jaxWsProxyFactoryBean.setAddress(currentDate);

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

		@Bean(name = "loginLdapProxy")
		public LoginLdap loginLdapProxy() {
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setServiceClass(LoginLdap.class);
			jaxWsProxyFactoryBean.setAddress(ldapDev);

			LoginLdap loginLdapProxy = (LoginLdap) jaxWsProxyFactoryBean.create();

			Client client = ClientProxy.getClient(loginLdapProxy);
			HTTPConduit http = (HTTPConduit) client.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setReceiveTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			http.setClient(httpClientPolicy);

			client.getInInterceptors().add(new LoggingInInterceptor());
			client.getOutInterceptors().add(new LoggingOutInterceptor());

			return loginLdapProxy;
		}

//		@Bean(name = "ldapgAuthenAndGetUserRolePortTypeProxy")
//		public LDPAGAuthenAndGetUserRolePortType ldapgAuthenAndGetUserRolePortTypeProxy() {
//			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//			jaxWsProxyFactoryBean.setServiceClass(LDPAGAuthenAndGetUserRolePortType.class);
//			jaxWsProxyFactoryBean.setAddress(exciseLdap);
//
//			LDPAGAuthenAndGetUserRolePortType ldapgAuthenAndGetUserRolePortTypeProxy = (LDPAGAuthenAndGetUserRolePortType) jaxWsProxyFactoryBean.create();
//
//			Client client = ClientProxy.getClient(ldapgAuthenAndGetUserRolePortTypeProxy);
//			HTTPConduit http = (HTTPConduit) client.getConduit();
//			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
//			httpClientPolicy.setConnectionTimeout(36000);
//			httpClientPolicy.setReceiveTimeout(36000);
//			httpClientPolicy.setAllowChunking(false);
//			http.setClient(httpClientPolicy);
//
//			client.getInInterceptors().add(new LoggingInInterceptor());
//			client.getOutInterceptors().add(new LoggingOutInterceptor());
//
//			return ldapgAuthenAndGetUserRolePortTypeProxy;
//		}

	}
}
