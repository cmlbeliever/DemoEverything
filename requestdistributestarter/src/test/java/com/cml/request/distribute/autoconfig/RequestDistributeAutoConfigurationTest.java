package com.cml.request.distribute.autoconfig;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    public void testConfigLoop() throws InterruptedException {
        final AtomicInteger withTokenCount = new AtomicInteger(0);
        final AtomicInteger withoutTokenCount = new AtomicInteger(0);
        int loopCount = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(loopCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            new Thread(() -> {
                try {
                    String result = testConfig();
                    if (result.startsWith("withToken")) {
                        withTokenCount.incrementAndGet();
                    } else {
                        withoutTokenCount.incrementAndGet();
                    }
                    Thread.sleep(100);
//                    System.out.println("withTokenCount:" + withTokenCount.get() + ",withoutTokenCount:" + withoutTokenCount.get());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        while (!countDownLatch.await(3, TimeUnit.SECONDS)) {
        }

        System.out.println("withTokenCount:" + withTokenCount.get() + ",withoutTokenCount:" + withoutTokenCount.get() + "," + (System.currentTimeMillis() - start));
    }

    public String testConfig() throws Exception {
//        System.out.println(111);
        String uri = "/test";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).content("测试参数dddddd").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();


//        System.out.println("result:" + content);

        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
        return content;
    }

}
