package com.weather.config;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;

@ConfigurationProperties(prefix = "spring.redisson")
@Data
public class RedissonConfig {
    private String address;
    private String clientName = null;
    private int connectionMinimumIdleSize = 10;
    private int connectionPoolSize = 64;
    private int connectTimeout = 10000;
    private int database = 0;
    private int dnsMonitoringInterval = 5000;
    private int idleConnectionTimeout = 10000;
    private boolean keepAlive = false;
    private String password = null;
    private int pingConnectionInterval = 0;
    private int retryAttempts = 3;
    private int retryInterval = 1500;
    private int subscriptionConnectionMinimumIdleSize = 1;
    private int subscriptionConnectionPoolSize = 50;
    private int subscriptionsPerConnection = 5;
    private int timeout = 3000;

    private int thread = 0;
    private String codec = "org.redisson.codec.JsonJacksonCodec";

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setClientName(clientName)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectTimeout(connectTimeout)
                .setDatabase(database)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setKeepAlive(keepAlive)
                .setPassword(password)
                .setPingConnectionInterval(pingConnectionInterval)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setTimeout(timeout);

        config.setCodec((Codec) ClassUtils.forName(codec, ClassUtils.getDefaultClassLoader()).newInstance())
                .setThreads(thread)
                .setEventLoopGroup(new NioEventLoopGroup());

        return Redisson.create(config);
    }
}