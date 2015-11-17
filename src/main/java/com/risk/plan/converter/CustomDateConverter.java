package com.risk.plan.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date>{

	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}

}
