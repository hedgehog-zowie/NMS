package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorObject;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface MonitorObjectService {

    MonitorObject getById(Long id);

    MonitorObject getByCode(String code);

    List<MonitorObject> listMonitorObject(final MonitorObject monitorObject);

    void addMonitorObject(MonitorObject monitorObject);

    void updateMonitorObject(MonitorObject monitorObject);

    void deleteMonitorObject(String ids);

    void enableMonitorObject(String ids);

    void disableMonitorObject(String ids);
    
}
