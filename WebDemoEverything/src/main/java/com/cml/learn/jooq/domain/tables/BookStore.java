/*
 * This file is generated by jOOQ.
*/
package com.cml.learn.jooq.domain.tables;


import com.cml.learn.jooq.domain.Indexes;
import com.cml.learn.jooq.domain.Jooqtest;
import com.cml.learn.jooq.domain.Keys;
import com.cml.learn.jooq.domain.tables.records.BookStoreRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookStore extends TableImpl<BookStoreRecord> {

    private static final long serialVersionUID = 730294907;

    /**
     * The reference instance of <code>jooqtest.book_store</code>
     */
    public static final BookStore BOOK_STORE = new BookStore();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookStoreRecord> getRecordType() {
        return BookStoreRecord.class;
    }

    /**
     * The column <code>jooqtest.book_store.name</code>.
     */
    public final TableField<BookStoreRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(400).nullable(false), this, "");

    /**
     * Create a <code>jooqtest.book_store</code> table reference
     */
    public BookStore() {
        this(DSL.name("book_store"), null);
    }

    /**
     * Create an aliased <code>jooqtest.book_store</code> table reference
     */
    public BookStore(String alias) {
        this(DSL.name(alias), BOOK_STORE);
    }

    /**
     * Create an aliased <code>jooqtest.book_store</code> table reference
     */
    public BookStore(Name alias) {
        this(alias, BOOK_STORE);
    }

    private BookStore(Name alias, Table<BookStoreRecord> aliased) {
        this(alias, aliased, null);
    }

    private BookStore(Name alias, Table<BookStoreRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Jooqtest.JOOQTEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.BOOK_STORE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BookStoreRecord>> getKeys() {
        return Arrays.<UniqueKey<BookStoreRecord>>asList(Keys.KEY_BOOK_STORE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookStore as(String alias) {
        return new BookStore(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookStore as(Name alias) {
        return new BookStore(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BookStore rename(String name) {
        return new BookStore(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BookStore rename(Name name) {
        return new BookStore(name, null);
    }
}