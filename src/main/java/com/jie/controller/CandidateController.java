package com.jie.controller;

import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Resource
    CandidateService candidateService;

    @RequestMapping("/queryOrderTemporaryByAccount")
    @ResponseBody
    public List<OrderTemporary> queryOrderTemporaryByAccount(String account){
        return candidateService.queryOrderTemporaryByAccount(account);
    }
    @RequestMapping("/queryNotTravelCandidateCount")
    @ResponseBody
    public int queryNotTravelCandidateCount(String account){
        return candidateService.queryNotTravelCandidateCount(account);
    }
    @RequestMapping("/queryNotTravelCandidateByPaging")
    @ResponseBody
    public List<Order> queryNotTravelCandidateByPaging(String account, String start, String count){
        return candidateService.queryNotTravelCandidateByPaging(account,start,count);
    }
    @RequestMapping("/queryHistoricalCandidateCount")
    @ResponseBody
    public int queryHistoricalCandidateCount(String account) {
        return candidateService.queryHistoricalCandidateCount(account);
    }
    @RequestMapping("/queryHistoricalCandidatePaging")
    @ResponseBody
    public List<Order> queryHistoricalCandidatePaging(String account, String start, String count) {
        return candidateService.queryHistoricalCandidatePaging(account,start,count);
    }
    @RequestMapping("/queryHistoricalCandidateConditionalPaging")
    @ResponseBody
    public List<Order> queryHistoricalCandidateConditionalPaging(String account, String startDate, String endDate,
                                                           String key, String start, String count){
        return candidateService.queryHistoricalCandidateConditionalPaging(account,startDate,endDate,
                key,start,count);
    }
    @RequestMapping("/queryHistoricalCandidateConditionalCount")
    @ResponseBody
    public int queryHistoricalCandidateConditionalCount(String account, String startDate, String endDate, String key) {
        return candidateService.queryHistoricalCandidateConditionalCount(account,startDate,endDate,key);
    }
    @RequestMapping("/delCandidateAndOrder")
    @ResponseBody
    public int delCandidateAndOrder(String order_number) {
        return candidateService.delCandidateAndOrder(order_number);
    }
}
