package com.restaurant.sample.development;

import lombok.val;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class DevelopmentApplicationTests {

    @ClassRule
    public static GenericContainer rabbit = new GenericContainer("rabbitmq:3-management")
            .withExposedPorts(5672, 15672);
//    @Rule
//    public OutputCapture outputCapture = new OutputCapture();

//    @Autowired
//    private MessageSender messageSender;

    @Test
    public void testBroadcast() {
//        messageSender.broadcast("Broadcast Test");
//        await().atMost(5, TimeUnit.SECONDS).until(isMessageConsumed(), is(true));
    }
//    private Callable<Boolean> isMessageConsumed() {
//        return () -> outputCapture.toString().contains("Broadcast Test");
//    }

    public static class Initializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
            @Override
            public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
                val values = TestPropertyValues.of(
                        "spring.rabbitmq.host=" + rabbit.getContainerIpAddress(),
                        "spring.rabbitmq.port=" + rabbit.getMappedPort(5672)
                );
                values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    void contextLoads() {
    }

}
