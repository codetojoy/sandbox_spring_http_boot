
package net.codetojoy.client;

import org.springframework.context.*;
import org.springframework.context.support.*;

import net.codetojoy.common.*;

public class Client {
    private static final String BILLING = "b";
    private static final String COMPOUND = "c";
    private static final String USER = "u";

    private static final String BILLING_SERVICE = "billingService";
    private static final String COMPOUND_SERVICE = "compoundService";
    private static final String USER_SERVICE = "userService";

    private ApplicationContext context = null;

    public Client(ApplicationContext context) {
        this.context = context;
    }

    public BillingService getBillingService() {
        return (BillingService) context.getBean(BILLING_SERVICE);
    }

    public CompoundService getCompoundService() {
        return (CompoundService) context.getBean(COMPOUND_SERVICE);
    }

    public UserService getUserService() {
        return (UserService) context.getBean(USER_SERVICE);
    }

    private void processCommand() {
        Prompt prompt = new Prompt();
        String input = prompt.getInput("\n\ncmd: [U=user, B=billing, C=compound, Q=quit] ?", USER, BILLING, COMPOUND);

        if (input.equalsIgnoreCase(USER)) {
            String name = prompt.getInput("enter a name: "); 
            System.out.println("TRACER userService: " + getUserService());
            User user = getUserService().getUser(name);
            System.out.println("result : " + user);
        } else if (input.equalsIgnoreCase(BILLING)) {
            String name = prompt.getInput("enter a name: "); 
            Payment payment = getBillingService().getPayment(name);
            System.out.println("result : " + payment);
        } else if (input.equalsIgnoreCase(COMPOUND)) {
            String name = prompt.getInput("enter a name: "); 
            CompoundInfo info = getCompoundService().getCompoundInfo(name);
            System.out.println("result : " + info);
        } 
    }

    public void inputLoop() {
        while (true) {
            try {
                processCommand();
            } catch(Exception ex) {
                System.err.println("\nTRACER command failed! check if the service is running \n");
                System.err.println("\nmessage : " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("client_config.xml"); 
        Client client = new Client(context);
        client.inputLoop();
    }
}
