package org.chuanshen.devladder.controller.system.basic;

import org.chuanshen.devladder.model.Position;
import org.chuanshen.devladder.model.RespBean;
import org.chuanshen.devladder.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/5/22 10:29
 * @Description:
 **/
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPositon(@RequestBody Position position) {
        if (positionService.addPosition(position) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
}
