package com.lbint.configuration;

import org.apache.catalina.connector.Connector;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.lbint.utility.RestClient;


@Configuration
public class ServiceConfiguration {
	private Environment env;
	
	@Autowired
	public ServiceConfiguration(Environment env){
		this.env = env;
	}
	
	@Bean
	public RestClient restClient(){
	    return new RestClient(new AsyncRestTemplate(), env);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.requestFactory(this::clientHttpRequestFactory)
				.build();
	}
	
	private HttpClientConnectionManager connManager() {
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
		
		manager.setMaxTotal(Integer.parseInt(env.getProperty("service.configuration.http.max-threads")));
		manager.setDefaultMaxPerRoute(Integer.parseInt(env.getProperty("service.configuration.http.max-threads-per-route")));
		
		return manager;
	}
	
	private RequestConfig requestConfig() {
		return RequestConfig
				.custom()
				.setSocketTimeout(Integer.parseInt(env.getProperty("service.configuration.http.socket-timeout")))//Tiempo de espera a la peticion
				.setConnectionRequestTimeout(Integer.parseInt(env.getProperty("service.configuration.http.conn-request-timeout")))//time out hacia el pull de conexiones interno
				.setConnectTimeout(Integer.parseInt(env.getProperty("service.configuration.http.http-request-timeout")))//tiempo de espera
				.build();
	}
	
	private HttpClient httpClient() {
		return HttpClientBuilder
				.create()
				.setConnectionManager(connManager())
				.setDefaultRequestConfig(requestConfig())
				.build();
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
	    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	    
	    factory.setHttpClient(httpClient());
	    
	    return factory;
	} 
	@Bean
    public ServletWebServerFactory servletContainer(@Value("${http.port}") int httpPort) {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);
        connector.setScheme("https");
        connector.setSecure(true);
//        SSLHostConfigCertificate s = new S
//        SSLHostConfig ssl = new SSLHostConfig();
//        ssl.setE
//        connector.addSslHostConfig(sslHostConfig);

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;  
    } 

}
