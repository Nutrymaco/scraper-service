/*
 * This file is generated by jOOQ.
 */
package com.nutrymaco.parser.model.tables.records;


import com.nutrymaco.parser.model.tables.Vacancy_2020_11;

import java.sql.Date;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.TableRecordImpl;


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
public class Vacancy_2020_11Record extends TableRecordImpl<Vacancy_2020_11Record> implements Record12<Integer, String, String, String, String, Date, Integer, Integer, String, Integer, Integer, String> {

    private static final long serialVersionUID = 2069178358;

    /**
     * Setter for <code>parser_service.vacancy_2020_11.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.company_name</code>.
     */
    public void setCompanyName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.company_name</code>.
     */
    public String getCompanyName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.city</code>.
     */
    public void setCity(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.city</code>.
     */
    public String getCity() {
        return (String) get(4);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.created</code>.
     */
    public void setCreated(Date value) {
        set(5, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.created</code>.
     */
    public Date getCreated() {
        return (Date) get(5);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.salary_from</code>.
     */
    public void setSalaryFrom(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.salary_from</code>.
     */
    public Integer getSalaryFrom() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.salary_to</code>.
     */
    public void setSalaryTo(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.salary_to</code>.
     */
    public Integer getSalaryTo() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.currency</code>.
     */
    public void setCurrency(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.currency</code>.
     */
    public String getCurrency() {
        return (String) get(8);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.experience_from</code>.
     */
    public void setExperienceFrom(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.experience_from</code>.
     */
    public Integer getExperienceFrom() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.experience_to</code>.
     */
    public void setExperienceTo(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.experience_to</code>.
     */
    public Integer getExperienceTo() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>parser_service.vacancy_2020_11.url</code>.
     */
    public void setUrl(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>parser_service.vacancy_2020_11.url</code>.
     */
    public String getUrl() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, String, String, String, String, Date, Integer, Integer, String, Integer, Integer, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Integer, String, String, String, String, Date, Integer, Integer, String, Integer, Integer, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Vacancy_2020_11.VACANCY_2020_11.ID;
    }

    @Override
    public Field<String> field2() {
        return Vacancy_2020_11.VACANCY_2020_11.NAME;
    }

    @Override
    public Field<String> field3() {
        return Vacancy_2020_11.VACANCY_2020_11.DESCRIPTION;
    }

    @Override
    public Field<String> field4() {
        return Vacancy_2020_11.VACANCY_2020_11.COMPANY_NAME;
    }

    @Override
    public Field<String> field5() {
        return Vacancy_2020_11.VACANCY_2020_11.CITY;
    }

    @Override
    public Field<Date> field6() {
        return Vacancy_2020_11.VACANCY_2020_11.CREATED;
    }

    @Override
    public Field<Integer> field7() {
        return Vacancy_2020_11.VACANCY_2020_11.SALARY_FROM;
    }

    @Override
    public Field<Integer> field8() {
        return Vacancy_2020_11.VACANCY_2020_11.SALARY_TO;
    }

    @Override
    public Field<String> field9() {
        return Vacancy_2020_11.VACANCY_2020_11.CURRENCY;
    }

    @Override
    public Field<Integer> field10() {
        return Vacancy_2020_11.VACANCY_2020_11.EXPERIENCE_FROM;
    }

    @Override
    public Field<Integer> field11() {
        return Vacancy_2020_11.VACANCY_2020_11.EXPERIENCE_TO;
    }

    @Override
    public Field<String> field12() {
        return Vacancy_2020_11.VACANCY_2020_11.URL;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public String component4() {
        return getCompanyName();
    }

    @Override
    public String component5() {
        return getCity();
    }

    @Override
    public Date component6() {
        return getCreated();
    }

    @Override
    public Integer component7() {
        return getSalaryFrom();
    }

    @Override
    public Integer component8() {
        return getSalaryTo();
    }

    @Override
    public String component9() {
        return getCurrency();
    }

    @Override
    public Integer component10() {
        return getExperienceFrom();
    }

    @Override
    public Integer component11() {
        return getExperienceTo();
    }

    @Override
    public String component12() {
        return getUrl();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public String value4() {
        return getCompanyName();
    }

    @Override
    public String value5() {
        return getCity();
    }

    @Override
    public Date value6() {
        return getCreated();
    }

    @Override
    public Integer value7() {
        return getSalaryFrom();
    }

    @Override
    public Integer value8() {
        return getSalaryTo();
    }

    @Override
    public String value9() {
        return getCurrency();
    }

    @Override
    public Integer value10() {
        return getExperienceFrom();
    }

    @Override
    public Integer value11() {
        return getExperienceTo();
    }

    @Override
    public String value12() {
        return getUrl();
    }

    @Override
    public Vacancy_2020_11Record value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value4(String value) {
        setCompanyName(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value5(String value) {
        setCity(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value6(Date value) {
        setCreated(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value7(Integer value) {
        setSalaryFrom(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value8(Integer value) {
        setSalaryTo(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value9(String value) {
        setCurrency(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value10(Integer value) {
        setExperienceFrom(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value11(Integer value) {
        setExperienceTo(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record value12(String value) {
        setUrl(value);
        return this;
    }

    @Override
    public Vacancy_2020_11Record values(Integer value1, String value2, String value3, String value4, String value5, Date value6, Integer value7, Integer value8, String value9, Integer value10, Integer value11, String value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached Vacancy_2020_11Record
     */
    public Vacancy_2020_11Record() {
        super(Vacancy_2020_11.VACANCY_2020_11);
    }

    /**
     * Create a detached, initialised Vacancy_2020_11Record
     */
    public Vacancy_2020_11Record(Integer id, String name, String description, String companyName, String city, Date created, Integer salaryFrom, Integer salaryTo, String currency, Integer experienceFrom, Integer experienceTo, String url) {
        super(Vacancy_2020_11.VACANCY_2020_11);

        set(0, id);
        set(1, name);
        set(2, description);
        set(3, companyName);
        set(4, city);
        set(5, created);
        set(6, salaryFrom);
        set(7, salaryTo);
        set(8, currency);
        set(9, experienceFrom);
        set(10, experienceTo);
        set(11, url);
    }
}
