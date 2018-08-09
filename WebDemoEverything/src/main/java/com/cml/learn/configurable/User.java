package com.cml.learn.configurable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Auther: cml
 * @Date: 2018-08-02 10:01
 * @Description:
 */
@Component()
@Scope("prototype")
public class User {
    private String username;
    private String name;
    @Autowired
    private UserRepository userRepository;

    public void save() {
        userRepository.save();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
