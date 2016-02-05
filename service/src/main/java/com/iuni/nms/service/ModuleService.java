package com.iuni.nms.service;

import com.iuni.nms.persist.domain.Module;

import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public interface ModuleService {

    Module getById(Long id);

    List<Module> listModule(final Module module);

    void addModule(Module module);

    void updateModule(Module module);

    void deleteModule(String ids);
    
}
