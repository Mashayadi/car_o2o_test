package cn.wolfcode.car.business.mapper;

import cn.wolfcode.car.business.domain.Appointment;
import cn.wolfcode.car.business.query.AppointmentQueryObject;

import java.util.List;

public interface AppointmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Appointment record);

    Appointment selectByPrimaryKey(Long id);

    List<Appointment> selectAll();

    int updateByPrimaryKey(Appointment record);

    List<Appointment> selectForList(AppointmentQueryObject qo);

    void updateArrival(Appointment appointment);

    void updateCancel(Appointment appointment);
}