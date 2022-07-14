package com.semp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(indexes = @Index(name = "properties_value_slug_index", columnList = "slug",unique = true))
public class PropertyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String value;

    @Column(length = 32, nullable = false)
    @JsonIgnore
    private String slug;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;


    @ManyToMany(mappedBy = "propertyValue")
    @JsonIgnore
    private List<Product> productList = new ArrayList<>();


}
