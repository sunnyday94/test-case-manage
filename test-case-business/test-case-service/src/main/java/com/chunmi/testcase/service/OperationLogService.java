/**
 * FileName: OperationLogService
 * Author:   sunny
 * Date:     2018/11/12 22:29
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.OperationLog;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.utils.PageBean;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface OperationLogService {

    Integer insertOperationLog(OperationLog operationLog);

    PageBean<OperationLog> selectOperationLogListByCondition(Users user, Integer pageCurrent, Integer pageSize,
                                                             Integer pageCount);

    Integer delOperationLog(OperationLog operationLog);

    OperationLog selectOperationLogDetailById(Long id);

}