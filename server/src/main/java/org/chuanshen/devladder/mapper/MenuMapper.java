package org.chuanshen.devladder.mapper;

import org.chuanshen.devladder.model.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Menu> getAllMenuWithRole();

    List<Menu> getAllMenus();

    List<Long> getMidsByRid(Long rid);
}
