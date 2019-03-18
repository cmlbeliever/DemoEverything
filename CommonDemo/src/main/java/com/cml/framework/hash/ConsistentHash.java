package com.cml.framework.hash;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConsistentHash<T> {

    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("node1", "node2", "node3", "node4");
        ConsistentHash<String> consistentHash = new ConsistentHash(nodes);

        Map<String, Integer> counterMap = new HashMap<>();

        int testCount = 1000 * nodes.size();
        for (int i = 0; i < testCount; i++) {
            String key = "key" + i;
            String node = consistentHash.select(key);
            Integer value = counterMap.get(node);
            counterMap.put(node, value == null ? 1 : value + 1);
        }
        System.out.println(counterMap);
    }

    private TreeMap<Long, T> virtualPoint = new TreeMap<>();
    private int replicaNumber = 160;


    public ConsistentHash(List<T> nodes) {
        for (int i = 0, len = nodes.size(); i < len; i++) {
            T node = nodes.get(i);
            for (int j = 0; j < replicaNumber; j++) {
                long hash = hash(toKey(i, j));
                virtualPoint.put(hash, node);
            }
        }
    }

    private String toKey(int i, int j) {
        return "node-" + i + "-" + j;
    }


    public T select(String key) {
        Map.Entry<Long, T> entry = virtualPoint.ceilingEntry(hash(key));
        if (null == entry) {
            return virtualPoint.firstEntry().getValue();
        }
        return entry.getValue();
    }

    /**
     * MurMurHash算法，是非加密HASH算法，性能很高，
     * 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
     * 等HASH算法要快很多，而且据说这个算法的碰撞率很低.
     * http://murmurhash.googlepages.com/
     */
    private Long hash(String key) {

        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }


    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes;
        try {
            bytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.update(bytes);
        return md5.digest();
    }
}
