package com.example.redis.utils.reids;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class LZHash {
    static List<String> realNodes = new ArrayList<>();
    int replcaNum = 50;//虚拟节点副本数量，需要大一些，能更好地保证均衡性
    static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

    public LZHash(List<String> realNodes) {
        for (String node : realNodes) {
            addNode(node);
        }
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

    public static String md5(String source) {
//        return Hashing.md5().newHasher().putString(source, Charsets.UTF_8).hash().toString();
        return Md5Util.generateHash(source);
    }

    public static String getRedisNode(String key) {
        int hash = md5(key).hashCode();

        if (virtualNodes.containsKey(hash)) {
            return virtualNodes.get(hash);
        }

        SortedMap<Integer, String> temp = virtualNodes.tailMap(hash);
        if (temp.isEmpty()) {
            return virtualNodes.get(virtualNodes.firstKey());
        }

        return temp.get(temp.firstKey());

    }

    public static void main(String[] args) {
        List<String> realNodes = new ArrayList<String>();
        realNodes.add("192.168.96.220:6379");
        realNodes.add("192.168.99.233:6379");
        realNodes.add("192.168.99.60:6379");
        LZHash h = new LZHash(realNodes);

        String keyPrefix = "binova_shopcart_";
        int node220 = 0;
        int node233 = 0;
        int node60 = 0;
        for (int i = 0; i < 100000; i++) {
            String key = keyPrefix + i;
            String node = getRedisNode(key);
            if (node.equals("192.168.96.220:6379")) {
                node220++;
            }
            if (node.equals("192.168.99.233:6379")) {
                node233++;
            }
            if (node.equals("192.168.99.60:6379")) {
                node60++;
            }
        }
        System.out.println("node220:" + node220);
        System.out.println("node233:" + node233);
        System.out.println("node60:" + node60);
    }

}