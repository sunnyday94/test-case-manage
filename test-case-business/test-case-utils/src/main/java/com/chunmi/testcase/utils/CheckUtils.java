/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-utils Mod.
 *
 * 版权所有(C) 唯存(上海)网络科技有限公司 2014-2023
 * Copyright 2014-2023 Vphotos TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * Vphotos Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Vphotos.
 *
 * File Created @ [2018年11月1日, 下午4:06:23 (CST)]
 */
package com.chunmi.testcase.utils;


import com.chunmi.testcase.utils.exception.ResultCodeEnum;
import com.chunmi.testcase.utils.exception.VPhotoException;
import org.springframework.beans.BeanUtils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 简述：对参数校验的方法<br>
 * 详细描述：<br>
 * 详细说明该类完成主要功能和注意点 <br>
 * 时间： 2018年08月02日 上午10:03:36 <br>
 * @version V1.0
 */
public class CheckUtils extends BaseUtils {

    private static final String NEW_FIELDS = "_NEW_FIELDS";
    public static final String FIELD_NAME_SEP = "#";

    /**
     * 手机号的规则
     */
    private static final String REGEX_MPHONE="^((13[\\d])|(14[\\d])|(15[\\d])|(17[\\d])|(18[\\d]))\\d{8}$";

    /**
     * 手机号的规则
     */
    private static final String REGEX_TPHONE="^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$";

    /**
     * email的规则
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 校验社会统一信用代码
     */
    private static final String REGEX_USCI = "[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y']{10}";

    /**
     * 组织结构代码
     */
    private static final String REGEX_ORGCD = "[a-zA-Z0-9]{8}-[a-zA-Z0-9]";


    /**
     * 判断对象obj是否是空
     * @param obj 对象
     * @param field 对应的对象名字或者字段名字 英文即可
     */
    public static void checkNull(Object obj,String field) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]为空");
//			throw new VPhotoException(VPhotoExceType.NULL,"属性["+field+"]为空");
        }
    }

    /**
     * 判断对象str是否是空
     * @param str 对象
     * @param field 对应的对象名字或者字段名字 英文即可
     */
    public static void checkNull(String str,String field) {
        if(isNull(str)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]为空");
//			throw new VPhotoException(VPhotoExceType.NULL,"属性["+field+"]为空");
        }
    }

    /**
     * 判断对象obj的所有属性是否为空
     * @param obj 待校验对象
     * @param fields 待校验对象的所有属性  逗号分割
     */
    public static void checkNulls(Object obj,String fields) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
//			throw new VPhotoException(VPhotoExceType.NULL,"属性[obj]为空");
        }
        if(isNull(fields))return;
        Map<String,String> fieldNames = getFieldsName(fields);
        String newFields = fieldNames.get(NEW_FIELDS);//新属性字符串
        Map<String,Object> values = getFieldsValue(obj, newFields);
        if(isNull(values)||values.size()<=0)return;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> value : values.entrySet()) {
            if(isNull(value.getValue()))sb.append("属性["+fieldNames.get(value.getKey())+"]为空").append("、");
        }
        if(sb.length()<=0)return;
//		throw new VPhotoException(VPhotoExceType.NULL,sb.toString());
        throw new VPhotoException(ResultCodeEnum.参数异常,sb.toString());
    }

    /**
     * 不能同时为空
     * 主要是用来判断某些值月另外的一些字段不能同时为空
     * @param obj
     * @param fields  属性字符串逗号分割 例如：a,b,c","d,e,f"  表示 abc属于和def属性不能同时为空
     */
    public static void checkNotAllNulls(Object obj,String ...fields) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
//			throw new VPhotoException(VPhotoExceType.NULL,"属性[obj]为空");
        }
        if(isNull(fields)||fields.length<=0)return;
        Map<String,String> map = new HashMap<String,String>();
        Map<String,Map<String,String>> newNames = new HashMap<String,Map<String,String>>();
        Map<String,Object> values = null;
        StringBuffer sb = new StringBuffer();
        Map<String,String> fieldNames = null;
        String newFields = null;
        for(String field:fields) {
            sb.setLength(0);
            fieldNames = getFieldsName(field);//获取对应的名称
            newFields = fieldNames.get(NEW_FIELDS);//新属性字符串
            values = getFieldsValue(obj, newFields);
            if(isNull(values)||values.size()<=0) {
                map.put(field, sb.toString());
                continue;
            }
            for (Map.Entry<String, Object> value : values.entrySet()) {
                if(isNull(value.getValue()))sb.append("属性["+fieldNames.get(value.getKey())+"]为空").append("、");
            }
            map.put(newFields, sb.toString());
            newNames.put(newFields, fieldNames);//新的名字
        }
        //==校验 是否全部都大于0 如果是说明有值是没填写的
        boolean flg = false; //是否全部是空的
        sb.setLength(0);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(BaseUtils.isNotNull(entry.getValue())) {//含有未填写的
                sb.append("属性[").append(getNewFileNames(newNames.get(entry.getKey()),entry.getKey())).append("]").append("、");
            }else {
                flg = true;
            }
        }
        //==只有同时为空的时候才抛异常
        if(!flg) {
            sb.setCharAt(sb.length()-1,'，');
            throw new VPhotoException(ResultCodeEnum.参数异常,sb.toString()+"不能同时为空");
        }
    }

    /**
     * id转换成名字
     * @param fieldNames
     * @param newFields
     * @return
     */
    private static String getNewFileNames(Map<String,String> fieldNames,String newFields ) {
        if(isNull(fieldNames)||isNull(newFields))return "";
        StringBuffer names = new StringBuffer();
        for(String one:newFields.split(",")) {
            if(isNull(one))continue;
            names.append(fieldNames.get(one)).append(",");
        }
        if(names.length()>0)names.setLength(names.length()-1);
        return names.toString();
    }

    /**
     * 无效数字
     * @param num 待校验的数字
     * @param field 对应的属性名称
     */
    public static void checkNum(String num,String field) {
        if(!isNumber(num)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+num+"]，为非数字");
        }
    }
    /**
     * 检查对象的属性是否是数字
     * @param obj 待检查对象
     * @param fields 所有属性值 逗号分割
     */
    public static void checkNums(Object obj,String fields) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
        }
        if(isNull(fields))return;
        Map<String,String> fieldNames = getFieldsName(fields);
        String newFields = fieldNames.get(NEW_FIELDS);//新属性字符串
        Map<String,Object> values = getFieldsValue(obj, newFields);
        if(isNull(values)||values.size()<=0)return;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> value : values.entrySet()) {
            if(!isNumber(null2string(value.getValue())))sb.append("属性["+fieldNames.get(value.getKey())+"]值为["+value.getValue()+"]，为非数字").append("、");
        }
        if(sb.length()<=0)return;
        throw new VPhotoException(ResultCodeEnum.参数异常,sb.toString());
    }

    /**
     * 无效小数
     * @param num 待校验的小数
     * @param field 对应的属性名称
     */
    public static void checkDecml(String num,String field) {
        if(!isDecimal(num)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+num+"]，为非数字");
        }
    }

    /**
     * 检查对象的属性是否是数字
     * @param obj 待检查对象
     * @param fields 所有属性值 逗号分割
     */
    public static void checkDecmls(Object obj,String fields) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
        }
        if(isNull(fields))return;
        Map<String,String> fieldNames = getFieldsName(fields);
        String newFields = fieldNames.get(NEW_FIELDS);//新属性字符串
        Map<String,Object> values = getFieldsValue(obj, newFields);
        if(isNull(values)||values.size()<=0)return;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> value : values.entrySet()) {
            if(!isDecimal(null2string(value.getValue())))sb.append("属性["+fieldNames.get(value.getKey())+"]值为["+value.getValue()+"]，为非数字").append("、");
        }
        if(sb.length()<=0)return;
        throw new VPhotoException(ResultCodeEnum.参数异常,sb.toString());
    }

    /**
     * 无效日期格式
     * @param date 待校验的日期字符串
     * @param field 对应的属性名称
     */
    public static void checkDate(String date,String field) {
        if(!DateUtils.isDateString(date)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+date+"]，为非日期格式，默认格式为[yyyy-MM-dd、yyyy-MM-dd HH:mm:ss]");
        }
    }

    /**
     * 检查对象的属性是否是日期
     * @param obj 待检查对象
     * @param fields 所有属性值 逗号分割
     */
    public static void checkDates(Object obj,String fields) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
        }
        if(isNull(fields))return;
        Map<String,String> fieldNames = getFieldsName(fields);
        String newFields = fieldNames.get(NEW_FIELDS);//新属性字符串
        Map<String,Object> values = getFieldsValue(obj, newFields);
        if(isNull(values)||values.size()<=0)return;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> value : values.entrySet()) {
            if(!DateUtils.isDateString(null2string(value.getValue())))sb.append("属性["+fieldNames.get(value.getKey())+"]值为["+value.getValue()+"]，为非日期格式").append("、");
        }
        if(sb.length()<=0)return;
        sb.append("默认格式为[yyyy-MM-dd、yyyy-MM-dd HH:mm:ss]");
        throw new VPhotoException(ResultCodeEnum.参数异常,sb.toString());
    }

    /**
     * 无效日期格式判断
     * @param date 日期字符串
     * @param formatString 日期格式
     * @param field 对应属性名称
     */
    public static void checkDate(String date,String field,String formatString) {
        if(DateUtils.isDateString(date,formatString)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+date+"]，为非日期格式，指定格式为["+formatString+"]");
        }
    }

    /**
     * 检查对象的属性是否是日期
     * @param obj 待检查对象
     * @param fields 所有属性值 逗号分割
     */
    public static void checkDates(Object obj,String fields,String formatString) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
        }
        if(isNull(fields))return;
        Map<String,String> fieldNames = getFieldsName(fields);
        String newFields = fieldNames.get(NEW_FIELDS);//新属性字符串
        Map<String,Object> values = getFieldsValue(obj, newFields);
        if(isNull(values)||values.size()<=0)return;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> value : values.entrySet()) {
            if(!DateUtils.isDateString(null2string(value.getValue()),formatString))sb.append("属性["+fieldNames.get(value.getKey())+"]值为["+value.getValue()+"]，为非日期格式").append("、");
        }
        if(sb.length()<=0)return;
        sb.append("指定格式为["+formatString+"]");
        throw new VPhotoException(ResultCodeEnum.参数异常,sb.toString());
    }

    /**
     * 校验长度
     * @param str 待校验的字符串
     * @param min 最短长度 可以为0，为0不校验
     * @param max 大查高难度 可以为0 为0不校验
     * @param field 对应字段
     */
    public static void checkLength(String str,int min,int max,String field) {
        if(isNull(str)) {
            checkNull(str, field);
        }
        int len = str.trim().length();
        if(min>0) {
            if(len<min) {
                throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+str+"]，长度短于最小长度["+min+"]");
            }
        }
        if(max>0) {
            if(len>max) {
                throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+str+"]，长度大于最大长度["+max+"]");
            }
        }
    }

    /**
     * 检查对象的属性是否满足长度
     * 这里只做长度校验 不做空校验 即如果值为空 不会抛出异常
     * @param obj 待检查对象
     * @param fields 格式：field-min-max,field-min-max
     */
    public static void checkLengths(Object obj,String fields) {
        if(isNull(obj)) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性[obj]为空");
        }
        if(isNull(fields))return;
        String newFields = "";
        Map<String,String> fieldMap = new HashMap<String,String>();
        String tmp[] = null;
        for(String field:fields.split(",")) {
            if(isNull(field))continue;
            tmp = field.split("-");
            if(tmp==null||tmp.length<3)continue;
            newFields += tmp[0]+",";
            fieldMap.put(tmp[0], tmp[1]+"-"+tmp[2]);
        }
        if(newFields.endsWith(","))newFields = newFields.substring(0,newFields.length()-1);
        Map<String,Object> values = getFieldsValue(obj, newFields);
        if(isNull(values)||values.size()<=0)return;
        StringBuffer sbShort = new StringBuffer();
        StringBuffer sbLong =  new StringBuffer();
        String v = null;
        int min,max;
        for (Map.Entry<String, Object> value : values.entrySet()) {
            min = string2int(fieldMap.get(value.getKey()).split("-")[0]);
            max = string2int(fieldMap.get(value.getKey()).split("-")[1]);
            v = null2string(value.getValue());
            if(isNull(v))continue;//不做空校验，值为空不会抛出异常
            int len = v.trim().length();
            if(min>0) {
                if(len<min) {
                    sbShort.append("属性["+value.getKey()+"]值为["+v+"]，长度短于最小长度["+min+"]").append("、");
                }
            }
            if(max>0) {
                if(len>max) {
                    sbLong.append("属性["+value.getKey()+"]值为["+v+"]，长度大于最大长度["+max+"]").append("、");
                }
            }
        }
        if(sbShort.length()<=0&&sbLong.length()>0) {
            throw new VPhotoException(ResultCodeEnum.参数异常,sbLong.toString());
        }else if(sbShort.length()>0&&sbLong.length()<=0) {
            throw new VPhotoException(ResultCodeEnum.参数异常,sbLong.toString());
        }else if(sbShort.length()>0&&sbLong.length()>0) {//如果都有 按照小于的类型输出
            throw new VPhotoException(ResultCodeEnum.参数异常,sbShort.toString()+sbLong.toString());
        }else {
            //没有异常
        }
    }

    /**
     * 获取对象部分属性值
     * @param obj
     * @param fields
     * @return
     */
    private static Map<String,Object> getFieldsValue(Object obj,String fields){
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(obj.getClass());
        List<String> proList = (fields != null ? Arrays.asList(fields.split(",")) : null);
        Method readMethod = null;
        Object value = null;
        Map<String,Object> values = new HashMap<String,Object>();
        for (PropertyDescriptor pd : pds) {
            if(!proList.contains(pd.getName()))continue;//如果传入了需要复制的属性 并且当前属性不在列表内 则不需要复制
            try {
                readMethod = pd.getReadMethod();
                value = readMethod.invoke(obj);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                value = null;
            }
            values.put(pd.getName(), value);
        }
        return values;
    }
    /**
     * 获取feild对应的name
     * @param fields
     * @return
     */
    private static Map<String,String> getFieldsName(String fields){
        List<String> proList = (fields != null ? Arrays.asList(fields.split(",")) : null);
        StringBuffer newFields = new StringBuffer();
        Map<String,String> fieldNames = new HashMap<String,String>();
        String fieldName[] = null;
        if(proList!=null) {
            for(String one:proList) {
                if(isNull(one))continue;
                if(one.indexOf(FIELD_NAME_SEP)<0) { //未传入名字
                    fieldNames.put(one, one);
                    newFields.append(one).append(",");
                }else {
                    fieldName = one.split(FIELD_NAME_SEP);
                    fieldNames.put(fieldName[0], fieldName[1]);
                    newFields.append(fieldName[0]).append(",");
                }
            }
        }
        fieldNames.put(NEW_FIELDS, newFields.toString());
        return fieldNames;
    }


    /**
     * 判断是否为一个座机号
     * @return
     */
    public static boolean checkMPhone(String phoneNumber,String field){
        if(isNull(phoneNumber)){
            return false;
        }
        Pattern p = Pattern.compile(REGEX_MPHONE);
        if(!p.matcher(phoneNumber).matches()) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+phoneNumber+"],不是手机号");
        }
        return true;
    }

    /**
     * 判断是否为一个座机号
     * @return
     */
    public static boolean checkTPhone(String phoneNumber,String field){
        if(isNull(phoneNumber)){
            return false;
        }
        Pattern p = Pattern.compile(REGEX_TPHONE);
        if(!p.matcher(phoneNumber).matches()) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+phoneNumber+"],不是座机号");
        }
        return true;
    }

    /**
     * 判断是否为一个手机号
     * @return
     */
    public static boolean checkEmail(String email,String field){
        if(isNull(email)){
            return false;
        }
        Pattern p = Pattern.compile(REGEX_EMAIL);
        if(!p.matcher(email).matches()) {
            throw new VPhotoException(ResultCodeEnum.参数异常,"属性["+field+"]值为["+email+"],不是手机号");
        }
        return true;
    }

}
