package com.spring.reddis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.spring.reddis.service.RedisService;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public void save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return redisService.get(key);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String key) {
        redisService.delete(key);
    }

}
