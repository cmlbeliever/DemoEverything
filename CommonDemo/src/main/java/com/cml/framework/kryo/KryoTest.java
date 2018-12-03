package com.cml.framework.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;

import java.io.*;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * @Auther: cml
 * @Date: 2018-11-30 17:49
 * @Description:
 */
public class KryoTest {


    private static ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        System.out.println("====init-----------");
        return kryo;
    });

    public static void main(String[] args) throws InterruptedException {
//        register(kryo, User.class);
//        register(kryo, Date.class);

        long start = System.currentTimeMillis();
        IntStream.range(1, 10000).forEach(t -> {
            testSerializableAndDeseriablize();
        });
        System.out.println("kryo耗时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        IntStream.range(1, 10000).forEach(t -> {
            try {
                testJDKSerializer();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        System.out.println("jdk耗时：" + (System.currentTimeMillis() - start));
    }

    private static void testJDKSerializer() throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("name");
        user.setAge(11);
        user.setMan(true);
        user.setTest(User.Test.ONE);
        user.setTime(new Date(System.currentTimeMillis()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        User user1 = (User) objectInputStream.readObject();
    }

    private static void testSerializableAndDeseriablize() {
        Kryo kryo = kryoThreadLocal.get();
        kryo.setRegistrationRequired(false);

        User user = new User();
        user.setName("name");
        user.setAge(11);
        user.setMan(true);
        user.setTest(User.Test.ONE);
        user.setTime(new Date(System.currentTimeMillis()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeClassAndObject(output, user);
        output.close();


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Input input = new Input(byteArrayInputStream);

        user = (User) kryo.readClassAndObject(input);
    }

    private static void register(Kryo kryo, Class claz) {
        kryo.register(claz);
    }
}
