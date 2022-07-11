package com.digov.api.util.request;

import com.digov.api.util.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class IpUtil {
	/******ip文件的路径*****/
	private static final String IP_FILE_PATH = "ip/ipdata.txt";
	private static List<String[]> ipListArray;
	private IpUtil(){

	}
	public static IpUtil getInstance(){
		return EnumSingle.INSTANCE.getIpUtil();
	}
	private  enum EnumSingle{
		INSTANCE;
		private IpUtil ipUtil;
		EnumSingle(){
			ipUtil = new IpUtil();
			ipUtil.loadFile();
		}
		private IpUtil getIpUtil(){
			return ipUtil;
		}
	}
	/**
	 * 加载ip文件
	 */
	private void loadFile(){
		log.info("加载IP库！！！！");
		ipListArray  = getIpListFromFile(IP_FILE_PATH);
		log.info("加载IP完成！！！！");
	}

	/**
	 * 根据IP库文件路径生成数组
	 * @param filePath
	 * @return
	 */
	private List<String[]> getIpListFromFile(String filePath){
		List<String[]> list= new ArrayList<>();
		BufferedReader in = null;
		if(filePath != null){
			try {
				ClassPathResource classPathResource = new ClassPathResource(filePath);
				InputStream inputStream = classPathResource.getInputStream();
				in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
				  String line;
				  while ((line = in.readLine()) !=  null) {
					  String[] ipinfo=line.split(",",9);
					  if(ipinfo!=null && ipinfo.length==9){
						  if(ipinfo[4].equals("中国") || ipinfo[4].equals("香港") || ipinfo[4].equals("台湾")){
							  list.add(ipinfo);
						  }
					  }
              }

			} catch (Exception e) {
				e.printStackTrace();
				log.info("ip文件加载失败");
				log.info(CommonUtil.getExceptionInfo(e));
			} finally {
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
						log.info("ip文件 流关闭失败");
						log.info(CommonUtil.getExceptionInfo(e));
					}
				}
			}
		}else{
			log.info("file : "+ filePath + " is not exist!");
		}

		return list;
	}

	/**
	 * 根据IP获取IP详细信息
	 * @param ip
	 * @return
	 */
	private String[] search(String ip){
		long fromIpNum=ipStrToIpNum(ip);
		int index=-1;
		if(fromIpNum!=-1  && ipListArray !=null){
			//使用折半查找法优先查找
			index=BinarySearch(ipListArray,fromIpNum,0,ipListArray.size());
		}
		if(index>-1){
			return ipListArray.get(index);
		}else{
			String[] a=new String[]{"","","","","国外","","","",""};
			return a;
		}
	}

	/**
	 * 根据IP获取IP详细信息
	 * @param fromIpNum
	 * @return
	 */
	public String[] search(long fromIpNum){
		int index=-1;
		if(fromIpNum!=-1  && ipListArray !=null){
			//使用折半查找法优先查找
			index=BinarySearch(ipListArray,fromIpNum,0,ipListArray.size());
		}
		if(index>-1){
			return ipListArray.get(index);
		}else{
			String[] a=new String[]{"","","","","国外","","","",""};
			return a;
		}
	}

	/**
     * @param data 从小到大排好序的数组
     * @param goal 要查找的数
     * @param left
     * @param right
     * @return 目标数的数组下标，没有找到为-1 ;
     */
	private int BinarySearch(List<String[]> data,long goal,int left,int right){
        int mid = (left+right)/2 ;
        if(left>right){
            return -1 ;
        }
        try {
        	//范围查找
            if(goal>=Long.parseLong(data.get(mid)[0]) && goal<=Long.parseLong(data.get(mid)[1])){
                return mid ;
            }else if(goal<Long.parseLong(data.get(mid)[0])){
                //注意right = mid -1 ;
                return BinarySearch(data,goal,left,mid-1);
            }else if(goal>Long.parseLong(data.get(mid)[1])){
                return BinarySearch(data,goal,mid+1,right);
            }
		} catch (Exception e) {
			return -1 ;
		}
		return -1 ;
    }
    /**
     * 把IP转换成数字
     * @param ipstr
     * @return
     */
	public long ipStrToIpNum(String ipstr){
		String[] fileds=ipstr.split("[.]");
		long ipnum=-1;
		try {
			if(fileds.length==4){
				ipnum=Long.parseLong(fileds[0])*256*256*256+Long.parseLong(fileds[1])*256*256
					+Long.parseLong(fileds[2])*256+Long.parseLong(fileds[3]);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return ipnum;
	}


	/**
	 * @param ip
	 * @return
	 * 根据ip获取省份
	 */
	public String getProvinceFromIp(String ip){
		String[] list = search(ip);
		if(list!=null && list.length==9){
			return list[5];
		}
		return null;
	}

	/**
	 * @param ip
	 * @return
	 * 根据ip获取城市
	 */
	public String getCityFromIp(String ip){
		String[] list = search(ip);
		if(list!=null && list.length==9){
			return list[6];
		}
		return null;
	}

	/**
	 * @param ip
	 * @return
	 * 根据ip获取地区
	 */
	public String getDiquFromIp(String ip){
		String[] list = search(ip);
		if(list!=null && list.length==9){
			return list[7];
		}
		return null;
	}


	/**
	 * 从request中获取对应的ip信息
	 * @param request
	 * @param regex
	 * @return
	 */
	public String getIpInfo(HttpServletRequest request, String regex){
		StringBuffer info = new StringBuffer();
		String fromIp = RequestUtil.getIp(request);
		info.append(fromIp);
		info.append(regex);
		long formIpNum =  ipStrToIpNum(fromIp);
		String[] icing = search(formIpNum);
		for(int i = 4; i < 9; i ++){
			String value = icing[i];
			if(value == null || "".equals(value)){
				value = "empty";
			}
			info.append(value);
			info.append(regex);
		}
		return info.toString();
	}

}
