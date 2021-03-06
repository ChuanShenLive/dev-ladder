package org.chuanshen.devladder.mapper;

import org.chuanshen.devladder.model.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PositionMapper {

    int addPos(@Param("pos") Position pos);

    Position getPosByName(String name);

    List<Position> getAllPos();

    int deletePosById(@Param("pids") String[] pids);

    int updatePosById(@Param("pos") Position position);

    int insert(Position record);

    int insertSelective(Position record);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    int deleteByPrimaryKey(Integer id);

    Integer deletePositionsByIds(@Param("ids") Integer[] pids);
}
