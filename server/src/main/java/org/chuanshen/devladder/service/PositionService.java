package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.PositionMapper;
import org.chuanshen.devladder.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/5/22 10:31
 * @Description:
 **/
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPos();
    }

    public Integer addPosition(Position position) {

        return positionMapper.addPos(position);
    }
}
