//package com.xaohii.flow.config;
//
//public class FlowConfig {
//}
//public class CustomFlowControlConfigurer implements FlowControlConfigurer {
//	/*** * 配置redis客户端 */
//	@Override
//	public RedissonClient redissonClient() {
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://localhost:6379");
//		RedissonClient redisson = Redisson.create(config);return redisson;
//	}
//	/*** * 配置拦截成功后-执行的动作 */
//	@Override
//	public FlowControlInterceptAction flowControlInterceptAction() {
//		return new DefaultFlowControlInterceptAction();
//	}
//	/*** * 配置拦截数据来源 */
//	@Override
//	public FlowControlDao flowControlDao() {
//		return new InMemoryFlowControlDao();
//	}
//	/*** * 配置redis key的命名策略 */
//	@Override
//	public RedisKeyNameStrategy redisKeyNameStrategy() {
//		return new DefaultRedisKeyNameStrategy();
//	}
//}