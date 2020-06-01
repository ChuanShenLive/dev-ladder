package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.JobLevelMapper;
import org.chuanshen.devladder.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobLevelService {

    @Autowired
    private JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    public Integer addJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.addJobLevel(jobLevel);
    }

    public Integer updateJobLevelById(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobLevelById(Long id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteJobLevelsByIds(Long[] ids) {
        return jobLevelMapper.deleteJobLevelsByIds(ids);
    }
}
