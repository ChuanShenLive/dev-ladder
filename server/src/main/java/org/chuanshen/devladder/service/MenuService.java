package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.MenuMapper;
import org.chuanshen.devladder.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/4/28 14:59
 * @Description:
 **/
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;


    public Object getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
