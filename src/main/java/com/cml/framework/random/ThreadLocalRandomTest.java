package com.cml.framework.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * http://ifeve.com/并发包中threadlocalrandom类原理剖析/
 * <p>
 * 解决并发情况下随机数种子并发导致的效率偏低问题
 * </p>
 * 
 * @author cml
 *
 */
public class ThreadLocalRandomTest {
	public static void main(String[] args) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		System.out.println(random.nextInt(100));
	}
}
