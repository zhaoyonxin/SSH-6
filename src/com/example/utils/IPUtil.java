package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * 根据ip地址获取其ip归属地
 * @author LiaoGang
 * @version 1.0
 */
public class IPUtil {
	private Logger logger = Logger.getLogger(IPUtil.class);
	//淘宝ip地址归属地查询的公共接口
	private static final String TB_INTERFACE = "http://ip.taobao.com/service/getIpInfo.php?ip=";
	private JSONObject resultJSON = null;
	private JSONObject dataJSON = null;
	private StringBuffer buf = null;
	
	public IPUtil(String ip) {
		URL url = null;
		HttpURLConnection httpConn = null;
		BufferedReader reader = null;
		try {
			url = new URL(TB_INTERFACE+ip);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.connect();
			
			reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String line;
			buf = new StringBuffer("");
			while ((line = reader.readLine()) != null) {
				buf.append(line);
			}
			resultJSON = JSONObject.fromObject(buf.toString());
			if(getCode()==0) {
				dataJSON = resultJSON.getJSONObject("data");
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		} finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.debug(e.getMessage());
					e.printStackTrace();
				}
			}
			if(httpConn!=null) {
				httpConn.disconnect();
			}
		}
	}
	
	//获取json格式的结果
	public JSONObject getReusltJson() {
		return resultJSON;
	}
	
	//获取字符串的结果
	public String getReuslt() {
		return buf.toString();
	}
	
	/**
	 * 获取状态码,0成功,1失败
	 * @return
	 */
	public int getCode() {
		if(resultJSON!=null) {
			return resultJSON.getInt("code");
		} else {
			return 1;
		}
	}
	
	//获取结果数据对象, 可能是json格式的对象也可能是字符串()
	public Object getData() {
		Object obj = null;
		if(getCode()==1 && resultJSON!=null) {
			return resultJSON.getString("data");
		} else if(resultJSON!=null) {
			obj = resultJSON.getJSONObject("data");
		}
		return obj;
	}
	
	//获取要查询的ip
	public String getIp() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("ip");
		}
		return null;
	}
	
	//获取查询ip所在的国家
	public String getCounty() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("country");
		}
		return null;
	}
	
	//获取查询ip所在的国家id号
	public String getCountId() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("country_id");
		}
		return null;
	}
	
	//获取查询ip所在的地区
	public String getArea() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("area");
		}
		return null;
	}
	
	//获取查询ip所在的地区的id
	public String getAreaId() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("area_id");
		}
		return null;
	}
	
	//获取查询ip所在的省份
	public String getRegion() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("region");
		}
		return null;
	}
	
	//获取查询ip所在的省份的Id
	public String getRegionId() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("region_id");
		}
		return null;
	}
	
	//获取查询ip所在的城市
	public String getCity() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("city");
		}
		return null;
	}
	
	//获取查询ip所在的城市的id
	public String getCityId() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("city_id");
		}
		return null;
	}
	
	//获取查询ip的运营商
	public String getIsp() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("isp");
		}
		return null;
	}
	
	//获取查询ip的运营商id
	public String getIspId() {
		if(getCode()==0 && dataJSON!=null) {
			return dataJSON.getString("isp_id");
		}
		return null;
	}
	
	//获取结果集字符串,国家 地区 省 市 运营商
	public String getContent() {
		return getCounty()+" "+getArea()+" "+getRegion()+" "+getCity()+" "+getIsp();
	}
}
