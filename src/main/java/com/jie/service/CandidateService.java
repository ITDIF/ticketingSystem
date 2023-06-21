package com.jie.service;

import com.jie.pojo.Candidate;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;

import java.util.List;

/**
 * @author jie
 */
public interface CandidateService {
    /**
     * 根据账户查询所有的未完成订单
     * @param account 账户
     * @return 未完成订单
     */
    List<OrderTemporary> queryOrderTemporaryByAccount(String account);

    /**
     * 查询未兑现候补订单数量
     * @param account 账户
     * @return 数量
     */
    int queryNotTravelCandidateCount(String account);

    /**
     * 根据账户查询未出行订单
     * @param account 账户
     * @param start 起点
     * @param count count条订单
     * @return 未出行订单
     */
    List<Order> queryNotTravelCandidateByPaging(String account, String start, String count);
    /**
     * 查询历史候补订单数量
     * @param account 账户
     * @return 历史候补订单数量
     */
    int queryHistoricalCandidateCount(String account);
    /**
     * 根据账户查询历史候补订单分页
     * @param account 账户
     * @param start 起点
     * @param count count条订单
     * @return 订单
     */
    List<Order> queryHistoricalCandidatePaging(String account, String start, String count);
    /**
     * 根据多个条件查询历史候补订单
     * @param account 账户
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param key 关键字（用户名，订单，车次）
     * @param start 开始
     * @param count 数量
     * @return 历史候补订单
     */
    List<Order> queryHistoricalCandidateConditionalPaging(String account, String startDate, String endDate, String key, String start, String count);
    /**
     * 根据多个条件查询历史候补订单数量
     * @param account 账户
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param key 关键字（用户名，订单，车次）
     * @return 历史候补订单数量
     */
    int queryHistoricalCandidateConditionalCount(String account, String startDate, String endDate, String key);

    /**
     * 删除订单和候补
     * @param orderNumber 订单号
     * @return int
     */
    int delCandidateAndOrder(String orderNumber);

    /**
     * 查询截止兑票日期
     * @param orderNumber 订单号
     * @return 小时数
     */
    int queryDeadlineOrderNumber(String orderNumber);

    /**
     * 修改候补订单
     * @param candidate 候补订单
     * @return int
     */
    int updateCandidate(Candidate candidate);
}
