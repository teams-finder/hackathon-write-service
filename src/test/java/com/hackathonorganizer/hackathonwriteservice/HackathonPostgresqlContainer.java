package com.hackathonorganizer.hackathonwriteservice;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class HackathonPostgresqlContainer extends PostgreSQLContainer<HackathonPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:14.4";

    @Container
    public static HackathonPostgresqlContainer container;


    private HackathonPostgresqlContainer() {

        super(IMAGE_VERSION);
    }

    public static HackathonPostgresqlContainer getInstance() {

        if (container == null) {

            container = new HackathonPostgresqlContainer();
        }
        return container;
    }

    @DynamicPropertySource
    public static void overrideProperties(final DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }
}
