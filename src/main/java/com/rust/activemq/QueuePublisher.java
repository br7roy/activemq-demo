 /*
  * Package com.rust.activemq
  * FileName: QueuePublisher
  * Author:   Rust
  * Date:     2018/6/11 22:06
  */
 package com.rust.activemq;

 import org.apache.activemq.ActiveMQConnectionFactory;

 import javax.jms.*;

 import static com.rust.activemq.TopicPublisher.BROKER_URL;
 import static com.rust.activemq.TopicPublisher.USER_NAME;
 import static com.rust.activemq.TopicPublisher.USER_PWD;

 /**
  * FileName:    QueuePublisher
  * Author:      Rust
  * Date:        2018/6/11
  * Description:
  */
 public class QueuePublisher {
  public static void main(String[] args) {

   // 创建链接工厂
   ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME, USER_PWD, BROKER_URL);
   // 创建连接
   try {
    Connection connection = connectionFactory.createConnection();
    // 创建会话，不需要事物
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 创建 Topic,用作消费者订阅消息
    Queue queue = session.createQueue("queueTest");
    // 消息生产者
    MessageProducer producer = session.createProducer(queue);

    for (int i = 0; i < 3; i++) {
     TextMessage message = session.createTextMessage("发送消息" + i);
     producer.send(queue, message);
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
