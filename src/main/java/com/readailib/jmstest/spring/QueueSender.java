package com.readailib.jmstest.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/*
 * @program: jmstest
 * @description:
 * @Author: ReadAILib
 * @create: 2018-04-09 19:13
 **/
@Service
public class QueueSender {
    @Autowired
    private JmsTemplate jt = null;

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueSender ct = (QueueSender) ctx.getBean("queueSender");
        ct.jt.send(new MessageCreator() {
            public Message createMessage(Session s) throws JMSException {
                TextMessage msg = s.createTextMessage("Spring msg2222===");
                return msg;
            }
        });
    }
}
