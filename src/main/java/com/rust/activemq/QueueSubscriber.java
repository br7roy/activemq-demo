 /*
  * Package com.rust.activemq
  * FileName: QueueSubscriber
  * Author:   Rust
  * Date:     2018/6/11 22:03
  */
 package com.rust.activemq;

 import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.rust.activemq.TopicPublisher.BROKER_URL;
import static com.rust.activemq.TopicSubscriber.PASSWORD;
import static com.rust.activemq.TopicSubscriber.USERNAME;

 /**
  * FileName:    QueueSubscriber
  * Author:      Rust
  * Date:        2018/6/11
  * Description:订阅queue
  */
 public class QueueSubscriber {

  public static void main(String[] args) {
   //创建连接工厂
   ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
   try {
    //创建连接
    Connection connection = connectionFactory.createConnection();
    //开启连接
    connection.start();
    //创建会话，不需要事务
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    //创建 Topic
    Queue testQueue = session.createQueue("queueTest");
    MessageConsumer messageConsumer = session.createConsumer(testQueue);
    messageConsumer.setMessageListener(new MessageListener() {
     @Override
     public void onMessage(Message message) {
      try {
       System.out.println("消费者接收到消息：" + ((TextMessage) message).getText());
      } catch (JMSException e) {
       e.printStackTrace();
      }
     }
    });
    // 关闭资源
    // session.close();
    // connection.close();
    // messageConsumer1.close();
    // messageConsumer2.close();
    // messageConsumer3.close();
   } catch (JMSException e) {
    e.printStackTrace();
   }
  }
 }
