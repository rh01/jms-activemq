package com.readailib.jmstest.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/*
 * @program: jmstest
 * @description:
 * @Author: ReadAILib
 * @create: 2018-04-09 19:16
 **/
@Service
public class QueueReceiver {
    @Autowired
    private JmsTemplate jt = null;
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueReceiver ct = (QueueReceiver)ctx.getBean("queueReceiver");
        String msg = (String)ct.jt.receiveAndConvert();
        System.out.println("msg==="+msg);
    }
}
