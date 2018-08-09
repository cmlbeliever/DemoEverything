package com.cml.learn.jooq;

import com.cml.learn.jooq.domain.AuthorBean;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static com.cml.learn.jooq.domain.Tables.AUTHOR;

public class JooQTest {
    public static void main(String[] args) {
        String userName = "root";
        String password = "root";
        String url = "jdbc:mysql://192.168.99.100:3307/jooqtest";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> result = create.select().from(AUTHOR).fetch();

            List<Condition> list = new ArrayList<>();
            list.add(AUTHOR.ID.eq(1L).or(AUTHOR.ID.eq(2L)));
            System.out.println(create.select().from(AUTHOR).where(list).getSQL());

            for (Record r : result) {
                Long id = r.getValue(AUTHOR.ID);
                String firstName = r.getValue(AUTHOR.FIRST_NAME);
                String lastName = r.getValue(AUTHOR.LAST_NAME);

                System.out.println("=======>ID: " + id + " first name: " + firstName + " last name: " + lastName);
            }

            String lastName = create.select(AUTHOR.LAST_NAME).from(AUTHOR).limit(1).fetchOptionalInto(String.class).orElse("none");
            System.out.println("===>lastname:" + lastName);
            AuthorBean bean=     create.select(AUTHOR.LAST_NAME).from(AUTHOR).limit(1).fetchOneInto(AuthorBean.class);
            System.out.println("bean--->"+bean.getLastName());

        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
