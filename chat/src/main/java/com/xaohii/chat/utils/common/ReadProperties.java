package com.xaohii.chat.utils.common;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * 读取配置文件信息
 *
 * */
public class ReadProperties {
	private final String mailPropertiesPath = "properties/mail.properties";

	private final String propertiesPath = "application.properties";

	/**
	 * 从邮箱的配置文件读取
	 * */
	public Properties getMailProperties() {
		Properties properties = readFile(mailPropertiesPath);
		if (Objects.isNull(properties)) {
			return null;
		} else {
			return properties;
		}
	}

	public Properties getProperties() {
		Properties properties = readFile(propertiesPath);
		if (Objects.isNull(properties)) {
			return null;
		} else {
			return properties;
		}
	}

	/**
	 * 根据输入的文件路径，来读取文件配置信息
	 * @param path:配置文件的路径
	 * */
	private Properties readFile(String path) {
		Properties properties = new Properties();
		try(InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(path)) {
			properties.load(inputStream);
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
