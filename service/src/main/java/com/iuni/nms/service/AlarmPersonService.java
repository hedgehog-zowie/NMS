package com.iuni.nms.service;

import com.iuni.nms.persist.domain.AlarmPerson;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface AlarmPersonService {

    AlarmPerson getById(Long id);

    List<AlarmPerson> listAlarmPerson(final AlarmPerson alarmPerson);

    void addAlarmPerson(AlarmPerson alarmPerson);

    void updateAlarmPerson(AlarmPerson alarmPerson);

    void deleteAlarmPerson(String ids);

}
