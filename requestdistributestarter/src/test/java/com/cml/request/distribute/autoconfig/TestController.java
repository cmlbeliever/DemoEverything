package com.cml.request.distribute.autoconfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestBody String body) {
        return body;
//        throw  new RuntimeException(body);
    }
}
