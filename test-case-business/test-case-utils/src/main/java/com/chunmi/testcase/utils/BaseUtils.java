package com.chunmi.testcase.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


/**
 * 简述：工具类的基础类<br>
 * 详细描述：<br>
 * 1.判空处理 <br>
 * 时间： 2018年08月08日 上午9:49:17 <br>
 *
 * @version V1.0
 */
public class BaseUtils {

    private static final Logger log = LoggerFactory.getLogger(BaseUtils.class);

    //=================1.字符串与空的处理=============================================

    /**
     * null2string<br>
     * 1.把指针NULL 转换成""字符串<br>
     * 2.把字符串 null 转换成""<br>
     * 校验str字符串是否是空、null(字符串)、nullnull等，<br>
     * 如果是直接返回"" <br>
     * 如果不是返回原字符串<br>
     *
     * @param str
     * @return String
     */
    public static String null2string(String str) {
        if (str == null || str.trim().length() <= 0 || str.trim().toLowerCase().equals("null")
                || str.trim().toLowerCase().equals("nullnull") || str.trim().toLowerCase().equals("nullnullnull")
                || str.trim().toLowerCase().equals("nullnullnullnull")) {
            return "";
        }
        return str;
    }

    /**
     * @param l
     * @return
     */
    public static Long null2long(Long l) {
        if (l == null) return 0L;
        return l;
    }

    /**
     * 过滤null
     *
     * @param obj
     * @return
     */
    public static String null2string(Object obj) {
        if (obj == null) return "";
        return obj.toString();
    }

    /**
     * 判断字符串是否为空<br>
     *
     * @param str
     * @return 如果为null或者为"" 则返回true
     */
    public static boolean isNull(String str) {
        return null2string(str).length() <= 0;
    }


    /**
     * 判断对象是否为空
     *
     * @param o
     * @return boolean
     */
    public static boolean isNull(Object o) {
        if (o == null)
            return true;
        if (o instanceof String) {
            return null2string(String.valueOf(o)).length() <= 0;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return 如果字符串 不为null 并且不为 "" 返回true 否则返回false
     */
    public static boolean isNotNull(String str) {
        return null2string(str).length() > 0;
    }

    /**
     * 判断对象是否不为空
     *
     * @param o
     * @return 如果为不为null 返回true 否则返回false
     */
    public static boolean isNotNull(Object o) {
        if (o == null)
            return false;
        if (o instanceof String) {
            return null2string(String.valueOf(o)).length() > 0;
        }
        return true;
    }

    //==================2.字符串转换成数字或============================================

    /**
     * 判断是否是数字
     *
     * @param num
     * @return
     */
    public static boolean isNumber(String num) {
        if (isNull(num)) return false;
        final String number = "0123456789";
        for (int i = 0; i < number.length(); i++) {
            if (number.indexOf(num.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是小数
     *
     * @param num
     * @return
     */
    public static boolean isDecimal(String num) {
        if (isNull(num)) return false;
        final String number = "0123456789.";
        for (int i = 0; i < number.length(); i++) {
            if (number.indexOf(num.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串转换成int <br>
     * 如果str为空、不是字符串 则返回0<br>
     *
     * @param str
     * @return
     */
    public static int string2int(String str) {
        return string2int(str, 0);
    }

    /**
     * 字符串转换成int <br>
     *
     * @param str          对应字符串
     * @param defaultValue 如果字符串为空或者出错 默认返回的值
     * @return str=null 则返回 defaultValue；  str不是整数，则返回  defaultValue
     */
    public static int string2int(String str, int defaultValue) {
        if (isNotNull(str)) {
            try {
                return Integer.valueOf(str).intValue();
            } catch (Exception ex) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 字符串转换成long <br>
     * 如果str为空、不是字符串 则返回0<br>
     *
     * @param str
     * @return
     */
    public static long string2long(String str) {
        return string2long(str, 0);
    }

    /**
     * 字符串转换成long <br>
     *
     * @param str          对应字符串
     * @param defaultValue 如果字符串为空或者出错 默认返回的值
     * @return str=null 则返回 defaultValue；  str不是整数，则返回  defaultValue
     */
    public static long string2long(String str, long defaultValue) {
        if (isNotNull(str)) {
            try {
                return Long.valueOf(str).longValue();
            } catch (Exception ex) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 字符串转换成double <br>
     * 如果str为空、不是字符串 则返回0.0<br>
     *
     * @param str
     * @return
     */
    public static double string2double(String str) {
        return string2double(str, 0.0);
    }

    /**
     * 字符串转换成double <br>
     * 这里没有对double精度进行处理，要想保留精度，必须再调用另外一个方法： doubleFormat<br>
     *
     * @param str          对应字符串
     * @param defaultValue 如果字符串为空或者出错 默认返回的值
     * @return str=null 则返回 defaultValue；  str不是整数，则返回  defaultValue
     */
    public static double string2double(String str, double defaultValue) {
        if (isNotNull(str)) {
            try {
                return Double.valueOf(str).doubleValue();
            } catch (Exception ex) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 格式化double 保留对应的位数<br>
     * 是按照四舍五入的方法保留小数位数的<br>
     *
     * @param d       待处理double
     * @param decimal 保留的小数位数
     * @return
     */
    public static double doubleFormat(double d, int decimal) {
        BigDecimal b = new BigDecimal(d);
        //==1.ROUND_DOWN  直接把多余的位数删除
        //==2.ROUND_UP 进位处理
        //==3.ROUND_HALF_DOWN 四舍五入 1.5 会转换为1.0
        //==4.ROUND_HALF_UP 四舍五入 1.5会转换为2.0
        return b.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue(); //四舍五入
    }

    /**
     * 字符串转换成BigDecimal <br>
     * 如果str为空、不是字符串 则返回0.0<br>
     *
     * @param str
     * @return
     */
    public static BigDecimal string2bigDecimal(String str) {
        return string2bigDecimal(str, 0.0);
    }

    /**
     * 字符串转换成BigDecimal <br>
     *
     * @param str          对应字符串
     * @param defaultValue 如果字符串为空或者出错 默认返回的值
     * @return str=null 则返回 defaultValue；  str不是整数，则返回  defaultValue
     */
    public static BigDecimal string2bigDecimal(String str, double defaultValue) {
        if (isNotNull(str)) {
            try {
                return new BigDecimal(str);
            } catch (Exception ex) {
                return new BigDecimal(defaultValue);
            }
        }
        return new BigDecimal(defaultValue);
    }

    /**
     * 对字符串进行trim
     *
     * @param str
     * @return String
     */
    public static String stringTrim(String str) {
        return null2string(str).trim();
    }

    /**
     * 比较两个字符串是否相同
     * 主要是防止为空
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean compare(String s1, String s2) {
        s1 = null2string(s1);
        s2 = null2string(s2);
        return s1.equals(s2);
    }

    //================================map转换成字符串=======================================

    /**
     * map转换成字符串 使用 gap隔开<br>
     * 假设gap为分号 则 转换后的格式：k1=v1;k2=v2;
     *
     * @param map
     * @param gap
     * @return String
     */
    public static String map2string(Map<String, String> map, String gap) {
        if (isNull(map))
            return "";
        gap = null2string(gap);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> m : map.entrySet()) {
            try {
                sb.append(m.getKey()).append("=").append(null2string(m.getValue())).append(gap);
            } catch (Exception ex) {
                //
            }
        }
        return sb.toString();
    }


    /**
     * 把对象转换成字符串
     *
     * @param <T>
     * @param t
     * @return String
     */
    public static <T> String obj2string(T t) {
        StringBuffer sb = new StringBuffer();
        try {
            if (t == null)
                return sb.toString();
            Method[] ms = t.getClass().getMethods();
            Object value;
            String fieldName;
            for (Method m : ms) {
                fieldName = m.getName();
                if (fieldName.indexOf("get") >= 0) {
                    fieldName = fieldName.substring(fieldName.indexOf("get") + 3, fieldName.length());
                    value = m.invoke(t);
                    if (value instanceof Date) {
                        value = DateUtils.toString(((Date) value), "yyyy-MM-dd HH:mm:ss");// 转换成date
                    } else if (value instanceof Timestamp) {
                        value = DateUtils.toString(new Date(((Timestamp) value).getTime()), "yyyy-MM-dd HH:mm:ss");// 转换成date
                    }
                    sb.append(fieldName).append("=").append(value).append(";");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * bean拷贝
     * null有效，即null值也会覆盖
     * 以t为标准，解析t的所有属性，从s中取对应的属性值进行拷贝
     *
     * @param s    原bean
     * @param t    目标bean
     * @param pros 需要拷贝的属性  如果不输入  则会检索t的所有属性，从s拷贝对应属性
     */
    public static void copyBeanNullValid(Object s, Object t, String... pros) {
        copyBean(s, t, true, pros);
    }

    /**
     * bean拷贝
     * null无效  即如果s的属性值为null 则不会覆盖t的属性值
     * 以t为标准，解析t的所有属性，从s中取对应的属性值进行拷贝
     *
     * @param s    原bean
     * @param t    目标bean
     * @param pros 需要拷贝的属性  如果不输入  则会检索t的所有属性，从s拷贝对应属性
     */
    public static void copyBeanNullInvalid(Object s, Object t, String... pros) {
        copyBean(s, t, false, pros);
    }

    /**
     * bean拷贝
     * 如果s中的属性为空 则不进行覆盖
     * 以t为标准，解析t的所有属性，从s中取对应的属性值进行拷贝
     *
     * @param s         原bean
     * @param t         目标bean
     * @param nullValid null有效 传入 true  null无效传入 false
     * @param pros      需要拷贝的属性  如果不输入  则会检索t的所有属性，从s拷贝对应属性
     */
    private static void copyBean(Object s, Object t, boolean nullValid, String... pros) {
        if (s == null) return;
        if (t == null) return;
        Class<?> actualEditable = t.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> proList = (pros != null ? Arrays.asList(pros) : null);
        Method writeMethod = null, readMethod = null;
        PropertyDescriptor sourcePd = null;
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd == null) continue;
            if (proList != null && proList.size() > 0 && !proList.contains(targetPd.getName()))
                continue;//如果传入了需要复制的属性 并且当前属性不在列表内 则不需要复制
            sourcePd = BeanUtils.getPropertyDescriptor(s.getClass(), targetPd.getName());
            if (sourcePd == null) continue;
            readMethod = sourcePd.getReadMethod();
            if (readMethod == null) continue;
            writeMethod = targetPd.getWriteMethod();
            if (writeMethod == null) continue;
            if (!ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) continue;
            try {
                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                    readMethod.setAccessible(true);
                }
                Object value = readMethod.invoke(s);
                if (!nullValid) { //null 无效 则不覆盖
                    if (value == null || value.toString().length() <= 0) continue;//这里防止把空覆盖
                }
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(t, value);
            } catch (Throwable ex) {
                log.error("==copybean==s[{}]==t{}==p[{}]==e[{}]==", s.getClass().getName(), t.getClass().getName(), targetPd.getName(), ex.getMessage());
            }
        }
    }

    /**
     * 类对拷
     *
     * @param s   源对象
     * @param sps 源对象的属性逗号分隔 eg:a,b,c
     * @param t   目标对象
     * @param tps 目标对象的属性 逗号分隔 eg:a,b,c
     */
    @SuppressWarnings("unchecked")
    public static void copyBean(Object s, String sps, Object t, String tps) {
        if (s == null || BaseUtils.isNull(sps) || t == null || BaseUtils.isNull(tps)) return;
        String spsb[] = sps.split(","), tpsb[] = tps.split(",");
        if (spsb.length != tpsb.length) return;
        if (s instanceof Map && t instanceof Map) {
            copyMap2Map((Map<String, Object>) s, sps, (Map<String, Object>) t, tps);
        } else if (s instanceof Map) {
            copyMap2Bean((Map<String, Object>) s, sps, t, tps);
        } else if (t instanceof Map) {
            copyBean2Map(s, sps, (Map<String, Object>) t, tps);
        } else {
            copyBean2Bean(s, sps, t, tps);
        }
    }

    /**
     * 类对拷
     *
     * @param s   源对象
     * @param sps 源对象的属性逗号分隔 eg:a,b,c
     * @param t   目标对象
     * @param tps 目标对象的属性 逗号分隔 eg:a,b,c
     */
    private static void copyBean2Map(Object s, String sps, Map<String, Object> t, String tps) {
        if (s == null || BaseUtils.isNull(sps) || t == null || BaseUtils.isNull(tps)) return;
        String spsb[] = sps.split(","), tpsb[] = tps.split(",");
        if (spsb.length != tpsb.length) return;
        Method readMethod = null;
        PropertyDescriptor spd = null;
        Class<?> sClass = s.getClass();
        Object value = null;
        int len = spsb.length;
        for (int i = 0; i < len; i++) {
            try {
                spd = BeanUtils.getPropertyDescriptor(sClass, spsb[i]);
                if (spd == null) continue;
                readMethod = spd.getReadMethod();
                if (readMethod == null) continue;
                value = readMethod.invoke(s);
                if (value == null) continue;
                t.put(tpsb[i], value);
            } catch (Throwable ex) {
                log.error("==copybean==s[{}]==t{}==sp[{}]==tp[{}]==e[{}]==", s.getClass().getName(), t.getClass().getName(), spsb[i], tpsb[i], ex.getMessage());
            }
        }
    }

    /**
     * 类对拷
     *
     * @param s   源对象
     * @param sps 源对象的属性逗号分隔 eg:a,b,c
     * @param t   目标对象
     * @param tps 目标对象的属性 逗号分隔 eg:a,b,c
     */
    private static void copyMap2Bean(Map<String, Object> s, String sps, Object t, String tps) {
        if (s == null || BaseUtils.isNull(sps) || t == null || BaseUtils.isNull(tps)) return;
        String spsb[] = sps.split(","), tpsb[] = tps.split(",");
        if (spsb.length != tpsb.length) return;
        Method writeMethod = null;
        PropertyDescriptor tpd = null;
        Class<?> tClass = t.getClass();
        Object value = null;
        int len = spsb.length;
        for (int i = 0; i < len; i++) {
            try {
                value = s.get(spsb[i]);
                if (value == null) continue;
                tpd = BeanUtils.getPropertyDescriptor(tClass, tpsb[i]);
                if (tpd == null) continue;
                writeMethod = tpd.getWriteMethod();
                if (writeMethod == null) continue;
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(t, value);
            } catch (Throwable ex) {
                log.error("==copybean==s[{}]==t{}==sp[{}]==tp[{}]==e[{}]==", s.getClass().getName(), t.getClass().getName(), spsb[i], tpsb[i], ex.getMessage());
            }
        }
    }

    /**
     * 类对拷
     *
     * @param s   源对象
     * @param sps 源对象的属性逗号分隔 eg:a,b,c
     * @param t   目标对象
     * @param tps 目标对象的属性 逗号分隔 eg:a,b,c
     */
    private static void copyBean2Bean(Object s, String sps, Object t, String tps) {
        if (s == null || BaseUtils.isNull(sps) || t == null || BaseUtils.isNull(tps)) return;
        String spsb[] = sps.split(","), tpsb[] = tps.split(",");
        if (spsb.length != tpsb.length) return;
        Method writeMethod = null, readMethod = null;
        PropertyDescriptor spd = null, tpd = null;
        Class<?> sClass = s.getClass(), tClass = t.getClass();
        Object value = null;
        int len = spsb.length;
        for (int i = 0; i < len; i++) {
            try {
                spd = BeanUtils.getPropertyDescriptor(sClass, spsb[i]);
                if (spd == null) continue;
                readMethod = spd.getReadMethod();
                if (readMethod == null) continue;
                tpd = BeanUtils.getPropertyDescriptor(tClass, tpsb[i]);
                if (tpd == null) continue;
                writeMethod = tpd.getWriteMethod();
                if (writeMethod == null) continue;
                value = readMethod.invoke(s);
                if (value == null) continue;
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(t, value);
            } catch (Throwable ex) {
                log.error("==copybean==s[{}]==t{}==sp[{}]==tp[{}]==e[{}]==", s.getClass().getName(), t.getClass().getName(), spsb[i], tpsb[i], ex.getMessage());
            }
        }
    }

    /**
     * 类对拷
     *
     * @param s   源对象
     * @param sps 源对象的属性逗号分隔 eg:a,b,c
     * @param t   目标对象
     * @param tps 目标对象的属性 逗号分隔 eg:a,b,c
     */
    private static void copyMap2Map(Map<String, Object> s, String sps, Map<String, Object> t, String tps) {
        if (s == null || BaseUtils.isNull(sps) || t == null || BaseUtils.isNull(tps)) return;
        String spsb[] = sps.split(","), tpsb[] = tps.split(",");
        if (spsb.length != tpsb.length) return;
        Object value = null;
        int len = spsb.length;
        for (int i = 0; i < len; i++) {
            try {
                value = s.get(spsb[i]);
                if (value == null) continue;
                t.put(tpsb[i], value);
            } catch (Throwable ex) {
                log.error("==copybean==s[{}]==t{}==sp[{}]==tp[{}]==e[{}]==", s.getClass().getName(), t.getClass().getName(), spsb[i], tpsb[i], ex.getMessage());
            }
        }
    }

    /**
     * 对象进行序列化
     *
     * @param value
     * @return
     */
    public static byte[] serialize(Object value) {
        byte[] rv = null;
        if (value == null) return rv;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            rv = bos.toByteArray();
            os.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) os.close();
                if (bos != null) bos.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rv;
    }

    /**
     * 反序列化
     *
     * @param in
     * @return
     */
    public static Object deserialize(byte[] in) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in == null) return rv;
            bis = new ByteArrayInputStream(in);
            is = new ObjectInputStream(bis);
            rv = is.readObject();
            is.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (bis != null) bis.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rv;
    }

    /**
     * 获取id
     *
     * @return String
     * @throws Exception
     */
    public static String getUUID() {
        String id = UUID.randomUUID().toString().replace("-", "");
//				+System.currentTimeMillis();

        return id;
    }

}
