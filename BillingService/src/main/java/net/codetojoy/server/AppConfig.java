
package net.codetojoy.server;

import net.codetojoy.common.BillingService;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.*;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@EnableWebMvc
@Configuration
public class AppConfig {
    @Bean
    public BillingService billingService() {
        return new BillingServiceImpl();
    }

    @Bean(name = "/BillingService")
    public RemoteExporter exporter() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(billingService());
        exporter.setServiceInterface(BillingService.class);
        return exporter;
    }
}
