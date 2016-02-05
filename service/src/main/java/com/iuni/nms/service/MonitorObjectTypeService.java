package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorObjectType;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface MonitorObjectTypeService {

    MonitorObjectType getById(Long id);

    List<MonitorObjectType> listMonitorObjectType(final MonitorObjectType monitorObjectType);

    void addMonitorObjectType(MonitorObjectType monitorItemType);

    void updateMonitorObjectType(MonitorObjectType monitorItemType);

    void deleteMonitorObjectType(String ids);
    
}
