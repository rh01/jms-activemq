package com.readailib.jmstest.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/*
 * @program: jmstest
 * @description: 消息接受，消费者
 * @Author: ReadAILib
 * @create: 2018-04-09 16:32
 **/
public class QueueReceiver {
    public static void main(String[] args) throws Exception {
        // 创建工厂构造
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
                //"tcp://192.168.59.150:61616");
        // 创建连接
        Connection connection = cf.createConnection();
        // 启动连接
        connection.start();

        Enumeration name = connection.getMetaData().getJMSXPropertyNames();
        while (name.hasMoreElements()) {
            String o = (String) name.nextElement();
            System.out.println("jmsx name =" + o );
        }

        // 创建会话
        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 这个队列名字一定是activemq里面有的
        Destination destination = session.createQueue("my-queue1");
        // 创建消费者来读取队列的数据
        MessageConsumer consumer = session.createConsumer(destination);
        int i=0;
        // 开始消费
        while(i<3) {
            i++;
            //TextMessage message = (TextMessage) consumer.receive();
            MapMessage message = (MapMessage) consumer.receive();
            Enumeration mapNames = message.getMapNames();
            while (mapNames.hasMoreElements()) {
                session.commit();
                Object o = mapNames.nextElement();
                System.out.println("收到消息, name = " +  o.toString() + ", value = "+ message.getString(o.toString()));
            }


            // 告诉会话，已经取走
//            session.commit();
//            System.out.println("收到消息：" + message.getText());
        }
        // 关闭连接
        session.close();
        connection.close();
    }
}
