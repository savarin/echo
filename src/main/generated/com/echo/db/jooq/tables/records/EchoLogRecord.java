/*
 * This file is generated by jOOQ.
 */
package com.echo.db.jooq.tables.records;


import com.echo.db.jooq.tables.EchoLog;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EchoLogRecord extends UpdatableRecordImpl<EchoLogRecord> implements Record3<String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>echo_log.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>echo_log.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>echo_log.message</code>.
     */
    public void setMessage(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>echo_log.message</code>.
     */
    public String getMessage() {
        return (String) get(1);
    }

    /**
     * Setter for <code>echo_log.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>echo_log.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return EchoLog.ECHO_LOG.ID;
    }

    @Override
    public Field<String> field2() {
        return EchoLog.ECHO_LOG.MESSAGE;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return EchoLog.ECHO_LOG.CREATED_AT;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getMessage();
    }

    @Override
    public LocalDateTime component3() {
        return getCreatedAt();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getMessage();
    }

    @Override
    public LocalDateTime value3() {
        return getCreatedAt();
    }

    @Override
    public EchoLogRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public EchoLogRecord value2(String value) {
        setMessage(value);
        return this;
    }

    @Override
    public EchoLogRecord value3(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public EchoLogRecord values(String value1, String value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EchoLogRecord
     */
    public EchoLogRecord() {
        super(EchoLog.ECHO_LOG);
    }

    /**
     * Create a detached, initialised EchoLogRecord
     */
    public EchoLogRecord(String id, String message, LocalDateTime createdAt) {
        super(EchoLog.ECHO_LOG);

        setId(id);
        setMessage(message);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }
}
