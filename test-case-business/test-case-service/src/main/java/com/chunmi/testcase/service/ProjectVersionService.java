/**
 * FileName: ProjectVersionService
 * Author:   sunny
 * Date:     2018/11/12 22:36
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.ProjectVersion;
import com.chunmi.testcase.utils.PageBean;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface ProjectVersionService {

    PageBean<ProjectVersion> selectProjectVersionByCondition(ProjectVersion projectVersion, Integer pageCurrent,
                                                             Integer pageSize, Integer pageCount);

    ProjectVersion seletProjectVersionByProjectIdAndVersionNum(ProjectVersion projectVersion);

    Integer addProjectVersion(ProjectVersion projectVersion);

}
