package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorItem;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface MonitorItemService {

    MonitorItem getById(Long id);

    MonitorItem getByCode(String code);

    List<MonitorItem> listMonitorItem(final MonitorItem monitorItem);

    void addMonitorItem(MonitorItem monitorItem);

    void updateMonitorItem(MonitorItem monitorItem);

    void deleteMonitorItem(String ids);
    
}
