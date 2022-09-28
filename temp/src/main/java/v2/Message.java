package v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
	@JsonProperty(value = "source_name")
	private String sourceName;

	private String message;

	@JsonProperty(value = "to_name")
	private String toName;

	/**
	 * 类型目前有：0:普通消息;1:注册消息
	 * */
	private int type;

	@Override
	public String toString() {
		return "Message{" +
				"sourceName='" + sourceName + '\'' +
				", message='" + message + '\'' +
				", toName='" + toName + '\'' +
				'}';
	}
}
