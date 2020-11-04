/*
 * This file is generated by jOOQ.
 */
package com.nutrymaco.parser.model.tables;


import com.nutrymaco.parser.model.Keys;
import com.nutrymaco.parser.model.ParserService;
import com.nutrymaco.parser.model.tables.records.VacancyRecord;

import java.sql.Date;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row12;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Vacancy extends TableImpl<VacancyRecord> {

    private static final long serialVersionUID = -729564630;

    /**
     * The reference instance of <code>parser_service.vacancy</code>
     */
    public static final Vacancy VACANCY = new Vacancy();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VacancyRecord> getRecordType() {
        return VacancyRecord.class;
    }

    /**
     * The column <code>parser_service.vacancy.id</code>.
     */
    public final TableField<VacancyRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('parser_service.vacancy_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>parser_service.vacancy.name</code>.
     */
    public final TableField<VacancyRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>parser_service.vacancy.description</code>.
     */
    public final TableField<VacancyRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>parser_service.vacancy.company_name</code>.
     */
    public final TableField<VacancyRecord, String> COMPANY_NAME = createField(DSL.name("company_name"), org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>parser_service.vacancy.city</code>.
     */
    public final TableField<VacancyRecord, String> CITY = createField(DSL.name("city"), org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>parser_service.vacancy.created</code>.
     */
    public final TableField<VacancyRecord, Date> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>parser_service.vacancy.salary_from</code>.
     */
    public final TableField<VacancyRecord, Integer> SALARY_FROM = createField(DSL.name("salary_from"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>parser_service.vacancy.salary_to</code>.
     */
    public final TableField<VacancyRecord, Integer> SALARY_TO = createField(DSL.name("salary_to"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>parser_service.vacancy.currency</code>.
     */
    public final TableField<VacancyRecord, String> CURRENCY = createField(DSL.name("currency"), org.jooq.impl.SQLDataType.CHAR(3), this, "");

    /**
     * The column <code>parser_service.vacancy.experience_from</code>.
     */
    public final TableField<VacancyRecord, Integer> EXPERIENCE_FROM = createField(DSL.name("experience_from"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>parser_service.vacancy.experience_to</code>.
     */
    public final TableField<VacancyRecord, Integer> EXPERIENCE_TO = createField(DSL.name("experience_to"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>parser_service.vacancy.url</code>.
     */
    public final TableField<VacancyRecord, String> URL = createField(DSL.name("url"), org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>parser_service.vacancy</code> table reference
     */
    public Vacancy() {
        this(DSL.name("vacancy"), null);
    }

    /**
     * Create an aliased <code>parser_service.vacancy</code> table reference
     */
    public Vacancy(String alias) {
        this(DSL.name(alias), VACANCY);
    }

    /**
     * Create an aliased <code>parser_service.vacancy</code> table reference
     */
    public Vacancy(Name alias) {
        this(alias, VACANCY);
    }

    private Vacancy(Name alias, Table<VacancyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Vacancy(Name alias, Table<VacancyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Vacancy(Table<O> child, ForeignKey<O, VacancyRecord> key) {
        super(child, key, VACANCY);
    }

    @Override
    public Schema getSchema() {
        return ParserService.PARSER_SERVICE;
    }

    @Override
    public Identity<VacancyRecord, Integer> getIdentity() {
        return Keys.IDENTITY_VACANCY;
    }

    @Override
    public Vacancy as(String alias) {
        return new Vacancy(DSL.name(alias), this);
    }

    @Override
    public Vacancy as(Name alias) {
        return new Vacancy(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Vacancy rename(String name) {
        return new Vacancy(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Vacancy rename(Name name) {
        return new Vacancy(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, String, String, String, String, Date, Integer, Integer, String, Integer, Integer, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}
