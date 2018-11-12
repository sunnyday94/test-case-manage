/**
 * FileName: ProjectService
 * Author:   sunny
 * Date:     2018/11/12 22:34
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.Project;
import com.chunmi.testcase.utils.PageBean;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface ProjectService {

    PageBean<Project> selectProjectListByCondition(Project project, Integer pageCurrent, Integer pageSize,
                                                   Integer pageCount);

    Project selectProjectByName(String projectName);

    Integer addProject(Project project);

    Integer delProjectById(Long id);

}
