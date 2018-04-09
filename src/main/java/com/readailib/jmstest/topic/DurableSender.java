package com.readailib.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
 * @program: jmstest
 * @description: 消息生产者
 * @Author: ReadAILib
 * @create: 2018-04-09 16:31
 **/
public class DurableSender {
    public static void main(String[] args) throws Exception {
        // 连接工厂
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory("tcp://192.168.59.150:61616");
        // 创建连接
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 创建一个队列
        Destination destination = session.createTopic("MyTopic1");
        // 生产者通过session将消息发送给my-queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        // 开始连接
        connection.start();
        // 产生三条消息，向外发送
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message11111--" + i);


            //Thread.sleep(1000);
            //通过消息生产者发出消息
            producer.send(message);
        }
        // session提交
        session.commit();
        session.close();
        connection.close();
    }
}
