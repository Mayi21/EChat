package cn.xaohii.common.enums;

/**
 * 消息类型的枚举类型：
 * 通知：暂无用
 * 单聊：一对一
 * 群聊：一对多
 * 广播：暂时无用
 * */
public enum MessageTypeEnum {
	NOTIFICATION("notification"),
	MESSAGE("message"),
	SINGLE("single"),
	GROUP("group"),
	BROADCAST("broadcast");

	private String value;

	MessageTypeEnum(String value) {
		this.value = value;
	}
}
