package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.MenuMapper;
import org.chuanshen.devladder.mapper.MenuRoleMapper;
import org.chuanshen.devladder.model.Hr;
import org.chuanshen.devladder.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/4/28 14:59
 * @Description:
 **/
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public Object getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    // @Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenuWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Long> getMidsByRid(Long rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Long rid, Long[] mids) {
        menuRoleMapper.deleteMenuByRid(rid);
        if (mids == null) return true;
        Integer result = menuRoleMapper.addMenu(rid, mids);
        return result == mids.length;
    }
}
