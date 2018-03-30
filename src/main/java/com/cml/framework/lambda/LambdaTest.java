package com.cml.framework.lambda;

import java.util.Arrays;
import java.util.function.Predicate;

public class LambdaTest {
	public static void main(String[] args) {
		testPredicate((str) -> ((String) str).length() > 4, "test11");

		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		Arrays.asList("a", "b", "Jddc").stream().filter(startsWithJ.and(fourLetterLong))
				.forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));

	}

	static void testPredicate(Predicate<String> p, String data) {
		p.test(data);
	}
}
