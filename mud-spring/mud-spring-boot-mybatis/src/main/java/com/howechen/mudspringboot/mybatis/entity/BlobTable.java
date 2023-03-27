package com.howechen.mudspringboot.mybatis.entity;

public class BlobTable {
    private Long id;

    private byte[] field;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getField() {
        return field;
    }

    public void setField(byte[] field) {
        this.field = field;
    }
}