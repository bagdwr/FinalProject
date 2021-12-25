package com.example.finalproject.Service;


import com.example.finalproject.Model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import java.time.LocalDate;

@Stateless
public class JMSservice {
    @EJB
    UserService userService;

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource
    private Queue queue;

    public User sendJMSmessage(String name, String email, String birthday, String password)throws JMSException {
        Connection connection=null;
        Session session=null;
        try {
            connection=connectionFactory.createConnection();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer=session.createProducer(queue);
            connection.start();
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            final Message message=session.createTextMessage(name+" "+email+" "+birthday+" "+password);
            messageProducer.send(message);
            return userService.createUser(name,email,birthday,password);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Caught exception from JMS when sending a message", ex);
        }finally {
            if (session != null) session.close();
            if (connection != null ) connection.close();
        }
    }

    public String getJMSmessage()throws JMSException{
        Connection connection = null;
        Session session = null;
        try {
            connection=connectionFactory.createConnection();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            final MessageConsumer messageConsumer=session.createConsumer(queue);

            connection.start();
            final Message message=messageConsumer.receive(1000);
            if (message==null){
                return "JMS is null";
            }

            TextMessage user=(TextMessage) message;
            if (user == null) {
                return "JMS is null";
            }

            return user.getText();
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Caught exception from JMS when getting a message", ex);
        }finally {
            if (session != null) session.close();
            if (connection != null ) connection.close();
        }
    }
}
