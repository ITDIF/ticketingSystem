package com.jie.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jie
 */
@Configuration
public class RabbitMqConfig {
    public static final String DELAY_EXCHANGE_NAME = "delayed_exchange";
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";
    public static final String DELAY_ROUTING_KEY = "delay_routing_key";

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue queue() {
        return new Queue(DELAY_QUEUE_NAME, true);
    }
    @Bean
    public Queue candidateQueue() {
        return new Queue("candidateQueue", true);
    }

    @Bean
    public Binding binding(Queue queue, CustomExchange delayExchange) {
        return BindingBuilder.bind(queue).to(delayExchange).with(DELAY_ROUTING_KEY).noargs();
    }
    @Bean
    public Binding candidateBinding(Queue candidateQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(candidateQueue).to(delayExchange).with("candidate_queue_key").noargs();
    }

    // 队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("TestDirectQueue",true,true,false);
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestDirectQueue",true);
    }
    @Bean
    public Queue TicketSuccessQueue(){
        return new Queue("TicketSuccessQueue",true);
    }
    @Bean
    public Queue CandidateSuccessQueue(){
        return new Queue("CandidateSuccessQueue",true);
    }
    @Bean
    public Queue WaitingSuccessQueue(){ return new Queue("WaitingSuccessQueue",true);}

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchange",true,false);
    }
    @Bean
    DirectExchange TicketSuccessExchange(){
        return new DirectExchange("TicketSuccessExchange",true,false);
    }



    //绑定
    //将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }
    @Bean
    Binding bindingDirectTicket() {
        return BindingBuilder.bind(TicketSuccessQueue()).to(TicketSuccessExchange()).with("TicketSuccess");
    }
    @Bean
    Binding candidateSuccessBinding() {
        return BindingBuilder.bind(CandidateSuccessQueue()).to(TicketSuccessExchange()).with("candidate_success_key");
    }
    @Bean
    Binding waitingSuccessBinding() {
        return BindingBuilder.bind(WaitingSuccessQueue()).to(TicketSuccessExchange()).with("waiting_success_key");
    }



    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
}
