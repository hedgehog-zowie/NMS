package com.iuni.nms.service;

import com.iuni.nms.persist.domain.AlarmInfo;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface AlarmInfoService {

    AlarmInfo getById(Long id);

    List<AlarmInfo> listAlarmInfo(final AlarmInfo alarmInfo);

    void addAlarmInfo(AlarmInfo alarmInfo);

    void updateAlarmInfo(AlarmInfo alarmInfo);

    void deleteAlarmInfo(String ids);

}
