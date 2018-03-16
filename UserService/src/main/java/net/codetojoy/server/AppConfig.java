
package net.codetojoy.server;

import net.codetojoy.common.UserService;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.*;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@EnableWebMvc
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean(name = "/UserService")
    public RemoteExporter exporter() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(userService());
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }
}
