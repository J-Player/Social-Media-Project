package com.example.demo.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Mapper {

    public static final ModelMapper INSTANCE = new ModelMapper();

}
