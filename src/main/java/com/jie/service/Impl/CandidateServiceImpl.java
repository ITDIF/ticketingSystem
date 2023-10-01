package com.jie.service.Impl;

import com.jie.mapper.CandidateMapper;
import com.jie.mapper.OrderMapper;
import com.jie.pojo.Candidate;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.CandidateService;
import com.jie.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author jie
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Resource
    CandidateMapper candidateMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    UserService userService;
    @Resource
    RabbitService rabbitService;

    @Override
    public List<Candidate> queryCandidatePaging(String start, String count, String key, String value) {
        return candidateMapper.queryCandidatePaging(start, count, key, value);
    }

    @Override
    public int queryCandidateCount(String key, String value) {
        return candidateMapper.queryCandidateCount(key, value);
    }

    @Override
    public List<OrderTemporary> queryOrderTemporaryByAccount(String account) {
        return candidateMapper.queryOrderTemporaryByAccount(account);
    }
    @Override
    public int queryNotTravelCandidateCount(String account) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String departure_time = sdf.format(System.currentTimeMillis());
        return candidateMapper.queryNotTravelCandidateCount(account, departure_time);
    }
    @Override
    public List<Order> queryNotTravelCandidateByPaging(String account, String start, String count) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String departure_time = sdf.format(System.currentTimeMillis());
        return candidateMapper.queryNotTravelCandidateByPaging(account, departure_time, start, count);
    }

    @Override
    public int queryHistoricalCandidateCount(String account) {
        return candidateMapper.queryHistoricalCandidateCount(account);
    }

    @Override
    public List<Order> queryHistoricalCandidatePaging(String account, String start, String count) {
        return candidateMapper.queryHistoricalCandidatePaging(account,start,count);
    }

    @Override
    public List<Order> queryHistoricalCandidateConditionalPaging(String account, String startDate, String endDate, String key, String start, String count) {
        return candidateMapper.queryHistoricalCandidateConditionalPaging(account,startDate,endDate,key,start,count);
    }

    @Override
    public int queryHistoricalCandidateConditionalCount(String account, String startDate, String endDate, String key) {
        return candidateMapper.queryHistoricalCandidateConditionalCount(account,startDate,endDate,key);
    }

    @Override
    public void candidateSuccess(String routeNumber, String departureTime) {
        rabbitService.waitingSuccess(routeNumber,departureTime);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int delCandidateAndOrder(String orderNumber, String account) {
        Order order = orderMapper.queryOrder(orderNumber);
        userService.updateMoneyAndIntegral(account,order.getPrice(),-order.getPrice().intValue()*100);
        return orderMapper.updateOrder(new Order(null,orderNumber,null,null,
                null,null,null,null,null,null,null,null,"已取消(候补)",null)) &
                candidateMapper.deleteCandidateByOrderNumber(orderNumber);
    }

    @Override
    public int queryDeadlineOrderNumber(String orderNumber) {
        return candidateMapper.queryDeadlineByOrderNumber(orderNumber);
    }

    @Override
    public int updateCandidate(Candidate candidate) {
        return candidateMapper.updateCandidate(candidate);
    }
}
