/**
 * FileName: ActualResultServiceImpl
 * Author:   sunny
 * Date:     2018/11/12 22:28
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.mapper.ActualResultMapper;
import com.chunmi.testcase.model.po.ActualResult;
import com.chunmi.testcase.service.ActualResultService;
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
public class ActualResultServiceImpl implements ActualResultService {

    @Resource
    private ActualResultMapper actualResultMapper;

    @Override
    public List<ActualResult> selectAllActualResultList() {
        return actualResultMapper.selectAllActualResultList();
    }

}