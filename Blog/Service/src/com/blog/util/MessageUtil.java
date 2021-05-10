package com.blog.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Not Uses
 */
@Component
public class MessageUtil {
	@Autowired
	private static ResourceBundleMessageSource messageSource;
	
	@PostConstruct
	public void init(){
		messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("com/longforever/messages/message");
	}
	
	public static String getMessage(String text,Locale locale){
		if(messageSource==null){
			return text;
		}
		return messageSource.getMessage(text, null ,locale);
	}
	
}
