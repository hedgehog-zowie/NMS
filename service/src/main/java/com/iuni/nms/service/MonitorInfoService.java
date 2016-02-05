package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorInfo;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface MonitorInfoService {

    MonitorInfo getById(Long id);

    List<MonitorInfo> listMonitorInfo(final MonitorInfo monitorInfo);

    MonitorInfo addMonitorInfo(MonitorInfo monitorInfo);

    MonitorInfo updateMonitorInfo(MonitorInfo monitorInfo);

    void deleteMonitorInfo(String ids);

    void handleMonitorInfo(String ids);

}
