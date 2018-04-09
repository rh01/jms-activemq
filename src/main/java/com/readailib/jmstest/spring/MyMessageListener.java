package com.readailib.jmstest.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
 * @program: jmstest
 * @description:
 * @Author: ReadAILib
 * @create: 2018-04-09 19:29
 **/
public class MyMessageListener implements MessageListener {
    public void onMessage(Message arg0) {
        TextMessage msg = (TextMessage)arg0;
        try {
            System.out.println("receive txt msg==="+msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
