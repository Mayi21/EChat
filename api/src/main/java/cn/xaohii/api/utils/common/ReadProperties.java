package cn.xaohii.api.utils.common;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ReadProperties {
	private final String mailPropertiesPath = "properties/mail.properties";

	private final String propertiesPath = "application.properties";

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
