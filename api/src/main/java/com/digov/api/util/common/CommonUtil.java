package com.digov.api.util.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 公共 有关的
 *
 * @author yyx
 * @date 2016年11月7日 14:55:27
 * @version 2.0
 */

public class CommonUtil {
	private static final Logger LOGGER = LogManager.getLogger(CommonUtil.class);


	public static List<Map<String, Object>> listMapToLowerCase(
			List<Map<String, Object>> list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				list.set(i, mapToLowerCase(map));
			}
		}
		return list;
	}
	/**
	 * 将map中的key都转换为小写
	 * @param inMap
	 * @return
	 */
	public static Map<String, Object> mapToLowerCase(Map<String, Object> inMap) {
		Map<String, Object> resultMap = null;
		if (inMap != null) {
			if (LinkedHashMap.class.isInstance(inMap)) {
				resultMap = new LinkedHashMap<>();
			} else if (HashMap.class.isInstance(inMap)) {
				resultMap = new HashMap<>();
			} else if(TreeMap.class.isInstance(inMap)){
				resultMap = new HashMap<>();
			} else {
				return inMap;
			}
			for (Iterator<Entry<String, Object>> it = inMap.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, Object> e = it.next();
				String new_key = e.getKey().toLowerCase();
				resultMap.put(new_key, e.getValue());
			}
		}
		return resultMap;
	}


	public static String changeEncode(String str, String fromEncode,
			String toEncode) throws UnsupportedEncodingException {
		String returnStr;
		returnStr = new String(str.getBytes(fromEncode), toEncode);
		return returnStr;
	}


	public static List<String> changeToList(String str) {
		if (isEmpty(str)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		int fromIndex = 0;
		int toIndex = 0;
		while ((toIndex = str.indexOf("},{", fromIndex)) > -1) {
			String s = str.substring(fromIndex, toIndex + 1);
			fromIndex = toIndex + 2;
			list.add(s);
		}
		String s = str.substring(fromIndex);
		if (!isEmpty(s)) {
			list.add(s);
		}
		return list;
	}

	/**
	 * 过滤空字符串
	 * @param obj
	 * @return
	 */
	public static String trimNull(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Object str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Long str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}


	public static boolean isEmpty(List<?> list) {
		if (list == null || list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isEmpty(Set<?> set) {
		if (set == null || set.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isEmpty(String[] arr) {
		if (arr == null || arr.length == 0) {
			return true;
		} else {
			return false;
		}
	}



	/**
	 * 从map中获取String
	 *
	 * @date 2011-7-19
	 * @time 下午05:18:31
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getMapString(Map<String, Object> map, String key) {
		if (map == null) {
			return null;
		}
		Object value_obj = map.get(key);
		if (value_obj == null) {
			return null;
		} else {
			return value_obj.toString().trim();
		}
	}

	/**
	 * 从map中获取long
	 *
	 * @date 2011-7-19
	 * @time 下午05:18:20
	 * @param map
	 * @param key
	 * @return
	 */
	public static Long getMapLong(HashMap<String, Object> map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Long.parseLong(value);
		}
	}

	public static Double getMapDouble(HashMap<String, Object> map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * 从map中获取Int
	 *
	 * @date 2011-7-19
	 * @time 下午05:18:20
	 * @param map
	 * @param key
	 * @return
	 */
	public static Integer getMapInt(HashMap<String, Object> map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}
	/**
	 * 将字符串转换为Integer
	 *
	 * @param value
	 * @return
	 */
	public static Integer parseInt(String value){
	    if(CommonUtil.isEmpty(value)){
	        return null;
	    }
	    try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
	}
	/**
	 * 在位数不到total的情况下前面补0,默认长度过长则裁剪
	 *
	 * @date 2011-7-21
	 * @time 上午11:36:16
	 * @param number
	 * @param total
	 * @return
	 */
	public static String addZeroToNumber(Long number, int total) {
		if (number == null) {
			return null;
		}
		String number_string = number.toString();
		return addZeroToNumber(number_string, total, true);
	}

	/**
	 * 在位数不到total的情况下前面补0
	 *
	 * @date 2011-7-21
	 * @time 上午11:36:16
	 * @param number
	 * @param total
	 * @param cut_if_long
	 * @return
	 */
	public static String addZeroToNumber(String number, int total,
			boolean cut_if_long) {
		if (number == null) {
			return null;
		}
		int length = number.length();
		if (length < total) {
			for (int i = length; i < total; i++) {
				number = "0" + number;
			}
		} else if (cut_if_long && length > total) {
			number = number.substring(length - total, length);
		}
		return number;
	}

	public static String format(String value, String type) {
		if ("".equals(value)) {
			value = "''";
		} else {
			if ("date".equals(type)) {
				value = "to_date('" + value + "','YYYY/MM/DD HH24:MI:SS')";
			} else if ("int".equals(type) || "string".equals(type)) {
				value = "'" + value + "'";
			}
		}

		return value;
	}


	public static String arrayToString(String[] arr) {
		if (arr == null) {
			return null;
		} else {
			String re = "";
			for (int i = 0; i < arr.length; i++) {
				re += arr[i] + ",";
			}
			if (!"".equals(re)) {
				re = re.substring(0, re.length() - 1);
			}
			return re;
		}
	}

	/**
	 *
	 * @param list
	 * @param regex 字符串连接符
	 * @return
	 */
	public static String listToString(List<String> list, String regex) {
		if (list == null) {
			return null;
		} else {
			StringBuffer stringBuffer = new StringBuffer();
			for (String s : list) {
				stringBuffer.append(s);
				stringBuffer.append(regex);
			}
			if (stringBuffer.length() > 0) {
				stringBuffer = stringBuffer.delete(stringBuffer.length() - 1,stringBuffer.length());

			}
			return stringBuffer.toString();
		}
	}
	public static List<String> stringToList(String str, String split) {
		if (CommonUtil.isEmpty(str)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] strArray = str.split(split);
		for(String entity : strArray){
			list.add(entity);
		}
		return list;
	}
	/**
	 * 取得两个数组中的公共部分
	 *
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:06
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List<String> getExistNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList<String>(
				Arrays.asList(new String[old_phone_numbers.size()]));
		Collections.copy(result_list, old_phone_numbers);
		result_list.retainAll(new_phone_numbers);
		return result_list;
	}


	/**
	 * 获取具体的错误信息
	 * @param e
	 * @return
	 */
	public static String getExceptionInfo(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String msg=sw.toString();
		return msg;
	}
	/**
	 * 获取编译文件的根目录
	 * @return
	 */
	public static String getClassPath(){
		return EnumSingletonClassPath.INSTANCE.getClassPath();
	}
	private enum EnumSingletonClassPath{
		INSTANCE;
		private String classPath;
		EnumSingletonClassPath(){
			classPath = CommonUtil.class.getClassLoader().getResource("").getPath();
		}
		private String getClassPath(){
			return classPath;
		}
	}

}
