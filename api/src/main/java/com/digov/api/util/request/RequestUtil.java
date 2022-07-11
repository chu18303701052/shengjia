package com.digov.api.util.request;


import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * request 的工具类
 */
public class RequestUtil {
    private static final String DEFAULT_VALUE = "EMPTY";
    /**
     * 获取请求头的信息
     * @param request
     * @param key
     * @return
     */
    public static String getHeaderInfo(HttpServletRequest request, String key){
        String value = request.getHeader(key);
        if(value == null || "".equals(value)){
            value = DEFAULT_VALUE;
        }
        return value;
    }

    /**
     * 获取完整的请求地址
     * @param request
     * @return
     */
    public static String getOriginalURL(HttpServletRequest request) {
        StringBuffer originalURL = request.getRequestURL();
        Map<?, ?> parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            originalURL.append("?");
            for (Iterator<?> iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                String[] values = (String[]) parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    originalURL.append(key).append("=").append(values[i]).append("&");
                }
            }
        }
        return originalURL.toString();
    }

    /**
     * 获取请求中的地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = getHeaderInfo(request,"x-forwarded-for");
        if (DEFAULT_VALUE.equals(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeaderInfo(request,"Proxy-Client-IP");
        }
        if (DEFAULT_VALUE.equals(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeaderInfo(request,"WL-Proxy-Client-IP");
        }
        if (DEFAULT_VALUE.equals(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        try {
            ip = ip.split(",")[0];
        } catch (Exception e) {
        }
        if (ip == null) {
            ip = "";
        }
        return ip;
    }

}
