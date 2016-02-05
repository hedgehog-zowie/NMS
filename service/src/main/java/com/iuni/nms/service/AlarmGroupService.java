package com.iuni.nms.service;

import com.iuni.nms.persist.domain.AlarmGroup;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface AlarmGroupService {

    AlarmGroup getById(Long id);

    List<AlarmGroup> listAlarmGroup(final AlarmGroup alarmGroup);

    void addAlarmGroup(AlarmGroup alarmGroup);

    void updateAlarmGroup(AlarmGroup alarmGroup);

    void deleteAlarmGroup(String ids);
    
}
