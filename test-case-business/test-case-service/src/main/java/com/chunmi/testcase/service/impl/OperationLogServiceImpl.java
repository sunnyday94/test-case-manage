/**
 * FileName: OperationLogServiceImpl
 * Author:   sunny
 * Date:     2018/11/12 22:30
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.mapper.OperationLogMapper;
import com.chunmi.testcase.model.po.OperationLog;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.service.OperationLogService;
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
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    @Override
    public Integer insertOperationLog(OperationLog operationLog) {
        return operationLogMapper.insertSelective(operationLog);
    }

    @Override
    public PageBean<OperationLog> selectOperationLogListByCondition(Users user, Integer pageCurrent, Integer pageSize,
                                                                    Integer pageCount) {
        PageBean<OperationLog> pb = new PageBean<>();
        //判断
        if(pageSize == 0) pageSize = 12;         //每页条目数
        if(pageCurrent == 0) pageCurrent = 1;    //当前页
        Integer rows = operationLogMapper.selectOperationLogsCountsByCondition(user).intValue();
        pb.setRows(rows);
        //计算分页
        pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
        //设置分页
        pageCount = pageCount ==0 ? 1: pageCount;

        if(pageCurrent>=pageCount) {
            pb.setPageCurrent(pageCount);
        }
        PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
        pb.setPageSize(pageSize);
        pb.setPageCount(pageCount);
        pb.setObjectBean(user);
        List<OperationLog> operationLogs = operationLogMapper.selectOperationLogListByCondition(user,pageRequest);
        pb.setList(operationLogs);
        return pb;
    }

    @Override
    public Integer delOperationLog(OperationLog operationLog) {
        return operationLogMapper.delOperationLog(operationLog);
    }

    @Override
    public OperationLog selectOperationLogDetailById(Long id) {
        return operationLogMapper.selectOperationLogDetailById(id);
    }

}