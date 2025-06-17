package com.sparrows.user.cache.key;

@FunctionalInterface
public interface CacheKeyGenerator<K> {
    String generateKey(K key);
}