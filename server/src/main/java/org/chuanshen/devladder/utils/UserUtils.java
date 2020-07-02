package org.chuanshen.devladder.utils;

import org.chuanshen.devladder.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/7/2 9:58
 * @Description:
 **/
public class UserUtils {
    public static Hr getCurrentUser() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
