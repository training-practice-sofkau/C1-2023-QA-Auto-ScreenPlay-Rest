package com.sofkau.models;

import netscape.javascript.JSObject;

import java.util.List;

public class ResponsePokeApi {
    private String id;
    private String name;
    private List<JSObject> names;

    private List<JSObject> version_group;

    public List<JSObject> getNames() {
        return names;
    }

    public void setNames(List<JSObject> names) {
        this.names = names;
    }

    public List<JSObject> getVersion_group() {
        return version_group;
    }

    public void setVersion_group(List<JSObject> version_group) {
        this.version_group = version_group;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
