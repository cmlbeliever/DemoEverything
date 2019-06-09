package com.cml.framework.worksummary.diff;

import com.cml.framework.worksummary.diff.comparator.DiffComparator;
import com.cml.framework.worksummary.diff.comparator.StringDiffComparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiffCollectorTest {

    @Test
    public void testBean() {
        List<Bean> srcList = new ArrayList<>();
        srcList.add(new Bean(1, "name1", "desc1", 1));
        srcList.add(new Bean(2, "name2", "desc2", 2));
        srcList.add(new Bean(3, "name3", "desc3", 3));
        srcList.add(new Bean(4, "name4", "desc4", 4));

        List<Bean> stdList = new ArrayList<>();
        stdList.add(new Bean(2, "name2", "desc2", 2));
        stdList.add(new Bean(3, "name3-changed", "desc3", 3));
        stdList.add(new Bean(4, "name4", "desc4-changed", 4));
        stdList.add(new Bean(5, "name5", "desc5", 5));


        DiffContainer<Bean> calDiff = DiffCollector.calDiff(srcList, stdList, new DiffComparator<Bean>() {
            @Override
            public String generateKey(Bean bean) {
                return bean.getId() + "";
            }

            @Override
            public boolean equals(Bean src, Bean std) {
                return src.getId().equals(std.getId())
                        && src.getName().equals(std.getName())
                        && src.getDesc().equals(std.getDesc())
                        && src.getAge().equals(std.getAge());
            }
        });

        //add
        assert calDiff.getElementAddList().size() == 1 && calDiff.getElementAddList().get(0).getId() == 1;
        //unchanged
        assert calDiff.getElementUnChangeList().size() == 1 && calDiff.getElementUnChangeList().get(0).getId() == 2;
        //changed
        assert calDiff.getElementChangeList().size() == 2 && calDiff.getElementChangeList().stream().filter(t -> t.getId() == 3 || t.getId() == 4).collect(Collectors.toList()).size() == 2;
        //lost
        assert calDiff.getElementLostList().size() == 1 && calDiff.getElementLostList().get(0).getId() == 5;

    }


    @Test
    public void testStringList() {
        List<String> srcList = new ArrayList<>();
        srcList.add("1");
        srcList.add("2");
        srcList.add("3");
        List<String> stdList = new ArrayList<>();
        stdList.add("2");
        stdList.add("4");

        DiffContainer<String> diffContainer = DiffCollector.calDiff(srcList, stdList, new StringDiffComparator());

        assert diffContainer.getElementLostList().size() == 1;
        assert diffContainer.getElementLostList().get(0).equals("4");
        assert diffContainer.getElementChangeList().size() == 0;
        assert diffContainer.getElementUnChangeList().size() == 1;
        assert diffContainer.getElementUnChangeList().get(0).equals("2");
        assert diffContainer.getElementAddList().size() == 2;
        assert diffContainer.getElementAddList().contains("1") && diffContainer.getElementAddList().contains("3");
    }

    static class Bean {
        private Integer id;
        private String name;
        private String desc;
        private Integer age;

        public Bean(Integer id, String name, String desc, Integer age) {
            this.id = id;
            this.name = name;
            this.desc = desc;
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
