package cn.xaohii.common.entity;

import cn.xaohii.common.enums.RespCodeEnum;
import lombok.Data;

@Data
public class AppResp {
	private RespCodeEnum respCodeEnum;
	private String message;
}
