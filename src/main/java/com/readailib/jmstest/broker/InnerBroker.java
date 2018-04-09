package com.readailib.jmstest.broker;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import java.net.URI;

/*
 * @program: jmstest
 * @description:
 * @Author: ReadAILib
 * @create: 2018-04-09 18:36
 **/
public class InnerBroker {
    public static void main(String[] args) throws Exception {
        /** 本地内嵌*/
//        BrokerService broker = new BrokerService();
//        broker.setUseJmx(true);
//        broker.addConnector("tcp://localhost:61616");
//        broker.start();

        /** 注入bean*/
//        String Uri = "properties:broker.properties";
//        BrokerService broker1 = BrokerFactory.createBroker(new URI(Uri));
//        broker1.addConnector("tcp://localhost:61616");
//        broker1.start();

        /** spring注入*/
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


    }
}
