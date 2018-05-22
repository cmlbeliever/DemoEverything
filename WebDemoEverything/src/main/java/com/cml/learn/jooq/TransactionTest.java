package com.cml.learn.jooq;

import com.cml.learn.jooq.domain.tables.Author;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cml.learn.jooq.domain.Tables.AUTHOR;

@Service
@Transactional
public class TransactionTest {

    @Autowired
    private DSLContext dslContext;

    public void testTransaction() {
        dslContext.update(Author.AUTHOR).set(Author.AUTHOR.LAST_NAME, "update:" + System.currentTimeMillis()).execute();
        dslContext.update(Author.AUTHOR).set(Author.AUTHOR.LAST_NAME, "===update:" + System.currentTimeMillis()).execute();

        Result<Record> result = dslContext.select().from(AUTHOR).fetch();
        for (Record r : result) {
            Long id = r.getValue(AUTHOR.ID);
            String firstName = r.getValue(AUTHOR.FIRST_NAME);
            String lastName = r.getValue(AUTHOR.LAST_NAME);

            System.out.println("testTransaction=======>ID: " + id + " first name: " + firstName + " last name: " + lastName);
        }

        throw new IllegalArgumentException("主动测试异常");
    }
}
