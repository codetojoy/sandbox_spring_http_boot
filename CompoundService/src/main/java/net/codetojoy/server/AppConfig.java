
package net.codetojoy.server;

import net.codetojoy.common.*;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.*;

import org.springframework.remoting.httpinvoker.*;
import org.springframework.remoting.support.RemoteExporter;

@EnableWebMvc
@Configuration
public class AppConfig {
    @Bean
    public CompoundService compoundService() {
        return new CompoundServiceImpl();
    }

    @Bean(name = "/CompoundService")
    public RemoteExporter exporter() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(compoundService());
        exporter.setServiceInterface(CompoundService.class);
        return exporter;
    }

    @Bean
    public HttpInvokerProxyFactoryBean userService() {
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl("http://localhost:5151/UserService");
        bean.setServiceInterface(UserService.class);
        return bean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean billingService() {
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl("http://localhost:5152/BillingService");
        bean.setServiceInterface(BillingService.class);
        return bean;
    }
}
