package org.chuanshen.devladder.mapper;

import org.chuanshen.devladder.model.JobLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobLevelMapper {
    JobLevel selectByPrimaryKey(Integer id);

    JobLevel getJobLevelByName(String name);

    List<JobLevel> getAllJobLevels();

    int addJobLevel(@Param("jobLevel") JobLevel jobLevel);

    // int deleteJobLevelsByIds(@Param("ids") String[] ids);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobLevel jobLevel);

    int updateJobLevel(@Param("jobLevel") JobLevel jobLevel);

    Integer deleteJobLevelsByIds(@Param("ids") Long[] ids);
}
