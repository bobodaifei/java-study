package com.example.redis.utils.reids;

import redis.clients.jedis.Jedis;

import java.util.*;

public class RedisClient {

    List<String> realNodes = new ArrayList<>();
    int replcaNum = 50;//虚拟节点副本数量，需要大一些，能更好地保证均衡性
    //hash环
    SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();
    //这样好吗？
    Map<String, Jedis> jedisMap = new HashMap<>();


    public RedisClient(List<String> realNodes) {
        for (String node : realNodes) {
            addNode(node);
            addJedis(node);
        }
    }

    private void addJedis(String node) {
        String[] address = node.split(":");
        Jedis jedis = new Jedis(address[0], Integer.parseInt(address[1]));
        jedisMap.put(node, jedis);
    }

    private void addNode(String node) {
        for (int i = 0; i < replcaNum; i++) {
            virtualNodes.put(md5(node + i).hashCode(), node);
        }

        realNodes.add(node);
    }

    private void removeNode(String node) {
        for (int i = 0; i < replcaNum; i++) {
            virtualNodes.remove(md5(node + i));
        }

        realNodes.remove(node);
    }

    public String md5(String source) {
//        return Hashing.md5().newHasher().putString(source, Charsets.UTF_8).hash().toString();
        return Md5Util.generateHash(source);
    }

    public String getRedisNode(String key) {
        int hash = md5(key).hashCode();

        if (virtualNodes.containsKey(hash)) {
            return virtualNodes.get(hash);
        }

        SortedMap<Integer, String> temp = virtualNodes.tailMap(hash);
        //当 hash 的值大于所有虚拟节点的哈希值时。
        if (temp.isEmpty()) {
            return virtualNodes.get(virtualNodes.firstKey());
        }

        //找当前hash第一个遇到的
        return temp.get(temp.firstKey());
    }

    public Jedis getJedis(String key) {
        String node = getRedisNode(key);
        return jedisMap.get(node);
    }

    public String get(String key) {
        Jedis jedis = getJedis(key);
        System.out.println(key + "本次端口为" + jedis.getClient().getPort());
        return jedis.get(key);
    }

    public String set(String key, String value) {
        Jedis jedis = getJedis(key);
        System.out.println(key + "本次端口为" + jedis.getClient().getPort());
        return jedis.set(key, value);
    }


}
