package com.jie.mapper;

import com.jie.pojo.Candidate;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jie
 */
@Mapper
@Repository
public interface CandidateMapper {
    /**
     * 分页查询候补订单
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 候补订单集合
     */
    List<Candidate> queryCandidatePaging(String start, String count, String key, String value);

    /**
     * 候补订单数量
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryCandidateCount(String key, String value);
    /**
     * 根据账户查询所有的未完成订单
     * @param account 账户
     * @return 未完成订单
     */
    List<OrderTemporary> queryOrderTemporaryByAccount(String account);
    /**
     * 查询已有候补数量
     * @param route_number 线路编号
     * @param departure_time 出发时间
     * @return 数量
     */
    int queryExistCandidateCount(String route_number, String departure_time);
    /**
     * 根据订单号查询候补信息
     * @param order_number 订单号
     * @return 候补信息
     */
    Candidate queryCandidateByOrderNumber(String order_number);
    /**
     * 查询未兑现候补订单数量
     * @param account 账户
     * @param departure_time 出发时间
     * @return 数量
     */
    int queryNotTravelCandidateCount(String account, String departure_time);

    /**
     * 根据账户查询未兑现候补订单
     * @param account 账户
     * @param departure_time 出发时间
     * @param start 起点
     * @param count count条订单
     * @return 未出行订单
     */
    List<Order> queryNotTravelCandidateByPaging(String account, String departure_time, String start, String count);

    /**
     * 查询历史候补订单数量
     * @param account 账户
     * @return 数量
     */
    int queryHistoricalCandidateCount(String account);
    /**
     * 根据账户查询历史订单分页
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
     * @param start 起点
     * @param count count条订单
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
     * 根据订单号查询截止兑现时间
     * @param order_number 订单号
     * @return 截止兑现时间
     */
    int queryDeadlineByOrderNumber(String order_number);
    /**
     * 查询最小候补订单号
     * @param route_number 线路编号
     * @param departure_time 出发时间
     * @return 订单号
     */
    String queryMinCandidate(String route_number, String departure_time);

    /**
     * 添加候补信息
     * @param candidate 候补
     * @return int
     */
    int addCandidate(Candidate candidate);

    /**
     * 修改候补订单
     * @param candidate 候补订单
     * @return int
     */
    int updateCandidate(Candidate candidate);

    /**
     * 删除候补信息（根据订单号）
     * @param order_number 订单号
     * @return int
     */
    int deleteCandidateByOrderNumber(String order_number);



}
