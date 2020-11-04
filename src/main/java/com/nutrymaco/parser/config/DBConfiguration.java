package com.nutrymaco.parser.config;

import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.impl.DSL;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {
    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("89.223.94.132");
        ds.setDatabaseName("postgres");
        ds.setCurrentSchema("parser_service");
        ds.setUser("nutrymaco");
        ds.setPassword("Wtkv7GERgerghrt");
        return ds;
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource())
                .baselineOnMigrate(true)
                .locations("classpath:db.migration")
                .schemas("persistence_example").load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    public DSLContext dslContext() {
        DSLContext dslContext = DSL.using(dataSource(), SQLDialect.POSTGRES);
        dslContext.settings().setRenderNameStyle(RenderNameStyle.LOWER);
        return dslContext;
    }
}
