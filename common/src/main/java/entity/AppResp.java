package entity;

import enums.RespCodeEnum;
import lombok.Data;

@Data
public class AppResp {
	private RespCodeEnum respCodeEnum;
	private String message;
}
