package com.jie.service;

/**
 * @author jie
 */
public interface OrderService {
    /**
     * 添加临时订单到临时表
     * @param route_number 车次编号
     * @param route_date 车次发车时间
     * @param account 账户
     * @return 结果 -1 不成功、订单号
     */
    String addOrderTemporary(String route_number, String route_date, String account);

    /**
     * 查询已有候补数量
     * @param route_number 线路编号
     * @param departure_time 出发时间
     * @return 数量
     */
    int queryExistCandidateCount(String route_number, String departure_time);

    /**
     * 根据订单编号查询下单时间
     * @param order_number 订单编号
     * @return 下单时间
     */
    String queryOrderTimeByOrderNumber(String order_number);

    /**
     * 添加订单同时删除临时订单（支付成功操作）
     * @param orderNumber 临时订单编号
     * @return int
     */
    int addOrderAndDelTemporary(String orderNumber);

    /**
     * 候补
     * @param route_number 线路编号
     * @param route_date 线路发车时间
     * @param account 账户
     * @return 订单号
     */
    String addCandidate(String route_number, String route_date, String account);

    /**
     * 删除临时订单和车票（取消订单操作）
     * @param order_number 订单编号
     * @param date 订单时间
     * @return 结果
     */
    int deleteOrderTemporaryAndTicket(String order_number, String date);

}
