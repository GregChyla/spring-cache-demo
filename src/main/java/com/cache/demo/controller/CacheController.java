package com.cache.demo.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/cache"})
public class CacheController {

    @CacheEvict(cacheNames = {"users"}, allEntries = true)
    @GetMapping("/clear")
    public void clearCache() {
    }
}
