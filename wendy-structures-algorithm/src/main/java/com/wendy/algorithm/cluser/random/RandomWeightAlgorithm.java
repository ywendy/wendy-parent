package com.wendy.algorithm.cluser.random;

import com.wendy.algorithm.cluser.Server;

import java.util.*;

/**
 * Created by Administrator on 2017/11/16.
 */
public class RandomWeightAlgorithm {
    public final Random random = new Random();
    public Server randomServer(List<Server> servers){
        //计算总权重数以及是否都一样
        int totalWeight = 0;
        boolean sameWeight = true;
        int index = 0;
        for (Server server : servers){
            totalWeight+=server.getWeight();
            if (sameWeight&&index>0&&server.getWeight()!=servers.get(index-1).getWeight()){
                sameWeight = false;
            }
            index++;
        }

        if (!sameWeight&&totalWeight>0){
            int offset = random.nextInt(totalWeight);
            int tmpWeight = 0;
            for (Server server:servers){
                tmpWeight+= server.getWeight();
                if (tmpWeight>offset){
                    return server;
                }
            }
        }
        //如果权重都相等直接随机一个
        return servers.get(random.nextInt(servers.size()));


    }

    public static void main(String[] args) {
        Server server1= new Server("192.168.0.100",8080,1);
        Server server2= new Server("192.168.0.101",8080,2);
        Server server3= new Server("192.168.0.102",8080,3);
        Server server4= new Server("192.168.0.103",8080,4);
        Server server5= new Server("192.168.0.104",8080,5);
        List<Server> list = new ArrayList<>();
        list.add(server1);
        list.add(server2);
        list.add(server3);
        list.add(server4);
        list.add(server5);
        RandomWeightAlgorithm randomWeightAlgorithm = new RandomWeightAlgorithm();
        Map<Server,Integer> result = new HashMap<>();
        for (int i=0;i<1000;i++){
            Server server = randomWeightAlgorithm.randomServer(list);
            if (result.containsKey(server)){
                result.put(server,result.get(server)+1);
            }else {
                result.put(server,1);
            }
        }

/**
 * 100次：{Server [host=192.168.0.104, port=8080, weight=5]=20, Server [host=192.168.0.103, port=8080, weight=4]=20, Server [host=192.168.0.102, port=8080, weight=3]=18, Server [host=192.168.0.101, port=8080, weight=2]=21, Server [host=192.168.0.100, port=8080, weight=1]=21}
 * 10000次:
 */
        System.out.println(result);


    }


}
