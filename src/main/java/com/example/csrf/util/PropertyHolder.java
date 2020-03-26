package com.example.csrf.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PropertyHolder {

	public static Boolean getBooleanProperty(String name) {
		return getPropertyValue(name, Boolean.class) != null ? getPropertyValue(name, Boolean.class) : null;
	}

	private static <T> T getPropertyValue(String name, Class<T> clazz) {
		return BeanUtil.getBean(Environment.class).getProperty(name, clazz);
	}
}
