package cn.xaohii.common.enums;

public enum RespCodeEnum {
	SUCCESS("success"),
	FAIL("fail"),
	EXCEPTION("exception");

	private String status;
	RespCodeEnum(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
}
