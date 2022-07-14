package com.semp.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semp.entity.ProductPropertyForRemaining;
import com.semp.entity.ProductRemainingByProperty;
import com.semp.serializer.RemainingSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@JsonSerialize(using = RemainingSerializer.class)
public class Remaining {
    private ProductRemainingByProperty remaining;
    private List<ProductPropertyForRemaining> property;
}
