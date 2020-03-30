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
                        //String exchange = "myexchange";
                        //String exchange = "myexchange1";
                        String exchange = "myexchange2";
                        //channel.exchangeDeclare(exchange,"fanout",true,true,null);
                        //channel.exchangeDeclare(exchange,"direct",true,true,null);
                        channel.exchangeDeclare(exchange,"topic",true,true,null);
                        //String queue = "myqueue";
                        //channel.queueDeclare(queue, false, false, false, null);
                        //channel.basicPublish("", queue, null, "hello".getBytes());
                        //channel.basicPublish(exchange,"A",null,"hello".getBytes());
                        channel.basicPublish(exchange,"order.update",false,false,null,"hello".getBytes());
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
                    Connection connection = connectionFactory.newConnection();;
                    final  Channel channel = connection.createChannel();;
                    //channel.basicQos(1);//能者多劳模式
                    //String exchange = "myexchange";
                    //String exchange = "myexchange1";
                    String exchange = "myexchange2";
                    //String queue = "myqueue";
                    //String queue = "myqueue1";
                    String queue = "myqueue2";
                    channel.queueDeclare(queue, false, false, false, null);
                    channel.exchangeDeclare(exchange,"topic",true,true,null);
                    //channel.queueBind(queue,exchange,"");
                    channel.queueBind(queue,exchange,"order.*");
                    boolean b=false;//ture,自动确认
                    channel.basicConsume(queue,false,new DefaultConsumer(channel){
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            String s = new String(body);
                            System.out.println(s);
                            channel.basicAck(envelope.getDeliveryTag(),false);
                        }
                    });
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
