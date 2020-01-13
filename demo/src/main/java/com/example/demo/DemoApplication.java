package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.connectionfactory.lookup.AbstractRoutingConnectionFactory;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryMetadata;
import io.r2dbc.spi.ConnectionFactoryOptions;
import reactor.core.publisher.Mono;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.DialectResolver;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.dialect.SqlServerDialect;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.WebFilter;
import org.springframework.data.r2dbc.dialect.DialectResolver.R2dbcDialectProvider;

@SpringBootApplication
public class DemoApplication {
	
	public static final String DATABASE_CONFIGURATION_KEY = "DATABASE_CONFIGURATION_KEY";
	
	@Autowired private TenantConfigurationResolver tenantConfigurationResolver;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean 
	public WebFilter tenantResolverWebfilter() {
		return (serverWebExchange,chain) -> {
				ServerHttpRequest request = serverWebExchange.getRequest();
				String tenantKey = request.getHeaders().getFirst("Tenant");
				Mono<DBConfiguration> dbConfiguration = tenantConfigurationResolver
						.resolveTenantConfiguration(tenantKey);
				return dbConfiguration.flatMap(dbConfig -> chain.filter(serverWebExchange)
					.subscriberContext(context -> context.put(DATABASE_CONFIGURATION_KEY, dbConfig)));
						
			};
	}

	class MyRoutingConnectionFactory extends AbstractRoutingConnectionFactory {
		
		
		private ConnectionFactory getNewConnectionFactory(DBConfiguration dbConfiguration) {
			System.out.println("yup");
			ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
				    .option(ConnectionFactoryOptions.DRIVER, "sqlserver")
				    .option(ConnectionFactoryOptions.HOST, dbConfiguration.getHost())
				    .option(ConnectionFactoryOptions.PORT,dbConfiguration.getPort())  // optional, defaults to 1433
				    .option(ConnectionFactoryOptions.USER, dbConfiguration.getUserName())
				    .option(ConnectionFactoryOptions.PASSWORD, dbConfiguration.getPassword())
				    .option(ConnectionFactoryOptions.DATABASE, dbConfiguration.getDbName()) // optional
				    .option(ConnectionFactoryOptions.SSL, dbConfiguration.isSsl()) // optional, defaults to false
				    .build();

				ConnectionFactory mssqlconnectionFactory = ConnectionFactories.get(options);
				return mssqlconnectionFactory;
		}


		@Override
		protected Mono<Object> determineCurrentLookupKey() {
			return Mono.subscriberContext()
					.map(it -> it.get(DATABASE_CONFIGURATION_KEY));
		}
		
		@Override
		protected Mono<ConnectionFactory> determineTargetConnectionFactory(){
			Mono<DBConfiguration> lookupKey = this.determineCurrentLookupKey()
					.cast(DBConfiguration.class);
			return lookupKey.map(this::getNewConnectionFactory);
		}
		
		@Override
		public ConnectionFactoryMetadata getMetadata() {
			return new ConnectionFactoryMetadata() {
				
				@Override
				public String getName() {
					return "Microsoft SQL Server";
				}
			};
		}
	}
	
	
	@Bean
	public AbstractRoutingConnectionFactory routingConnectionFactory() {
			MyRoutingConnectionFactory router = new MyRoutingConnectionFactory();
			router.setTargetConnectionFactories(new HashMap<>());
			return router;
	}
	
	

}
