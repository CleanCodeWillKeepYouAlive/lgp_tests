package com.leadgen.tests.dto;

public enum Method {

    GET,
    POST,
    PUT,
    DELETE
    ;

    public boolean isGet() { return this.equals(Method.GET); }

    public boolean isPost() { return this.equals(Method.POST); }
}
