package com.petdb.filehandler;

public enum Extension {
    JSON("json"), XML("xml");

    private final String value;

    Extension(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
