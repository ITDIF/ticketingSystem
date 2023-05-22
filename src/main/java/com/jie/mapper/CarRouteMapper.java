package com.jie.mapper;

import com.jie.pojo.CarRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jie
 */
@Mapper
@Repository
public interface CarRouteMapper {
    List<CarRoute> queryCarRouteList();

    CarRoute queryCarRouteById(Integer id);

    List<CarRoute> queryCarRouteBySE(String start, String end);

    int addCarRoute(CarRoute carRoute);

    int updateCarRoute(CarRoute carRoute);

    int deleteCarRouteById(Integer id);
}
