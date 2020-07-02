package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.HrMapper;
import org.chuanshen.devladder.model.Hr;
import org.chuanshen.devladder.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/4/25 17:24
 * @Description:
 **/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        return hr;
    }

    public List<Hr> getAllHrs() {
        return hrMapper.getAllHr(UserUtils.getCurrentUser().getId());
    }

    public Integer updateUser(Hr hr) {
        return hrMapper.updateHr(hr);
    }
}
