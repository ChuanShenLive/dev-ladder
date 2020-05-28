package org.chuanshen.devladder.exception;

import org.chuanshen.devladder.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/5/28 16:16
 * @Description:
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean SQLExceptionHandler(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据有关联数据, 操作失败!");
        }
        return RespBean.ok("数据库异常, 操作失败!");
    }
}
