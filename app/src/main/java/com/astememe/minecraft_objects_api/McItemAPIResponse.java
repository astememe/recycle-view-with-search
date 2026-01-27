package com.astememe.minecraft_objects_api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class McItemAPIResponse {
        @SerializedName("name")
        public String mcItemNombre;

        @SerializedName("description")
        public String mcItemDescripcion;

        @SerializedName("image")
        public String mcItemImageURL;

}
