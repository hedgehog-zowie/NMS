package com.iuni.nms.service;

import com.iuni.nms.persist.domain.MonitorInfo;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface EmailService {

    void sendEmail(MonitorInfo monitorInfo);

}
