package com.web.rpg.service.worldServices;

public enum EventDetails {
    SOME_EVENT,
    TEST_EVENT;


    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}
