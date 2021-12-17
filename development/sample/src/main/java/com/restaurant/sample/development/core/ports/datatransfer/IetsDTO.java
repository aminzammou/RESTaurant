package com.restaurant.sample.development.core.ports.datatransfer;

public class IetsDTO {
    final private long id;
    final private String name;
    final private int number;

    public IetsDTO(long id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
