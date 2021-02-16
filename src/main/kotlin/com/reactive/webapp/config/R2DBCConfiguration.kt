package com.reactive.webapp.config

import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.ReactiveTransactionManager

@Configuration
@EnableR2dbcRepositories
internal class R2DBCConfiguration : AbstractR2dbcConfiguration() {

    fun connectionFactory() {
        val url = "mysql://orders:orders@127.0.0.1:3306/orders"
        return MySQLConnectionFactory(
            MySQLConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .username("root")
                .password("123456")
                .database("database_name")
                .build()
        )

    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory?): ReactiveTransactionManager? {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    fun initializer(connectionFactory: ConnectionFactory?): ConnectionFactoryInitializer? {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val populator = CompositeDatabasePopulator()
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("data.sql")))
        initializer.setDatabasePopulator(populator)
        return initializer
    }

}
