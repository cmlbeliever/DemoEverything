package com.cml.learn.configurable;

import org.springframework.stereotype.Repository;

/**
 * @Auther: cml
 * @Date: 2018-08-02 10:01
 * @Description:
 */
@Repository
public class UserRepository {
    public void save() {
        System.out.println("-----repositorySave----");
    }
}
