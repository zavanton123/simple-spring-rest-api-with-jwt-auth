package com.evolunta.api.auth.config

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator

@Configuration
class DataConfig(
    private val dataSource: DataSource
) {

    @Bean
    fun dataSourceInitializer(
        @Qualifier("dataSource") dataSource: DataSource
    ): DataSourceInitializer {
        println("zavanton - init data")
        val resourceDatabasePopulator = ResourceDatabasePopulator()
        resourceDatabasePopulator.addScript(ClassPathResource("/data.sql"))
        val dataSourceInitializer = DataSourceInitializer()
        dataSourceInitializer.setDataSource(dataSource)
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator)
        return dataSourceInitializer
    }
}