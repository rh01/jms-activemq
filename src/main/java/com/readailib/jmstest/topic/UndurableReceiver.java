package com.readailib.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/*
 * @program: jmstest
 * @description: 消息接受，消费者
 * @Author: ReadAILib
 * @create: 2018-04-09 16:32
 **/
public class UndurableReceiver {
    public static void main(String[] args) throws Exception {
        // 创建工厂构造
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.59.150:61616");
        // 创建连接
        Connection connection = cf.createConnection();
        // 启动连接
        connection.start();



        // 创建会话
        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 这个队列名字一定是activemq里面有的
        Destination destination = session.createTopic("MyTopic");
        // 创建消费者来读取队列的数据
        MessageConsumer consumer = session.createConsumer(destination);
        int i=0;
        // 开始消费
        Message message = consumer.receive();
        while(message!=null) {
            TextMessage txtMsg = (TextMessage)message;
            System.out.println("收到消息：" + txtMsg.getText());
            message = consumer.receive(1000L);
        }
        session.commit();

        // 关闭连接
        session.close();
        connection.close();
    }
}
