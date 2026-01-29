package com.copmap.tracking;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    private final RedisTemplate<String, Object> redisTemplate;

    public TrackingService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveLocation(OfficerLocation location) {
        redisTemplate.opsForValue()
                .set("OFFICER_LOC_" + location.getOfficerId(), location);
    }
}
