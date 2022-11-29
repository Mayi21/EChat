package com.xaohii.chat.utils.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
	// 每天定时清理接收验证码的数据库，
	// 每小时10分的时候，清理一次数据库
	@Scheduled(cron = "10 * * * *")
	public void cleanCodeDb() {

	}
}
