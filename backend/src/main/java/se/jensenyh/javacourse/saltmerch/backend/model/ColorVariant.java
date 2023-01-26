package se.jensenyh.javacourse.saltmerch.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorVariant {

    @JsonProperty
    public String colorName;

    @JsonProperty
    public List<String> images;

    @JsonProperty
    public List<SizeContainer> sizes;


    public ColorVariant() {
        images = new ArrayList<>();
        sizes = new ArrayList<>();
    }


    public ColorVariant(String colorName, List<SizeContainer> sizes, List<String> images) {
        this.colorName = colorName;
        this.images = images;
        this.sizes = sizes;
    }


    public void setImagesFromCSV(String csv) throws Exception {
        images = new ArrayList<>(Arrays.asList(csv.split(",")));
    }

}
