package com.dengqiang.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

import com.dengqiang.service.IOperatorsService;
/**
 *  容器加载完成监听器
 * @author dengqiang
 *
 */
@Controller
public class DataSourceInitListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private IOperatorsService operatorsService;
 	private Logger log = Logger.getLogger(DataSourceInitListener.class);
	private static boolean fisInit=true;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent ev) {
		//防止重复执行。
//		boolean b=ev.getApplicationContext() instanceof FileSystemXmlApplicationContext;
        if(fisInit){
        	fisInit=false;
			 log.error("初始化数据库结构......");
			 try {
				operatorsService.initTable("");
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.error("系统初始化完成!");
        }
	}
}
