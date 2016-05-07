package com.wendy.algorithm.cluser.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.wendy.algorithm.cluser.Server;

/**
 * 随机算法
 * 
 * 1、按服务器列表随机
 * 
 * 在运行了1000次后，基本可以达到平均分配.
 * 
 * 2、按照服务器权重随机
 * 
 * 运行1000次，按权重平均分配.
 * 
 * @author tony
 *
 */
public class RandomAlgorithm {

	private List<Server> servers;
	private int totalWeight;
	private final Random random = new Random();

	public RandomAlgorithm(List<Server> servers) {
		this.servers = servers;
		init();
	}

	private void init() {
		for (int i = 0; i < servers.size(); i++) {
			this.totalWeight += servers.get(i).getWeight();
		}
	}

	public Server randomServer() {
		return servers.get(random.nextInt(servers.size()));
	}

	public Server randomWeightServer() {
		int offset = random.nextInt(totalWeight);
		for (int i = 0; i < servers.size(); i++) {
			offset -= servers.get(i).getWeight();
			if (offset < 0) {
				return servers.get(i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Server server1 = new Server("192.168.0.101", 8080, 1);
		Server server2 = new Server("192.168.0.102", 8080, 3);
		Server server3 = new Server("192.168.0.103", 8080, 4);
		List<Server> servers = new ArrayList<>();
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);

		RandomAlgorithm r = new RandomAlgorithm(servers);

		Map<Server, Integer> result = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			Server s = r.randomWeightServer();
			Integer tmp = result.get(s);
			if (tmp != null && tmp != 0) {
				result.put(s, tmp + 1);
			} else {
				result.put(s, 1);
			}
		}
		System.out.println("randomWeightServer=>" + result);

		Map<Server, Integer> result1 = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			Server s = r.randomServer();
			Integer tmp = result1.get(s);
			if (tmp != null && tmp != 0) {
				result1.put(s, tmp + 1);
			} else {
				result1.put(s, 1);
			}
		}
		System.out.println("randomServer=>" + result1);

	}

}
