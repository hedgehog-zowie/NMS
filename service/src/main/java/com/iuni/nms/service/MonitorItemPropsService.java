package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorItemProps;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface MonitorItemPropsService {

    MonitorItemProps getById(Long id);

    MonitorItemProps getByCode(String code);

    List<MonitorItemProps> listMonitorItemProps(final MonitorItemProps monitorItemProps);

    void addMonitorItemProps(MonitorItemProps monitorItemProps);

    void updateMonitorItemProps(MonitorItemProps monitorItemProps);

    void deleteMonitorItemProps(String ids);
    
}
