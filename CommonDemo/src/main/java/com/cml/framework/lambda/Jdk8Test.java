package com.cml.framework.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Jdk8Test {
	public static void main(String[] args) {
		Jdk8Test test = new Jdk8Test();

		String name = test.testBiFunction((x, y) -> {
			return x + ":" + y;
		}, "name1", "name2");

		System.out.println(name);

		name = test.testFunction(a -> a + " append name", "this is mine :");
		System.out.println(name);

		String supplierTest = test.testSupplier(() -> "XXXX");
		System.out.println("supplier test:" + supplierTest);

		test.testConsumer(t -> {
			System.out.println("consumer value:" + t);
		}, "name");
	}

	<T> void testConsumer(Consumer<T> su, T t) {
		su.accept(t);
	}

	<T> T testSupplier(Supplier<T> sup) {
		return sup.get();
	}

	public <T, R> R testFunction(Function<T, R> fun, T t) {
		return fun.apply(t);
	}

	public <T, U, R> R testBiFunction(BiFunction<T, U, R> bf, T t, U u) {
		return bf.apply(t, u);
	}
}
