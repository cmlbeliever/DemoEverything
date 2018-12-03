package com.cml.framework.jdk.io;


import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: cml
 * @Date: 2018-11-29 08:52
 * @Description:
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\gitrepository\\DemoEverything\\CommonDemo\\src\\main\\resources\\test.txt");
//        readFile(file);
        writeFile(file);
        readFile(file);
    }

    private static void writeFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(300);

        String data = "this is write by channel" + System.currentTimeMillis() + " \r\n";
        byte[] target = data.getBytes();
        int offset = 0;


        System.out.println(byteBuffer.capacity());
        while (true) {

            if (offset >= target.length) {
                break;
            }

            byteBuffer.put(target, offset, Math.min(byteBuffer.capacity(), target.length - offset));
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()) {
                int count = channel.write(byteBuffer);
                System.out.println("写入数据：" + count);
            }
            byteBuffer.clear();
            offset += byteBuffer.limit();
        }


        channel.force(true);
        channel.close();
        fileOutputStream.close();


    }

    private static void readFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer data = ByteBuffer.allocate(1024);
        int read = fileChannel.read(data);
        System.out.println("readCount:" + read);
        System.out.println(new String(data.array()));

        fileChannel.close();
        fileInputStream.close();
    }
}
