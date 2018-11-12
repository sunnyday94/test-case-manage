/**
 * FileName: ProjectVersionServiceImpl
 * Author:   sunny
 * Date:     2018/11/12 22:38
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.mapper.ProjectVersionMapper;
import com.chunmi.testcase.model.po.ProjectVersion;
import com.chunmi.testcase.service.ProjectVersionService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
@Service
public class ProjectVersionServiceImpl implements ProjectVersionService {

    @Resource
    private ProjectVersionMapper projectVersionMapper;

    @Override
    public PageBean<ProjectVersion> selectProjectVersionByCondition(ProjectVersion projectVersion, Integer pageCurrent,
                                                                    Integer pageSize, Integer pageCount) {
        PageBean<ProjectVersion> pb = new PageBean<>();

        if(pageCurrent==0) pageCurrent =1;
        if(pageSize==0)  pageSize = 12;
        Integer rows = projectVersionMapper.selectProjectVersionCountsByCondition(projectVersion);
        pb.setRows(rows);

        pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

        pageCount = pageCount ==0 ? 1: pageCount;

        if(pageCurrent>=pageCount) {
            pb.setPageCurrent(pageCount);
        }
        PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
        pb.setPageSize(pageSize);
        pb.setPageCount(pageCount);
        pb.setObjectBean(projectVersion);
        List<ProjectVersion> projectVersionList = projectVersionMapper.selectProjectVersionByCondition(pageRequest,projectVersion);
        pb.setList(projectVersionList);
        return pb;
    }

    @Override
    public ProjectVersion seletProjectVersionByProjectIdAndVersionNum(ProjectVersion projectVersion) {
        return projectVersionMapper.seletProjectVersionByProjectIdAndVersionNum(projectVersion);
    }

    @Override
    public Integer addProjectVersion(ProjectVersion projectVersion) {
        return projectVersionMapper.insertSelective(projectVersion);
    }

}
