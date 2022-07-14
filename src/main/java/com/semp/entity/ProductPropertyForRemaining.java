package com.semp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semp.serializer.ProductPropertyForRemainingSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@JsonSerialize(using = ProductPropertyForRemainingSerializer.class)
public class ProductPropertyForRemaining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_value_id")
    private PropertyValue propertyValue;

    @JsonIgnore
    private Long parentId;

    private boolean displayAsSeparateProduct;
}
