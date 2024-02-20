package org.example.ebean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.Database;
import io.ebean.config.UnderscoreNamingConvention;
import org.example.ebean.entities.TenantEntity;
import org.example.ebean.entities.UserEntity;

import javax.sql.DataSource;

public class Example {

    public static void main(String[] args) {

        DataSource ds = buildDataSource();
        Database ebean = Database.builder().dataSource(ds).namingConvention(new UnderscoreNamingConvention()).build();

        TenantEntity tenant = ebean.find(TenantEntity.class).where().eq("id", "example").findOne();
        System.out.println("Found tenant: " + tenant.getId());

        for (UserEntity user : tenant.getUsers()) {
            System.out.println("Found user: " + user.getId());
        }

    }

    private static DataSource buildDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/ebeantest");
        hikariConfig.setUsername("ebeantest");
        hikariConfig.setPassword(System.getenv("POSTGRES_PASSWORD"));
        hikariConfig.setMaximumPoolSize(1);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        hikariConfig.setAutoCommit(false);

        return new HikariDataSource(hikariConfig);
    }

}
