package com.jie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.Impl.RabbitService;
import com.jie.service.Impl.RedisService;
import com.jie.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    RabbitService rabbitService;
    @Resource
    RedisService redisService;

    @RequestMapping("/queryOrder")
    public Order queryOrderByOrderNumber(String orderNumber) {
        return orderService.queryOrderByOrderNumber(orderNumber);
    }

    @RequestMapping("/temporary")
    public String temporary(String route_number, String route_date, String account, String oldOrderNumber){
        String orderId = orderService.addOrderTemporary(route_number,route_date,account,oldOrderNumber);
        if(!"-1".equals(orderId)){
            rabbitService.cancelOrder(orderId,route_date);
        }
        return orderId;
    }
    @RequestMapping("/queryOrderTimeByOrderNumber")
    public String queryOrderTimeByOrderNumber(String order_number){
        return orderService.queryOrderTimeByOrderNumber(order_number);
    }
    @RequestMapping("/queryOrderTimeAndSeatByOrderNumber")
    public Map<String,Object> queryOrderTimeAndSeat(String order_number){
        return orderService.queryOrderTimeAndSeatByOrderNumber(order_number);
    }
    @RequestMapping("/queryOrderTemporaryByAccount")
    public List<OrderTemporary> queryOrderTemporaryByAccount(String account){
        return orderService.queryOrderTemporaryByAccount(account);
    }
    @RequestMapping("/queryNotTravelOrderByPaging")
    public List<Order> queryNotTravelOrderByPaging(String account, String start, String count){
        return orderService.queryNotTravelOrderByPaging(account, start, count);
    }
    @RequestMapping("/deleteOrderTemporaryAndTicket")
    public int deleteOrderTemporaryAndTicket(String order_number, String date){
        return orderService.deleteOrderTemporaryAndTicket(order_number,date);
    }
    @RequestMapping("/upOrderAndDelTicket")
    public int upOrderAndDelTicket(String order_number, String date){
        return  orderService.upOrderAndDelTicket(order_number, date);
    }
    @RequestMapping("/addOrderAndDelTemporary")
    public int addOrderAndDelTemporary(String orderNumber){
        try {
            return orderService.addOrderAndDelTemporary(orderNumber);
        }catch (Exception e){
            return 0;
        }
    }
    @RequestMapping("/addOrderAndDelTemporaryAndUpOldOrder")
    public int addOrderAndDelTemporaryAndUpOldOrder(String orderNumber, String oldOrderNumber) {
        try {
            if(oldOrderNumber == null){
                oldOrderNumber = redisService.getRebookOrderNumber(orderNumber);
            }
            return orderService.addOrderAndDelTemporaryAndUpOldOrder(orderNumber, oldOrderNumber);
        }catch (Exception e){
            return 0;
        }
    }
    @RequestMapping("/candidateSuccess")
    public int candidateSuccess(String order_number){
        return orderService.candidateSuccess(order_number);
    }
    @RequestMapping("/queryHistoricalOrderByAccount")
    public List<Order> queryHistoricalOrderByAccount(String account){
        return orderService.queryHistoricalOrderByAccount(account);
    }
    @RequestMapping("/queryHistoricalOrderConditional")
    public List<Order> queryHistoricalOrderConditional(String account, String startDate, String endDate, String key, String start, String count){
        return orderService.queryHistoricalOrderConditional(account,startDate,endDate,key,start,count);
    }
    @RequestMapping("/queryHistoricalOrderConditionalCount")
    public int queryHistoricalOrderConditionalCount(String account, String startDate, String endDate, String key){
        return orderService.queryHistoricalOrderConditionalCount(account,startDate,endDate,key);
    }
    @RequestMapping("/queryHistoricalOrderPaging")
    public List<Order> queryHistoricalOrderPaging(String account, String start, String count){
        return orderService.queryHistoricalOrderPaging(account,start,count);
    }
    @RequestMapping("/queryHistoricalOrderCount")
    public int queryHistoricalOrderCount(String account){
        return orderService.queryHistoricalOrderCount(account);
    }
    @RequestMapping("/queryNotTravelOrderCount")
    public int queryNotTravelOrderCount(String account){
        return orderService.queryNotTravelOrderCount(account);
    }
    @RequestMapping("/candidate")
    public String candidate(String route_number, String route_date, String account,int deadline){
        String orderNumber = orderService.addCandidate(route_number, route_date, account, deadline);
        rabbitService.cancelCandidateOrder(orderNumber);
        return orderNumber;
    }
    @RequestMapping("/existCandidate")
    public int existCandidate(String route_number, String departure_time){
        return orderService.queryExistCandidateCount(route_number,departure_time);
    }
    @RequestMapping("/deleteOrderTemporaryAndCandidate")
    public int deleteOrderTemporaryAndCandidate(String order_number){
        return orderService.deleteOrderTemporaryAndCandidate(order_number);
    }
    @RequestMapping("/setRebookOrderNumber")
    public void setRebookOrderNumber(String orderNumber, String rebookOrderNumber){
        redisService.setRebookOrderNumber(orderNumber, rebookOrderNumber);
    }
    @RequestMapping("/deleteOrderByOrderNumber")
    public int deleteOrderByOrderNumber(String order_number) {
        return orderService.deleteOrderByOrderNumber(order_number);
    }
    @RequestMapping("/batchDel")
    public int batchDelByOrderNumber(String orderNumbers) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = mapper.readValue(orderNumbers, new TypeReference<>() {});
        return orderService.batchDelByOrderNumber(list);
    }

}
