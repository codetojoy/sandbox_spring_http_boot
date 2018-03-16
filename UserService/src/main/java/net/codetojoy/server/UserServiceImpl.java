
package net.codetojoy.server; 

import net.codetojoy.common.User;
import net.codetojoy.common.UserService;

import net.codetojoy.common.util.Utils;

public class UserServiceImpl implements UserService {
    // not thread-safe
    private static int count = 0;

    @Override
    public long ping() {
        return System.currentTimeMillis();
    }

    @Override
    public User getUser(String name) {
        System.out.println("TRACER request name: " + name + " total # " + count++);

        int id = new Utils().getRandomId(100, 1000);

        User user = new User(name, id);
        return user;
    }
}
