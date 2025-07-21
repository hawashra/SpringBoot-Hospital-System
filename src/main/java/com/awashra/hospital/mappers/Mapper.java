package com.awashra.hospital.mappers;

public interface Mapper <T, U> {
    U map(T t);
    T reverseMap(U u);
}