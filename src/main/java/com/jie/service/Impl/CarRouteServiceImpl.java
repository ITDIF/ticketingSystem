package com.jie.service.Impl;

import com.jie.mapper.CarMapper;
import com.jie.mapper.CarRouteMapper;
import com.jie.mapper.TicketMapper;
import com.jie.pojo.CarRoute;
import com.jie.service.CarRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jie
 */
@Service
public class CarRouteServiceImpl implements CarRouteService {
    @Autowired
    CarRouteMapper carRouteMapper;
    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    CarMapper carMapper;

    @Override
    public List<CarRoute> queryCarRouteList() {
        return carRouteMapper.queryCarRouteList();
    }

    @Override
    public CarRoute queryCarRouteById(Integer id) {
        return carRouteMapper.queryCarRouteById(id);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public List<CarRoute> queryCarRouteBySED(String start, String end, String date) {
        List<CarRoute> list = carRouteMapper.queryCarRouteBySE(start,end);
        for(CarRoute e : list){
            String route_number = e.getRoute_number();
            System.out.println("e "+e+" "+route_number+" "+date);
            String result = ticketMapper.queryRemainingTicket(route_number, date);
            int remainingTicket = 0;
            if(result == null){
                remainingTicket = ticketMapper.queryPassengerCapacityByRouteNumber(route_number);
                ticketMapper.insertRemainingTicketInfo(route_number,String.valueOf(remainingTicket),date);
            }else{
                remainingTicket = Integer.parseInt(result);
            }
            e.setRemaining_tickets(remainingTicket);
            e.setSeat_type(carMapper.querySeatByCarNumber(e.getCar_number()));
        }
        return list;
    }

    @Override
    public int addCarRoute(CarRoute carRoute) {
        return carRouteMapper.addCarRoute(carRoute);
    }

    @Override
    public int updateCarRoute(CarRoute carRoute) {
        return carRouteMapper.updateCarRoute(carRoute);
    }

    @Override
    public int deleteCarRouteById(Integer id) {
        return carRouteMapper.deleteCarRouteById(id);
    }

}
