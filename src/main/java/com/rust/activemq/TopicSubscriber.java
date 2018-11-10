 /*
  * Package com.rust.activemq
  * FileName: TopicSubscriber
  * Author:   Rust
  * Date:     2018/6/10 22:31
  */
 package com.rust.activemq;

 import org.apache.activemq.ActiveMQConnection;
 import org.apache.activemq.ActiveMQConnectionFactory;

 import javax.jms.*;
 import java.util.concurrent.TimeUnit;

 /**
  * FileName:    TopicSubscriber
  * Author:      Rust
  * Date:        2018/6/10
  * Description: 消息消费者
  */
 public class TopicSubscriber {

	 /**
	  * 默认用户名
	  */
	 public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	 /**
	  * 默认密码
	  */
	 public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	 /**
	  * 默认连接地址
	  */
	 public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

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
			 Topic myTestTopic = session.createTopic("activemq-topic-test1");
			 MessageConsumer messageConsumer1 = session.createConsumer(myTestTopic);
			 messageConsumer1.setMessageListener(new MessageListener() {
				 @Override
				 public void onMessage(Message message) {
					 try {
						 System.out.println("消费者1接收到消息：" + ((TextMessage) message).getText());
					 } catch (JMSException e) {
						 e.printStackTrace();
					 }
				 }
			 });
			 MessageConsumer messageConsumer2 = session.createConsumer(myTestTopic);
			 messageConsumer2.setMessageListener(new MessageListener() {
				 @Override
				 public void onMessage(Message message) {
					 try {
						 System.out.println("消费者2 接收到消息：" + ((TextMessage) message).getText());
					 } catch (JMSException e) {
						 e.printStackTrace();
					 }
				 }
			 });
			 MessageConsumer messageConsumer3 = session.createConsumer(myTestTopic);
			 messageConsumer3.setMessageListener(new MessageListener() {
				 @Override
				 public void onMessage(Message message) {
					 try {
						 System.out.println("消费者3 接收到消息：" + ((TextMessage) message).getText());
					 } catch (JMSException e) {
						 e.printStackTrace();
					 }
				 }
			 });
			 TimeUnit.SECONDS.sleep(100);
			 // 关闭资源
			 session.close();
			 connection.close();
			 // messageConsumer1.close();
			 // messageConsumer2.close();
			 // messageConsumer3.close();
		 } catch (JMSException | InterruptedException e) {
			 e.printStackTrace();
		 }

	 }


 }
