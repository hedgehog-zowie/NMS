package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorItemType;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface MonitorItemTypeService {

    MonitorItemType getById(Long id);

    List<MonitorItemType> listMonitorItemType(final MonitorItemType monitorItemType);

    void addMonitorItemType(MonitorItemType monitorItemType);

    void updateMonitorItemType(MonitorItemType monitorItemType);

    void deleteMonitorItemType(String ids);
    
}
