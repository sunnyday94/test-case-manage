/**
 * FileName: ProjectModuleServiceImpl
 * Author:   sunny
 * Date:     2018/11/12 22:33
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.mapper.ProjectModuleMapper;
import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;
import com.fasterxml.jackson.databind.Module;
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
public class ProjectModuleServiceImpl implements ProjectModuleService {

    @Resource
    private ProjectModuleMapper projectModuleMapper;

    @Override
    public PageBean<ProjectModule> selectModuleListByCondition(ProjectModule projectModule, Integer pageCurrent,
                                                               Integer pageSize, Integer pageCount) {
        PageBean<ProjectModule> pb = new PageBean<>();

        if(pageCurrent==0) pageCurrent =1;
        if(pageSize==0)  pageSize = 12;
        Integer rows = projectModuleMapper.selectModuleCountsByCondition(projectModule);
        pb.setRows(rows);

        pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

        pageCount = pageCount ==0 ? 1: pageCount;

        if(pageCurrent>=pageCount) {
            pb.setPageCurrent(pageCount);
        }
        PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
        pb.setPageSize(pageSize);
        pb.setPageCount(pageCount);
        pb.setObjectBean(projectModule);
        List<ProjectModule> projectModuleList = projectModuleMapper.selectModuleListByCondition(pageRequest,projectModule);
        pb.setList(projectModuleList);
        return pb;
    }

    @Override
    public ProjectModule selectModuleByProjectIdAndModuleName(ProjectModule projectModule) {
        return projectModuleMapper.selectModuleByProjectIdAndModuleName(projectModule);
    }

    @Override
    public Integer addProjectModule(ProjectModule projectModule) {
        return projectModuleMapper.insertSelective(projectModule);
    }

    @Override
    public Integer delModule(ProjectModule projectModule) {
        return projectModuleMapper.delModule(projectModule);
    }

    @Override
    public List<Module> selectAllModuleList() {
        return projectModuleMapper.selectAllModuleList();
    }

    @Override
    public Integer insertNewModule(ProjectModule pm) {
        return projectModuleMapper.insertSelective(pm);
    }

}