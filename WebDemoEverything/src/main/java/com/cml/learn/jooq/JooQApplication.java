package com.cml.learn.jooq;

import com.cml.learn.jooq.domain.tables.Author;
import org.jooq.*;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.cml.learn.jooq.domain.Tables.AUTHOR;

@EnableTransactionManagement
@SpringBootApplication
public class JooQApplication {
    public static void main(String[] args) {
        SpringApplication.run(JooQApplication.class, args);
    }


    @Autowired
    private DSLContext dslContext;

    @Autowired
    DataSource dataSource;

    @Bean
    RecordListenerProvider provider() {
        return new RecordListenerProvider() {
            @Override
            public RecordListener provide() {
                return new MyRecordListener();
            }
        };
    }

    @Bean
    public MyRecordListener myRecordListener() {
        return new MyRecordListener();
    }

    @Bean
    public ExecuteListenerProvider executeListenerProvider() {
        return MyExecuteListener::new;
    }

    //    @Bean
    public ApplicationRunner runner() {
        return (args) -> {
            System.out.println(dataSource.getClass());
            Result<Record> result = dslContext.select().from(AUTHOR).where(AUTHOR.ID.eq(1L)).fetch();
            for (Record r : result) {
                Long id = r.getValue(AUTHOR.ID);
                String firstName = r.getValue(AUTHOR.FIRST_NAME);
                String lastName = r.getValue(AUTHOR.LAST_NAME);
                System.out.println("=======>ID: " + id + " first name: " + firstName + " last name: " + lastName + ":" + dataSource.getClass());
            }
        };
    }

    @Bean
    public ApplicationRunner testTransaction(TransactionTest transactionTest) {
        return (args) -> {
//            int count = dslContext.update(Author.AUTHOR).set(Author.AUTHOR.LAST_NAME, "update:" + System.currentTimeMillis()).execute();
//            System.out.println("==========================" + count);
            transactionTest.testTransaction();
        };
    }
}
