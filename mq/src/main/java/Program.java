import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("unused")
public class Program {
    public static void main(String[] args) {
        new Thread(new Thread(){
            @Override
            public void run() {
                try {
                    ConnectionFactory connectionFactory = new ConnectionFactory();
                    connectionFactory.setHost("127.0.0.1");
                    connectionFactory.setUsername("guest");
                    connectionFactory.setPassword("guest");
                    Connection connection = null;
                    Channel channel = null;
                    try {
                        connection = connectionFactory.newConnection();
                        channel = connection.createChannel();
                        String queue = "myqueue";
                        channel.queueDeclare(queue, false, false, false, null);
                        channel.basicPublish("", queue, null, "hello".getBytes());
                    } finally {
                        if (channel != null) {
                            channel.close();
                            channel = null;
                        }
                        if (connection != null) {
                            connection.close();
                            connection = null;
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();

        new Thread(new Thread(){
            @Override
            public void run() {
                try {
                    ConnectionFactory connectionFactory = new ConnectionFactory();
                    connectionFactory.setHost("127.0.0.1");
                    connectionFactory.setUsername("guest");
                    connectionFactory.setPassword("guest");
                    Connection connection = null;
                    Channel channel = null;
                    try {
                        connection = connectionFactory.newConnection();
                        channel = connection.createChannel();
                        String queue = "myqueue";
                        channel.queueDeclare(queue, false, false, false, null);
                        channel.basicConsume(queue,true,new DefaultConsumer(channel){
                            @Override
                            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                                String s = new String(body);
                                System.out.println(s);
                            }
                        });
                    } finally {
                        /*if (channel != null) {
                            channel.close();
                            channel = null;
                        }
                        if (connection != null) {
                            connection.close();
                            connection = null;
                        }*/
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
