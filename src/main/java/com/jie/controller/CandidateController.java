package com.jie.controller;

import com.jie.pojo.Candidate;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.CandidateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Resource
    CandidateService candidateService;

    @RequestMapping("/queryCandidatePaging")
    public List<Candidate> queryCandidatePaging(String start, String count, String key, String value){
        return candidateService.queryCandidatePaging(start,count,key,value);
    }
    @RequestMapping("/queryCandidateCount")
    public int queryCandidateCount(String key, String value){
        return candidateService.queryCandidateCount(key, value);
    }
    @RequestMapping("/queryOrderTemporaryByAccount")
    public List<OrderTemporary> queryOrderTemporaryByAccount(String account){
        return candidateService.queryOrderTemporaryByAccount(account);
    }
    @RequestMapping("/queryNotTravelCandidateCount")
    public int queryNotTravelCandidateCount(String account){
        return candidateService.queryNotTravelCandidateCount(account);
    }
    @RequestMapping("/queryNotTravelCandidateByPaging")
    public List<Order> queryNotTravelCandidateByPaging(String account, String start, String count){
        return candidateService.queryNotTravelCandidateByPaging(account,start,count);
    }
    @RequestMapping("/queryHistoricalCandidateCount")
    public int queryHistoricalCandidateCount(String account) {
        return candidateService.queryHistoricalCandidateCount(account);
    }
    @RequestMapping("/queryHistoricalCandidatePaging")
    public List<Order> queryHistoricalCandidatePaging(String account, String start, String count) {
        return candidateService.queryHistoricalCandidatePaging(account,start,count);
    }
    @RequestMapping("/queryHistoricalCandidateConditionalPaging")
    public List<Order> queryHistoricalCandidateConditionalPaging(String account, String startDate, String endDate,
                                                           String key, String start, String count){
        return candidateService.queryHistoricalCandidateConditionalPaging(account,startDate,endDate,
                key,start,count);
    }
    @RequestMapping("/queryHistoricalCandidateConditionalCount")
    public int queryHistoricalCandidateConditionalCount(String account, String startDate, String endDate, String key) {
        return candidateService.queryHistoricalCandidateConditionalCount(account,startDate,endDate,key);
    }
    @RequestMapping("/delCandidateAndOrder")
    public int delCandidateAndOrder(String order_number) {
        return candidateService.delCandidateAndOrder(order_number);
    }
    @RequestMapping("/queryDeadlineOrderNumber")
    public int queryDeadlineOrderNumber(String orderNumber) {
        return candidateService.queryDeadlineOrderNumber(orderNumber);
    }
    @RequestMapping("/updateCandidateDeadline")
    public int updateCandidateDeadline(String orderNumber, int deadline){
        Candidate candidate = new Candidate(null,null,orderNumber,null,null,null,deadline,null);
        return candidateService.updateCandidate(candidate);
    }
}
