package com.dengqiang.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.ResultInfo;
import com.dengqiang.bean.UserInfoBean;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 公共工具类
 * @author dengqiang 2017-12-18 14:00:00
 */
public abstract class BaseController {
	public static final String SESSION_USER_INFO="SESSION_USER_INFO";//用户信息
	public static final String OPERATORS_NAME = "OPERATORS_NAME";//运营商信息
	public static final String HOUSING_ESTATE_INFO = "HOUSING_ESTATE_INFO";//小区信息
	/**
	 * 从请求头中获取参数的key和value
	 * @param request
	 * @param idname 需要忽略的id名称
	 * @return
	 */
	public Map<String,Object> getKeyAndValue(HttpServletRequest request) {
		Map<String,Object> param=new HashMap<String, Object>();
		Enumeration<String> ens=request.getParameterNames();
		while (ens.hasMoreElements()) {
			String key = ens.nextElement();
			if (StringUtils.isNotBlank(request.getParameter(key))) {
				param.put(key, request.getParameter(key).trim());
			}else{
				param.put(key, request.getParameter(key));
			}
		}
		return param;
	}
	/** 
	 * 获取绝对路径  已经在返回时添加 '/'
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request, String url) {
		return request.getSession().getServletContext().getRealPath(url)+"/";
	}
	/** 
	 * 获取绝对路径  已经在返回时添加 '/'
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/")+"/";
	}

	/**
	 * 获取服务器域名
	 * @param request
	 * @return 服务器域名
	 */
	public String getServerName(HttpServletRequest request) {
		return "http://" + request.getServerName() +"/";
	} 
	/**
	 * 获取HttpServletRequest请求中的
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * 保存数据到文件中
	 * @param file 文件存储路径
	 * @param str 存储数据
	 */
	public synchronized void saveFile(File file,String str){
		saveFile(file, str, true);
	}
	/**
	 * 保存数据到文件中
	 * @param file 文件存储路径
	 * @param str 存储数据
	 */
	public synchronized void saveFile(File file,String str,boolean append){
			try {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				OutputStreamWriter outputStream = new OutputStreamWriter(
						new FileOutputStream(file,append),
						"UTF-8");
				outputStream.write(str);
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * 获取登录用户id
	 * @param request
	 * @return 用户id
	 */
	public Long getUserInfoId(HttpServletRequest request) {
		UserInfoBean bean = (UserInfoBean) request.getSession().getAttribute(
				SESSION_USER_INFO);
		if (bean != null) {
			return bean.getId();
		}
		return 0l;
	}
	/**
	 * 获取小区信息
	 * @param request
	 * @return
	 */
	public HousingEstateBean getHousingEstate(HttpServletRequest request) {
		HousingEstateBean bean = (HousingEstateBean) request.getSession().getAttribute(
				HOUSING_ESTATE_INFO);
		return bean;
	}
	
	/**
	 * 获取登录用户信息
	 * @param request
	 * @return 用户信息
	 */
	public UserInfoBean getUserInfo(HttpServletRequest request) {
		UserInfoBean bean = (UserInfoBean) request.getSession().getAttribute(SESSION_USER_INFO);
		return bean;
	}
	/**
	 * 创建指定文件的父级目录
	 * @param file 文件路径
	 */
	public static synchronized void mkdirsDirectory(File file) {
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	}
	/**
	 * 获取带有comId的真实路径 已经在后面加 "/"
	 * @param request
	 * @return
	 */
	public String getComIdPath(HttpServletRequest request) {
		StringBuffer buffer=new StringBuffer(getRealPath(request));
		buffer.append(getComId(request)).append("/");
		return buffer.toString();
	}
	/**
	 * 获取运营商编码
	 * @param request
	 * @return 运营商编码
	 */
	public String getComId(HttpServletRequest request) {
		try {
			String com_id= request.getSession().getAttribute(OPERATORS_NAME).toString().trim();
			if(StringUtils.isBlank(com_id)){
				com_id="001";
			}
			return com_id;
		} catch (Exception e) {
			return "001";
		}
	}
	/**
	 * 获取运营商编码
	 * @param request
	 * @return 运营商编码
	 */
	public String getComId() {
		try {
			String com_id= getRequest().getSession().getAttribute(OPERATORS_NAME).toString();
			if(StringUtils.isBlank(com_id)){
				com_id="001";
			}
			return com_id.trim();
		} catch (Exception e) {
			return "001";
		}
	}
	
	/**
	 * 获取资源版本号,用于页面js+css加版本号
	 * @return
	 */
	public static void getVer(HttpServletRequest request) {
		String ajax=request.getParameter("_");
		String url=request.getRequestURI();
		if(StringUtils.isBlank(ajax)||url.contains(".do")){
			request.setAttribute("ver", "?ver="+getVer());
		}
	}
	/**
	 * 获取项目根路径
	 * @return 项目根路径
	 */
	public static String getProjectPath() {
		String path = BaseController.class.getClassLoader()
				.getResource("jdbc.properties").getPath();
		return path.replaceFirst("/", "").replace("WEB-INF/classes/jdbc.properties", "");
	}
	/**
	 * 获取资源版本号,用于页面js+css加版本号
	 * @return
	 */
	public static String getVer() {
		File verfile=new File(getProjectPath()+"ver.txt");
		String ver=null;
		if (verfile.exists()) {
			ver=getFileTextContent(verfile);
		}
		if (StringUtils.isBlank(ver)) {
			ver=(Math.random()*1000)+"";
			ver=ver.substring(0, 6);
		}
		return ver;
	}
	/**
	 * 获取txt文件内容
	 * @param path 文件路径
	 * @return 文件内容,文件不存在返回null,
	 */
	public synchronized String getFileTextContent(String path) {
		File file =new File(path);
		return getFileTextContent(file);
	}
	/**
	 * 获取txt文件内容
	 * @param path 文件路径
	 * @return 文件内容,文件不存在返回null,
	 */
	public static synchronized String getFileTextContent(File path) {
		try {
			if (!path.exists()) {
				return null;
			}
			Scanner scanner=new Scanner(path,"UTF-8");
			String line="";
			while (scanner.hasNext()) {
				String li=scanner.nextLine();
				 if (StringUtils.isNotBlank(li)) {
					 line+=li;
				}
			}
			if (line!=null&&line.length()>1) {
				String st=line.substring(0, 1);
				if(StringUtils.isBlank(st)){
					line=line.substring(1,line.length());
				}
			}
			scanner.close();
			return line.trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public String getNow() {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		return format.format(new Date());
	}
	/**
	 * 将数据组合成json写入客户端中
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public synchronized static void writeJson(HttpServletResponse response,String msg) throws IOException {
		ResultInfo info=new ResultInfo(true,msg);
		JSONObject json=JSONObject.fromObject(info);
		response.setContentType("text/html;charset=utf-8");
		response.getOutputStream().write(json.toString().getBytes("utf-8"));
	}
	
	/**
	 * 图片缩小
	 * @param originalFile
	 * @param resizedFile
	 * @param newWidth
	 * @param quality
	 * @throws IOException
	 */
	public synchronized void imgResize(File originalFile, File resizedFile,  
            int newWidth, float quality) throws IOException { 
  
        if (quality > 1) {  
            throw new IllegalArgumentException(  
                    "Quality has to be between 0 and 1");  
        }  
  
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());  
        Image i = ii.getImage();  
        Image resizedImage = null;  
  
        int iWidth = i.getWidth(null);  
        int iHeight = i.getHeight(null);  
  
        if (iWidth > iHeight) {  
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)  
                    / iWidth, Image.SCALE_SMOOTH);  
        } else {  
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,  
                    newWidth, Image.SCALE_SMOOTH);  
        }  
  
        // This code ensures that all the pixels in the image are loaded.  
        Image temp = new ImageIcon(resizedImage).getImage();  
  
        // Create the buffered image.  
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
  
        // Copy image to buffered image.  
        Graphics g = bufferedImage.createGraphics();  
  
        // Clear background and paint the image.  
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();  
  
        // Soften.  
        float softenFactor = 0.05f;  
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        bufferedImage = cOp.filter(bufferedImage, null);  
  
        // Write the jpeg to a file.  
        FileOutputStream out = new FileOutputStream(resizedFile);  
        // Encodes image as a JPEG data stream  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
  
        JPEGEncodeParam param = encoder  
                .getDefaultJPEGEncodeParam(bufferedImage);  
  
        param.setQuality(quality, true);  
  
        encoder.setJPEGEncodeParam(param);  
        encoder.encode(bufferedImage);
    }
	/**
	 * 判断map中key的值是否为空
	 * @param map
	 * @param key
	 * @return 为空就为true,
	 */
	public synchronized boolean isMapKeyNull(Map<String,Object> map,String key) {
		if(map==null||map.get(key)==null){
			return true;
		}else if("".equals(map.get(key).toString().trim())){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断map中key的值是否为空
	 * @param map
	 * @param key
	 * @return 为空就为false,
	 */
	public synchronized boolean isNotMapKeyNull(Map<String,Object> map,String key) {
		return !isMapKeyNull(map, key);
	}
	/**
	 * 从json对象中获取数据
	 * @param json 数据源
	 * @param jsonName json中获取数据的名称key
	 * @return 返回key对应的值,不存在时返回空字符串
	 */
	public synchronized String getJsonVal(JSONObject json,String jsonName) {
		try {
			if (json.has(jsonName)) {
				String val=json.getString(jsonName).trim();
				if (StringUtils.isNotBlank(val)) {
					return val;
				}
			}
		} catch (Exception e) {
		}
		return "";
	}
}
