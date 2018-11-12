/**
 * FileName: TemporaryService
 * Author:   sunny
 * Date:     2018/11/12 22:38
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface TemporaryService {

    void readContent(Workbook wb, Long projectId, Long versionId);
}