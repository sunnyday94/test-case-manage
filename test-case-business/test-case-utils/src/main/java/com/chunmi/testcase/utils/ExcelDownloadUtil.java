/**
 * FileName: ExcelDownloadUtil
 * Author:   sunny
 * Date:     2018/10/16 0:40
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.utils;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/10/16
 * @since 1.0.0
 */

import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.Number;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: Excel导出
 * @Author: sunny
 * @Date: 0:33 2018/10/16
 */
public class ExcelDownloadUtil {



    /**导出Excel文件，支持一个Sheet
     * @param response
     * @param excelName
     * @param columnMap
     * @param dataList
     */
    public static void downloadUtil(HttpServletResponse response, String excelName, LinkedHashMap<String,String> columnMap, Collection dataList){
        List<ExcelSheet> excelSheets=new ArrayList<ExcelSheet>();
        ExcelSheet sheet=ExcelSheet.instans();
        sheet.setColumnMap(columnMap);
        sheet.setDataList(dataList);
        excelSheets.add(sheet);
        downloadUtil(response, excelName, excelSheets);
    }

    /**导出Excel文件，支持多个Sheet
     * @param response
     * @param excelName 文件名
     * @param excelSheets List《ExcelSheet》
     */
    public static void downloadUtil(HttpServletResponse response,String excelName,List<ExcelSheet> excelSheets){
        try{
            OutputStream os = response.getOutputStream(); // 取得输出流
            response.reset(); // 清空输出流
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(excelName+".xls", "UTF-8")); // 设定输出文件头
            response.setContentType("application/msexcel"); // 定义输出类型


            //创建文件
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            //
            int i=0;
            for(ExcelSheet sheet:excelSheets) {
                createSheet(workbook,sheet,i);
                i++;
            }
            workbook.write(); // 写入文件
            workbook.close();
            os.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("下载失败");
        }
    }

    /**写入数据到sheet对象
     * @param workbook
     * @param excelSheet
     * @throws RowsExceededException
     * @throws WriteException
     */
    private static void createSheet(WritableWorkbook workbook,ExcelSheet excelSheet,int number) throws RowsExceededException, WriteException {
        String[] titles = new String[excelSheet.getColumnMap().size()];
        String[] columns = new String[excelSheet.getColumnMap().size()];

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat decimal = new DecimalFormat("0.00");
        int f = 0;
        for (Map.Entry<String,String> entry : excelSheet.getColumnMap().entrySet()) {
            titles[f] = entry.getValue();
            columns[f] = entry.getKey();
            f++;
        }
        WritableSheet wsheet = workbook.createSheet(excelSheet.getSheetName(), number);
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
                jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        for (int i = 0; i < titles.length; i++) {
            Label wlabel1 = new Label(i, 0, String.valueOf(titles[i]), format); // 行、列、单元格中的文本、文本格式
            wsheet.addCell(wlabel1);
        }
        font = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false,
                jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        format = new WritableCellFormat(font);

        for (int i = 0; i < titles.length; i++) {
            wsheet.setColumnView(i, 25);// 设置宽度
        }

        f = 1;
        for (Object obj : excelSheet.getDataList()) {
            for (int i = 0; i < columns.length; i++) {
                String columnName = columns[i];
                try{
                    Object val = null;
                    if(obj instanceof Map){
                        Map objMap = (Map) obj;
                        val = objMap.get(columnName);
                    }else{
                        val = PropertyUtils.getNestedProperty(obj, columnName);
                    }
                    //替换指定列值
                    if(val != null){
                        if(excelSheet.getDictMap()!=null && excelSheet.getDictMap().containsKey(columnName+"_"+val)){
                            val = excelSheet.getDictMap().get(columnName+"_"+val);
                        }
                    }else{
                        if(excelSheet.getNullDefault()!=null && excelSheet.getNullDefault().containsKey(columnName)){
                            val = excelSheet.getNullDefault().get(columnName);
                        }
                    }

                    if(val!=null && (val instanceof Integer || val instanceof Long ||   val instanceof Short)){
                        jxl.write.Number test = new jxl.write.Number(i, f, val==null?0:((Number)val).doubleValue(), format);
                        wsheet.addCell(test);
                    }else{
                        if (val!=null && val instanceof Date) {
                            Date date = (Date) val;
                            val = df.format(date);
                        }else if (val!=null && val instanceof Double){
                            val = decimal.format(val);
                        }
                        Label wlabel1 = new Label(i, f, val==null?"":val.toString(), format);
                        wsheet.addCell(wlabel1);
                    }
                }catch(Exception e){

                }
            }
            f++;
        }

    }

    /**Excel的Sheet属性
     * @author yangkunguo
     *
     */
    public static class ExcelSheet{

        private String sheetName;
        private LinkedHashMap<String,String> columnMap;//列名
        private Collection<?> dataList;//数据
        private Map<String,String> dictMap;//数据转换
        private Map<String,String> nullDefault;

        public static ExcelSheet instans() {
            return new ExcelSheet();
        }

        public String getSheetName() {
            return sheetName;
        }
        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }
        public LinkedHashMap<String, String> getColumnMap() {
            return columnMap;
        }
        public void setColumnMap(LinkedHashMap<String, String> columnMap) {
            this.columnMap = columnMap;
        }
        public Collection<?> getDataList() {
            return dataList;
        }
        public void setDataList(Collection<?> dataList) {
            this.dataList = dataList;
        }
        public Map<String, String> getDictMap() {
            return dictMap;
        }
        public void setDictMap(Map<String, String> dictMap) {
            this.dictMap = dictMap;
        }
        public Map<String, String> getNullDefault() {
            return nullDefault;
        }
        public void setNullDefault(Map<String, String> nullDefault) {
            this.nullDefault = nullDefault;
        }


    }
}
