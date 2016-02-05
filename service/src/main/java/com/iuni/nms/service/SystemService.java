package com.iuni.nms.service;

import com.iuni.nms.persist.domain.System;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface SystemService {

    System getById(Long id);

    List<System> listSystem(final System system);

    void addSystem(System system);

    void updateSystem(System system);

    void deleteSystem(String ids);
    
}
