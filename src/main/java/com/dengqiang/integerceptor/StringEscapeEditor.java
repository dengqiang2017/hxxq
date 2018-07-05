package com.dengqiang.integerceptor;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import java.beans.PropertyEditorSupport;
/**
 * 防止XSS攻击
 * @author 邓强
 */
public class StringEscapeEditor extends PropertyEditorSupport {
	private boolean escapeHTML;// 编码HTML
	private boolean escapeJavaScript;// 编码javascript
	public StringEscapeEditor() {
		super();
	}
	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
	}
	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				value = HtmlUtils.htmlEscape(value);//将html编码
//				String s2 = HtmlUtils.htmlUnescape(s); //解码为html代码
			}
			if (escapeJavaScript) {
				value = JavaScriptUtils.javaScriptEscape(value);
			}
			setValue(value);
		}
	}
}