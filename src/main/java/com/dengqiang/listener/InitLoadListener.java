package com.dengqiang.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Properties;
import java.util.Set;
/**
 * 容器启动初始化配置
 * @author dengqiang 
 *
 */
public class InitLoadListener implements ServletContextListener {
	 private Log log = LogFactory.getLog(this.getClass().getName()); 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
	public String getNonSecurePort(){
	    int tomcatPort=0;
	    try {
	        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
	        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
	                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
	        String obj=objectNames.iterator().next().getKeyProperty("port");
	        tomcatPort = Integer.valueOf(obj);
	    }catch (Exception e){
	        return "";
	    }
	    return tomcatPort+"";
	}
	@Override
	public void contextInitialized(ServletContextEvent event) {
       String filePath = event.getServletContext().getRealPath("/WEB-INF/classes/log4j.properties"); 
       Properties props = new Properties(); 
       log.info(filePath);
       try { 
           FileInputStream log4jStream = new FileInputStream(filePath); 
           props.load(log4jStream); 
           log4jStream.close(); 
           String logFile = "C:/weblog/hxxq"+getNonSecurePort()+"/debug"; //设置路径 
           log.info(logFile);
           props.setProperty("log4j.appender.R.File", logFile);
           PropertyConfigurator.configure(props); //装入log4j配置信息 
       } catch (IOException e) {
           e.printStackTrace();
       } 
	}
 
}
