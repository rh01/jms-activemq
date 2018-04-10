package com.readailib.jmstest.staticnetwork;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/*
 * @program: jmstest
 * @description: 多线程连接activemq
 * @Author: ReadAILib
 * @create: 2018-04-09 16:32
 **/
public class QueueReceiver1 {
    public static void main(String[] args) throws Exception {
        ConnectionFactory cf = new ActiveMQConnectionFactory(
                "tcp://192.168.59.150:61616");

        for (int i = 0; i < 10; i++) {
            Thread t = new MyThread(cf);
            t.start();
        }

    }
}


class MyThread extends Thread {
    // 创建工厂构造
    private ConnectionFactory cf = null;

    public MyThread(ConnectionFactory cf) {
        this.cf = cf;
    }

    public void run() {
        // 创建连接
        Connection connection = null;
        try {
            connection = cf.createConnection();

            // 启动连接
            connection.start();

            Enumeration name = null;
            name = connection.getMetaData().getJMSXPropertyNames();
            while (name.hasMoreElements()) {
                String o = (String) name.nextElement();
                System.out.println("jmsx name =" + o);
            }
            // 创建会话
            final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            // 这个队列名字一定是activemq里面有的
            Destination destination = session.createQueue("my-queue2");

            // 创建消费者来读取队列的数据
            MessageConsumer consumer = session.createConsumer(destination);

            final Connection finalConnection = connection;
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接受消息1111=====" + textMessage.getText());
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        session.commit();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        session.close();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        finalConnection.close();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            });


        } catch (JMSException e1) {
            e1.printStackTrace();
        }
        // 关闭连接

    }
}
