package se.jensenyh.javacourse.saltmerch.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    @JsonProperty
    public int id;

    @JsonProperty
    public String category;

    @JsonProperty
    public String title;

    @JsonProperty
    public String description;

    @JsonProperty
    public String previewImage;

    @JsonProperty
    public List<ColorVariant> colorVariants;


    public Product() {
        colorVariants = new ArrayList<>();
    }


    public Product(int id, String category, String title, String description, String previewImage) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.previewImage = previewImage;
        colorVariants = new ArrayList<>();
    }


    public Product(int id, String category, String title, String description, List<ColorVariant> colorVariants) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.colorVariants = colorVariants;
    }
}
