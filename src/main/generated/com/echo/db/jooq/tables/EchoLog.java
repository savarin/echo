/*
 * This file is generated by jOOQ.
 */
package com.echo.db.jooq.tables;


import com.echo.db.jooq.DefaultSchema;
import com.echo.db.jooq.Keys;
import com.echo.db.jooq.tables.records.EchoLogRecord;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EchoLog extends TableImpl<EchoLogRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>echo_log</code>
     */
    public static final EchoLog ECHO_LOG = new EchoLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EchoLogRecord> getRecordType() {
        return EchoLogRecord.class;
    }

    /**
     * The column <code>echo_log.id</code>.
     */
    public final TableField<EchoLogRecord, String> ID = createField(DSL.name("id"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>echo_log.message</code>.
     */
    public final TableField<EchoLogRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>echo_log.created_at</code>.
     */
    public final TableField<EchoLogRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private EchoLog(Name alias, Table<EchoLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private EchoLog(Name alias, Table<EchoLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>echo_log</code> table reference
     */
    public EchoLog(String alias) {
        this(DSL.name(alias), ECHO_LOG);
    }

    /**
     * Create an aliased <code>echo_log</code> table reference
     */
    public EchoLog(Name alias) {
        this(alias, ECHO_LOG);
    }

    /**
     * Create a <code>echo_log</code> table reference
     */
    public EchoLog() {
        this(DSL.name("echo_log"), null);
    }

    public <O extends Record> EchoLog(Table<O> child, ForeignKey<O, EchoLogRecord> key) {
        super(child, key, ECHO_LOG);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<EchoLogRecord> getPrimaryKey() {
        return Keys.ECHO_LOG__PK_ECHO_LOG;
    }

    @Override
    public EchoLog as(String alias) {
        return new EchoLog(DSL.name(alias), this);
    }

    @Override
    public EchoLog as(Name alias) {
        return new EchoLog(alias, this);
    }

    @Override
    public EchoLog as(Table<?> alias) {
        return new EchoLog(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public EchoLog rename(String name) {
        return new EchoLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public EchoLog rename(Name name) {
        return new EchoLog(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public EchoLog rename(Table<?> name) {
        return new EchoLog(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
