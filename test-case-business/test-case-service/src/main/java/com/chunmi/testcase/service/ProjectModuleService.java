/**
 * FileName: ProjectModuleService
 * Author:   sunny
 * Date:     2018/11/12 22:32
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.utils.PageBean;
import com.fasterxml.jackson.databind.Module;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface ProjectModuleService {

    PageBean<ProjectModule> selectModuleListByCondition(ProjectModule projectModule, Integer pageCurrent,
                                                        Integer pageSize, Integer pageCount);

    ProjectModule selectModuleByProjectIdAndModuleName(ProjectModule projectModule);

    Integer addProjectModule(ProjectModule projectModule);

    Integer delModule(ProjectModule projectModule);

    List<Module> selectAllModuleList();

    Integer insertNewModule(ProjectModule pm);

}