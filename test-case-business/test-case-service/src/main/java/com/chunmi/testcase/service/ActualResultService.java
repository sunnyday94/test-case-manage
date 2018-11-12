/**
 * FileName: ActualResultService
 * Author:   sunny
 * Date:     2018/11/12 22:27
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
import java.util.List;

import com.chunmi.testcase.model.po.ActualResult;

public interface ActualResultService {

    List<ActualResult> selectAllActualResultList();

}
