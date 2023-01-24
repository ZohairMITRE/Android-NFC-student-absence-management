package org.mbds.nfctag.model;

public enum TagType {
    TEXT("Text"),
    URL("URL"),
    PHONE("Phone");

    private String type;

    TagType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
