package com.cml.framework.jdk.map;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @Auther: cml
 * @Date: 2018-08-20 10:06
 * @Description:
 */
public class MapCompare {
    public static void main(String[] args) throws Exception {
        Map<String, String> dbMap = Maps.newHashMap();

        List<String> dbList=new ArrayList<>();

        FileUtils.readLines(new File("C:\\Users\\admin\\Desktop\\db.txt"), "UTF-8").stream().forEach(t -> {
            String[] data = t.split(",");
            dbMap.put(data[0] + "-" + data[1], "true");
            dbList.add(data[0] + "-" + data[1]);
        });

        Map<String, String> kinabaMap = Maps.newHashMap();
        List<String> kibanaList=new ArrayList<>();
        FileUtils.readLines(new File("C:\\Users\\admin\\Desktop\\kibana.csv"), "UTF-8").stream().forEach(t -> {
            String[] data = t.split(",");
            kinabaMap.put(data[0] + "-" + data[1], "true");
            kibanaList.add(data[0] + "-" + data[1]);
        });

        dbList.removeAll(kibanaList);
        kibanaList.removeAll(dbList);

        System.out.println(kibanaList);
        System.out.println(dbList);
        System.out.println("-----");
        System.out.println("-->"+dbMap.size()+":"+kinabaMap.size());
        Iterator<String> it = dbMap.keySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            String key = it.next();
            if (!kinabaMap.containsKey(key)) {
                count++;
                System.out.println(key);
            }
        }
        System.out.println("-==>" + count);
    }
}
