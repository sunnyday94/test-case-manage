/**
 * FileName: ProjectServiceImpl
 * Author:   sunny
 * Date:     2018/11/12 22:35
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.mapper.ProjectMapper;
import com.chunmi.testcase.model.po.Project;
import com.chunmi.testcase.service.ProjectService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageBean<Project> selectProjectListByCondition(Project project, Integer pageCurrent, Integer pageSize,
                                                          Integer pageCount) {
        PageBean<Project> pb = new PageBean<>();
        //�ж�
        if(pageCurrent==0) pageCurrent =1;
        if(pageSize==0)  pageSize = 12;
        Integer rows = projectMapper.selectProjectCountsByCondition(project);
        pb.setRows(rows);

        pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

        pageCount = pageCount ==0 ? 1: pageCount;

        if(pageCurrent>=pageCount) {
            pb.setPageCurrent(pageCount);
        }
        PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
        pb.setPageSize(pageSize);
        pb.setPageCount(pageCount);
        pb.setObjectBean(project);     
        List<Project> projectList = projectMapper.selectProjectListByCondition(project,pageRequest);
        pb.setList(projectList);
        return pb;
    }

    @Override
    public Project selectProjectByName(String projectName) {
        return projectMapper.selectProjectByName(projectName);
    }

    @Override
    public Integer addProject(Project project) {
        return projectMapper.insertSelective(project);
    }

    @Override
    public Integer delProjectById(Long id) {
        return projectMapper.delProjectById(id);
    }

}
