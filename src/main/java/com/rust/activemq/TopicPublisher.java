 /*
  * Package com.rust.activemq
  * FileName: TopicPublisher
  * Author:   Rust
  * Date:     2018/6/10 20:51
  */
 package com.rust.activemq;

 import org.apache.activemq.ActiveMQConnection;
 import org.apache.activemq.ActiveMQConnectionFactory;

 import javax.jms.*;

 /**
  * FileName:    TopicPublisher
  * Author:      Rust
  * Date:        2018/6/10
  * Description: 消息发布
  */
 public class TopicPublisher {


  public static final String USER_NAME = ActiveMQConnection.DEFAULT_USER;

  public static final String USER_PWD = ActiveMQConnection.DEFAULT_PASSWORD;

  public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

  public static void main(String[] args) {

   // 创建链接工厂
   ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME, USER_PWD, BROKER_URL);
   // 创建连接
   try {
    Connection connection = connectionFactory.createConnection();
    // 创建会话，不需要事物
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 创建 Topic,用作消费者订阅消息
    Topic topic = session.createTopic("activemq-topic-test1");
    // 消息生产者
    MessageProducer producer = session.createProducer(topic);

    for (int i = 0; i < 3; i++) {
     TextMessage message = session.createTextMessage("发送消息" + i);
     producer.send(topic, message);
    }
    // 关闭资源
    session.close();
    connection.close();
    // producer.close();
   } catch (JMSException e) {
    e.printStackTrace();
   }


  }









 }
