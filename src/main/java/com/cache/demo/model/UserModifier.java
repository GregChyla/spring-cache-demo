package com.cache.demo.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserModifier {
    String name;
    int age;
}
