package com.cml.request.distribute.autoconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = RequestDistributeAutoConfigurationTestConfiguration.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class RequestDistributeAutoConfigurationTest {

    private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationConnect;

    @Autowired
    private FilterRequestDistribute filterRequestDistribute;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).addFilters(filterRequestDistribute).build();
    }

    @Test
    public void testConfigLoop() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    testConfig();
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void testConfig() throws Exception {
//        System.out.println(111);
        String uri = "/test";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).content("测试参数").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

//        System.out.println("result:" + content);

        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
    }

}
