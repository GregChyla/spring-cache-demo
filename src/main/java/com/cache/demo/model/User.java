package com.cache.demo.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class User {
    UUID id;
    String name;
    int age;
}
