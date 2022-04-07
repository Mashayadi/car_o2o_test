package cn.wolfcode.car.business.service;

import cn.wolfcode.car.business.domain.Appointment;
import cn.wolfcode.car.business.query.AppointmentQueryObject;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

public interface IAppointmentService {

    TablePageInfo<Appointment> query(AppointmentQueryObject qo);

    Appointment get(Long id);

    void save(Appointment appointment);

    void update(Appointment appointment);

    void deleteBatch(String ids);

    List<Appointment> list();

    void updateArrival(Long id );

    void updateCancel(Long id);
}
