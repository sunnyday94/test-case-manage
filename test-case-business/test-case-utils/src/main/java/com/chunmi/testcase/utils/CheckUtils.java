/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-utils Mod.
 *
 * ��Ȩ����(C) Ψ��(�Ϻ�)����Ƽ����޹�˾ 2014-2023
 * Copyright 2014-2023 Vphotos TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * Vphotos Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Vphotos.
 *
 * File Created @ [2018��11��1��, ����4:06:23 (CST)]
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
 * �������Բ���У��ķ���<br>
 * ��ϸ������<br>
 * ��ϸ˵�����������Ҫ���ܺ�ע��� <br>
 * ʱ�䣺 2018��08��02�� ����10:03:36 <br>
 * @version V1.0
 */
public class CheckUtils extends BaseUtils {

	private static final String NEW_FIELDS = "_NEW_FIELDS";
	public static final String FIELD_NAME_SEP = "#";

	/**
	 * �ֻ��ŵĹ���
	 */
	private static final String REGEX_MPHONE="^((13[\\d])|(14[\\d])|(15[\\d])|(17[\\d])|(18[\\d]))\\d{8}$";

	/**
	 * �ֻ��ŵĹ���
	 */
	private static final String REGEX_TPHONE="^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$";

	/**
	 * email�Ĺ���
	 */
	private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * У�����ͳһ���ô���
	 */
	private static final String REGEX_USCI = "[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y']{10}";

	/**
	 * ��֯�ṹ����
	 */
	private static final String REGEX_ORGCD = "[a-zA-Z0-9]{8}-[a-zA-Z0-9]";


	/**
	 * �ж϶���obj�Ƿ��ǿ�
	 * @param obj ����
	 * @param field ��Ӧ�Ķ������ֻ����ֶ����� Ӣ�ļ���
	 */
	public static void checkNull(Object obj,String field) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]Ϊ��");
//			throw new VPhotoException(VPhotoExceType.NULL,"����["+field+"]Ϊ��");
		}
	}

	/**
	 * �ж϶���str�Ƿ��ǿ�
	 * @param str ����
	 * @param field ��Ӧ�Ķ������ֻ����ֶ����� Ӣ�ļ���
	 */
	public static void checkNull(String str,String field) {
		if(isNull(str)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]Ϊ��");
//			throw new VPhotoException(VPhotoExceType.NULL,"����["+field+"]Ϊ��");
		}
	}

	/**
	 * �ж϶���obj�����������Ƿ�Ϊ��
	 * @param obj ��У�����
	 * @param fields ��У��������������  ���ŷָ�
	 */
	public static void checkNulls(Object obj,String fields) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
//			throw new VPhotoException(VPhotoExceType.NULL,"����[obj]Ϊ��");
		}
		if(isNull(fields))return;
		Map<String,String> fieldNames = getFieldsName(fields);
		String newFields = fieldNames.get(NEW_FIELDS);//�������ַ���
		Map<String,Object> values = getFieldsValue(obj, newFields);
		if(isNull(values)||values.size()<=0)return;
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> value : values.entrySet()) {
			if(isNull(value.getValue()))sb.append("����["+fieldNames.get(value.getKey())+"]Ϊ��").append("��");
		}
		if(sb.length()<=0)return;
//		throw new VPhotoException(VPhotoExceType.NULL,sb.toString());
		throw new VPhotoException(ResultCodeEnum.�����쳣,sb.toString());
	}

	/**
	 * ����ͬʱΪ��
	 * ��Ҫ�������ж�ĳЩֵ�������һЩ�ֶβ���ͬʱΪ��
	 * @param obj
	 * @param fields  �����ַ������ŷָ� ���磺a,b,c","d,e,f"  ��ʾ abc���ں�def���Բ���ͬʱΪ��
	 */
	public static void checkNotAllNulls(Object obj,String ...fields) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
//			throw new VPhotoException(VPhotoExceType.NULL,"����[obj]Ϊ��");
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
			fieldNames = getFieldsName(field);//��ȡ��Ӧ������
			newFields = fieldNames.get(NEW_FIELDS);//�������ַ���
			values = getFieldsValue(obj, newFields);
			if(isNull(values)||values.size()<=0) {
				map.put(field, sb.toString());
				continue;
			}
			for (Map.Entry<String, Object> value : values.entrySet()) {
				if(isNull(value.getValue()))sb.append("����["+fieldNames.get(value.getKey())+"]Ϊ��").append("��");
			}
			map.put(newFields, sb.toString());
			newNames.put(newFields, fieldNames);//�µ�����
		}
		//==У�� �Ƿ�ȫ��������0 �����˵����ֵ��û��д��
		boolean flg = false; //�Ƿ�ȫ���ǿյ�
		sb.setLength(0);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			 if(BaseUtils.isNotNull(entry.getValue())) {//����δ��д��
				 sb.append("����[").append(getNewFileNames(newNames.get(entry.getKey()),entry.getKey())).append("]").append("��");
			 }else {
				 flg = true;
			 }
		}
		//==ֻ��ͬʱΪ�յ�ʱ������쳣
		if(!flg) {
			sb.setCharAt(sb.length()-1,'��');
			throw new VPhotoException(ResultCodeEnum.�����쳣,sb.toString()+"����ͬʱΪ��");
		}
	}

	/**
	 * idת��������
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
	 * ��Ч����
	 * @param num ��У�������
	 * @param field ��Ӧ����������
	 */
	public static void checkNum(String num,String field) {
		if(!isNumber(num)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+num+"]��Ϊ������");
		}
	}
	/**
	 * ������������Ƿ�������
	 * @param obj ��������
	 * @param fields ��������ֵ ���ŷָ�
	 */
	public static void checkNums(Object obj,String fields) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
		}
		if(isNull(fields))return;
		Map<String,String> fieldNames = getFieldsName(fields);
		String newFields = fieldNames.get(NEW_FIELDS);//�������ַ���
		Map<String,Object> values = getFieldsValue(obj, newFields);
		if(isNull(values)||values.size()<=0)return;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> value : values.entrySet()) {
			if(!isNumber(null2string(value.getValue())))sb.append("����["+fieldNames.get(value.getKey())+"]ֵΪ["+value.getValue()+"]��Ϊ������").append("��");
		}
		if(sb.length()<=0)return;
		throw new VPhotoException(ResultCodeEnum.�����쳣,sb.toString());
	}

	/**
	 * ��ЧС��
	 * @param num ��У���С��
	 * @param field ��Ӧ����������
	 */
	public static void checkDecml(String num,String field) {
		if(!isDecimal(num)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+num+"]��Ϊ������");
		}
	}

	/**
	 * ������������Ƿ�������
	 * @param obj ��������
	 * @param fields ��������ֵ ���ŷָ�
	 */
	public static void checkDecmls(Object obj,String fields) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
		}
		if(isNull(fields))return;
		Map<String,String> fieldNames = getFieldsName(fields);
		String newFields = fieldNames.get(NEW_FIELDS);//�������ַ���
		Map<String,Object> values = getFieldsValue(obj, newFields);
		if(isNull(values)||values.size()<=0)return;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> value : values.entrySet()) {
			if(!isDecimal(null2string(value.getValue())))sb.append("����["+fieldNames.get(value.getKey())+"]ֵΪ["+value.getValue()+"]��Ϊ������").append("��");
		}
		if(sb.length()<=0)return;
		throw new VPhotoException(ResultCodeEnum.�����쳣,sb.toString());
	}

	/**
	 * ��Ч���ڸ�ʽ
	 * @param date ��У��������ַ���
	 * @param field ��Ӧ����������
	 */
	public static void checkDate(String date,String field) {
		if(!DateUtils.isDateString(date)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+date+"]��Ϊ�����ڸ�ʽ��Ĭ�ϸ�ʽΪ[yyyy-MM-dd��yyyy-MM-dd HH:mm:ss]");
		}
	}

	/**
	 * ������������Ƿ�������
	 * @param obj ��������
	 * @param fields ��������ֵ ���ŷָ�
	 */
	public static void checkDates(Object obj,String fields) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
		}
		if(isNull(fields))return;
		Map<String,String> fieldNames = getFieldsName(fields);
		String newFields = fieldNames.get(NEW_FIELDS);//�������ַ���
		Map<String,Object> values = getFieldsValue(obj, newFields);
		if(isNull(values)||values.size()<=0)return;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> value : values.entrySet()) {
			if(!DateUtils.isDateString(null2string(value.getValue())))sb.append("����["+fieldNames.get(value.getKey())+"]ֵΪ["+value.getValue()+"]��Ϊ�����ڸ�ʽ").append("��");
		}
		if(sb.length()<=0)return;
		sb.append("Ĭ�ϸ�ʽΪ[yyyy-MM-dd��yyyy-MM-dd HH:mm:ss]");
		throw new VPhotoException(ResultCodeEnum.�����쳣,sb.toString());
	}

	/**
	 * ��Ч���ڸ�ʽ�ж�
	 * @param date �����ַ���
	 * @param formatString ���ڸ�ʽ
	 * @param field ��Ӧ��������
	 */
	public static void checkDate(String date,String field,String formatString) {
		if(DateUtils.isDateString(date,formatString)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+date+"]��Ϊ�����ڸ�ʽ��ָ����ʽΪ["+formatString+"]");
		}
	}

	/**
	 * ������������Ƿ�������
	 * @param obj ��������
	 * @param fields ��������ֵ ���ŷָ�
	 */
	public static void checkDates(Object obj,String fields,String formatString) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
		}
		if(isNull(fields))return;
		Map<String,String> fieldNames = getFieldsName(fields);
		String newFields = fieldNames.get(NEW_FIELDS);//�������ַ���
		Map<String,Object> values = getFieldsValue(obj, newFields);
		if(isNull(values)||values.size()<=0)return;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> value : values.entrySet()) {
			if(!DateUtils.isDateString(null2string(value.getValue()),formatString))sb.append("����["+fieldNames.get(value.getKey())+"]ֵΪ["+value.getValue()+"]��Ϊ�����ڸ�ʽ").append("��");
		}
		if(sb.length()<=0)return;
		sb.append("ָ����ʽΪ["+formatString+"]");
		throw new VPhotoException(ResultCodeEnum.�����쳣,sb.toString());
	}

	/**
	 * У�鳤��
	 * @param str ��У����ַ���
	 * @param min ��̳��� ����Ϊ0��Ϊ0��У��
	 * @param max �����Ѷ� ����Ϊ0 Ϊ0��У��
	 * @param field ��Ӧ�ֶ�
	 */
	public static void checkLength(String str,int min,int max,String field) {
		if(isNull(str)) {
			checkNull(str, field);
		}
		int len = str.trim().length();
		if(min>0) {
			if(len<min) {
				throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+str+"]�����ȶ�����С����["+min+"]");
			}
		}
		if(max>0) {
			if(len>max) {
				throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+str+"]�����ȴ�����󳤶�["+max+"]");
			}
		}
	}

	/**
	 * ������������Ƿ����㳤��
	 * ����ֻ������У�� ������У�� �����ֵΪ�� �����׳��쳣
	 * @param obj ��������
	 * @param fields ��ʽ��field-min-max,field-min-max
	 */
	public static void checkLengths(Object obj,String fields) {
		if(isNull(obj)) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����[obj]Ϊ��");
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
			if(isNull(v))continue;//������У�飬ֵΪ�ղ����׳��쳣
			int len = v.trim().length();
			if(min>0) {
				if(len<min) {
					sbShort.append("����["+value.getKey()+"]ֵΪ["+v+"]�����ȶ�����С����["+min+"]").append("��");
				}
			}
			if(max>0) {
				if(len>max) {
					sbLong.append("����["+value.getKey()+"]ֵΪ["+v+"]�����ȴ�����󳤶�["+max+"]").append("��");
				}
			}
		}
		if(sbShort.length()<=0&&sbLong.length()>0) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,sbLong.toString());
		}else if(sbShort.length()>0&&sbLong.length()<=0) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,sbLong.toString());
		}else if(sbShort.length()>0&&sbLong.length()>0) {//������� ����С�ڵ��������
			throw new VPhotoException(ResultCodeEnum.�����쳣,sbShort.toString()+sbLong.toString());
		}else {
			//û���쳣
		}
	}

	/**
	 * ��ȡ���󲿷�����ֵ
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
			if(!proList.contains(pd.getName()))continue;//�����������Ҫ���Ƶ����� ���ҵ�ǰ���Բ����б��� ����Ҫ����
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
	 * ��ȡfeild��Ӧ��name
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
				if(one.indexOf(FIELD_NAME_SEP)<0) { //δ��������
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
	 * �ж��Ƿ�Ϊһ��������
	 * @return
	 */
	public static boolean checkMPhone(String phoneNumber,String field){
		if(isNull(phoneNumber)){
			return false;
		}
		Pattern p = Pattern.compile(REGEX_MPHONE);
		if(!p.matcher(phoneNumber).matches()) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+phoneNumber+"],�����ֻ���");
		}
		return true;
	}

	/**
	 * �ж��Ƿ�Ϊһ��������
	 * @return
	 */
	public static boolean checkTPhone(String phoneNumber,String field){
		if(isNull(phoneNumber)){
			return false;
		}
		Pattern p = Pattern.compile(REGEX_TPHONE);
		if(!p.matcher(phoneNumber).matches()) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+phoneNumber+"],����������");
		}
		return true;
	}

	/**
	 * �ж��Ƿ�Ϊһ���ֻ���
	 * @return
	 */
	public static boolean checkEmail(String email,String field){
		if(isNull(email)){
			return false;
		}
		Pattern p = Pattern.compile(REGEX_EMAIL);
		if(!p.matcher(email).matches()) {
			throw new VPhotoException(ResultCodeEnum.�����쳣,"����["+field+"]ֵΪ["+email+"],�����ֻ���");
		}
		return true;
	}

}
