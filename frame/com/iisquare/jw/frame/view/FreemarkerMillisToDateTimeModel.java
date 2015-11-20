package com.iisquare.jw.frame.view;

import java.util.List;

import com.iisquare.jw.frame.FrameConfiguration;
import com.iisquare.jw.frame.util.DPUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 将毫秒转换为日期格式
 */
public class FreemarkerMillisToDateTimeModel implements TemplateMethodModelEx {

	private FrameConfiguration configuration;
	
	public FreemarkerMillisToDateTimeModel(FrameConfiguration configuration) {
		super();
		this.configuration = configuration;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		if (list == null || list.size() < 1) {  
            throw new TemplateModelException("Millis value is null!");  
        }
		Long millis = DPUtil.parseLong(list.get(0));
		String datetimeFormat;
		if(list.size() >= 2) {
			datetimeFormat =  list.get(1).toString();
		} else {
			datetimeFormat =  configuration.getDateTimeFormat();
		}
		return DPUtil.millisToDateTime(millis, datetimeFormat);
	}
}