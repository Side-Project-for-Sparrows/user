package com.sparrows.user.cache.factory;

import com.sparrows.user.cache.key.CacheKeyGenerator;
import com.sparrows.user.cache.layer.CacheLayer;
import com.sparrows.user.cache.layer.InMemoryCacheLayer;
import com.sparrows.user.cache.layer.MultiLevelCacheManager;
import com.sparrows.user.cache.layer.RedisCacheLayer;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class CacheManagerFactory {

    public static <K, V> MultiLevelCacheManager<K, V> create(
            Duration inMemoryTtl,
            Duration redisTtl,
            RedisTemplate<String, String> redisTemplate,
            CacheKeyGenerator<K> keyGenerator,
            Function<V, String> serializer,
            Function<String, V> deserializer

    ) {
        CacheLayer<K, V> inMemoryLayer = new InMemoryCacheLayer<>(inMemoryTtl, keyGenerator);
        CacheLayer<K, V> redisLayer = new RedisCacheLayer<>(redisTemplate, redisTtl, keyGenerator, serializer, deserializer);

        return new MultiLevelCacheManager<>(List.of(inMemoryLayer, redisLayer));
    }
}
