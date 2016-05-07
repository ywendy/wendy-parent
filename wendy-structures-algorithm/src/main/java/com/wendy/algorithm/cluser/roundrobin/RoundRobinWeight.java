package com.wendy.algorithm.cluser.roundrobin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wendy.algorithm.cluser.Server;

/**
 * 轮询权重调度算法.
 * 
 * @author tony
 *
 */
public final class RoundRobinWeight {

	private int currentServerIndex;// 当前选择的服务器.
	private int currentServerWeight;// 当前服务器的权重
	private int serverCount;// 机器数量.
	private int serverWeightGcd;// 机器列表的最大公约数
	private int serverMaxWeight;// 机器列表中最大的权重值.
	private List<Server> servers;// 服务器列表.

	public RoundRobinWeight(List<Server> servers) {
		this.servers = servers;
		init();
	}

	private void init() {

		this.currentServerIndex = -1;
		this.currentServerWeight = 0;
		this.serverCount = servers == null || servers.size() <= 0 ? 0 : this.servers.size();
		this.serverMaxWeight = getServerMaxWeight();
		this.serverWeightGcd = getServerWeightGcd();

	}

	/***
	 * 获取机器信息
	 * 
	 * 1、初始化当前持有权重为最大值
	 * 
	 * 2、以最大公约数进行递减
	 * 
	 * 3、轮询到第一台机器后持有权重重置为最大权重
	 * 
	 * @return {@link Server}
	 */
	public Server getServer() {

		if (servers == null || servers.size() <= 0) {
			return null;
		}
		while (true) {
			currentServerIndex = (currentServerIndex + 1) % serverCount;// 保证每次请求都落在服务器的数量范围内.

			if (currentServerIndex == 0) {// 一个轮询结束，重新计算当前持有的权重值
				currentServerWeight = currentServerWeight - serverWeightGcd;
				if (currentServerWeight <= 0) {
					currentServerWeight = serverMaxWeight;
				}
			}
			if (servers.get(currentServerIndex).getWeight() >= currentServerWeight) {
				return servers.get(currentServerIndex);
			}
		}
	}

	/**
	 * 获取服务器列表中的最大公约数.
	 * 
	 * @return
	 */
	private int getServerWeightGcd() {

		int gcdValue = 0;
		if (this.servers != null && this.servers.size() > 0) {

			int len = this.servers.size();

			if (len == 1) {
				gcdValue = servers.get(0).getWeight();
			} else {
				gcdValue = gcd(servers.get(0).getWeight(), servers.get(1).getWeight());
				for (int i = 2; i < len && len > 2; i++) {
					gcdValue = gcd(gcdValue, servers.get(i).getWeight());
				}

			}
		}

		return gcdValue;
	}

	private int gcd(int a, int b) {

		while (b != 0) {
			int tmp = a % b;
			a = b;
			b = tmp;
		}
		return a;
	}

	/**
	 * 获取服务器列表的最大权重.
	 * 
	 * @return
	 */
	private int getServerMaxWeight() {
		int maxWeight = 0;
		if (this.servers != null && this.servers.size() > 0) {
			for (int i = 0; i < this.servers.size(); i++) {
				int currentWeight = servers.get(i).getWeight();
				if (maxWeight < currentWeight) {
					maxWeight = currentWeight;
				}
			}
		}
		return maxWeight;
	}

	public static void main(String[] args) {
		Server server1 = new Server("192.168.0.101", 8080, 1);
		Server server2 = new Server("192.168.0.102", 8080, 3);
		Server server3 = new Server("192.168.0.103", 8080, 0);
		List<Server> servers = new ArrayList<>();
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);

		RoundRobinWeight r = new RoundRobinWeight(servers);
		System.out.println(r.getServerMaxWeight());
		System.out.println(r.getServerWeightGcd());
		Map<Server, Integer> result = new HashMap<>();
		for (int i = 0; i < 12; i++) {
			Server s = r.getServer();
			Integer tmp = result.get(s);
			if (tmp != null && tmp != 0) {
				result.put(s, tmp + 1);
			} else {
				result.put(s, 1);
			}
			System.out.println(result);
		}

	}

}
