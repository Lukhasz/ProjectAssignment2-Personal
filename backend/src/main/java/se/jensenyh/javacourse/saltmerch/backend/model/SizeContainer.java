package se.jensenyh.javacourse.saltmerch.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SizeContainer {

    @JsonProperty
    public String size;

    @JsonProperty
    public int stock;


    public SizeContainer() {
    }


    public SizeContainer(String size, int stock) {
        this.size = size;
        this.stock = stock;
    }

}
