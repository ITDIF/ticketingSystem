package com.jie.mapper;

import com.jie.pojo.Candidate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jie
 */
@Mapper
@Repository
public interface CandidateMapper {
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
     * 添加候补信息
     * @param candidate 候补
     * @return int
     */
    int addCandidate(Candidate candidate);

    /**
     * 删除候补信息（根据订单号）
     * @param order_number 订单号
     * @return int
     */
    int deleteCandidateByOrderNumber(String order_number);

}
