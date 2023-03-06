package com.waylau.netty.demo.filte;

import java.net.Inet4Address;
import java.net.InetAddress;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.IpSubnetFilterRule;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.ipfilter.UniqueIpFilter;

/**
 *@ClassName IpFilterInitializer
 *@Author tao.he
 *@Since 2023/3/6 21:51
 */
public class IpFilterInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//允许
		IpSubnetFilterRule rule = new IpSubnetFilterRule("192.168.123.11", 24, IpFilterRuleType.REJECT);
		// 拒绝
		IpSubnetFilterRule rule2 = new IpSubnetFilterRule("127.0.0.1", 32, IpFilterRuleType.REJECT);
		pipeline.addLast(new RuleBasedIpFilter(rule,rule2));

	}
}
