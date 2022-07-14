package com.semp.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semp.entity.Property;
import com.semp.serializer.FilterSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@JsonSerialize(using = FilterSerializer.class)
public class Filter {
    private String slugCategory;
    private List<Property> listProperty;
}
