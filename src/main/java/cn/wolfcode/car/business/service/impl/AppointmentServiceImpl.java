package cn.wolfcode.car.business.service.impl;

import cn.wolfcode.car.business.domain.Appointment;
import cn.wolfcode.car.business.mapper.AppointmentMapper;
import cn.wolfcode.car.business.query.AppointmentQueryObject;
import cn.wolfcode.car.business.service.IAppointmentService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public TablePageInfo<Appointment> query(AppointmentQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<Appointment> list = appointmentMapper.selectForList(qo);
        return new TablePageInfo<>(list);
    }

    @Override
    public Appointment get(Long id) {
        return appointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Appointment appointment) {
        appointment.setCreateTime(new Date());
        appointmentMapper.insert(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentMapper.updateByPrimaryKey(appointment);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] listId = ids.split(",");
        for (String id : listId) {
            appointmentMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<Appointment> list() {
        return appointmentMapper.selectAll();
    }

    @Override
    public void updateArrival(Long id){
        Appointment appointment = appointmentMapper.selectByPrimaryKey(id);
        appointment.setActualArrivalTime(new Date());
        appointment.setStatus(appointment.STATUS_ARRIVAL);
        appointmentMapper.updateArrival(appointment);
    }

    @Override
    public void updateCancel(Long id) {
        Appointment appointment = appointmentMapper.selectByPrimaryKey(id);
        appointment.setStatus(appointment.STATUS_CANCEL);
        appointmentMapper.updateCancel(appointment);
    }
}
