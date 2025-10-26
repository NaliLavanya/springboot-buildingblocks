package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworldController")
public class HelloWorldControllerI18N {

	@Autowired
	private ResourceBundleMessageSource messageSource;
//	@GetMapping("/Internationalization")
//	public String getIntMethod(@RequestHeader(name="AcceptLanguage",required=false)String locale)
//	{
//		   if (locale == null || locale.isEmpty()) {
//		        locale = "en"; // default locale
//		    }
//		//    Locale locale = LocaleContextHolder.getLocale();
//		return messageSource.getMessage("label.hello",null,new Locale(locale));
//		
//	}
	@GetMapping("/Internationalization")
	public String getIntMethod() {
	    Locale locale = LocaleContextHolder.getLocale();
	    return messageSource.getMessage("label.hello", null, locale);
	}
}
