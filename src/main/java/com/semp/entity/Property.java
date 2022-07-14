package com.semp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(indexes = @Index(name = "properties_slug_index", columnList = "slug",unique = true))
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
    @JsonIgnore
    private String slug;

    @ManyToOne
    @JoinColumn(name = "group_properties_id")
    @JsonIgnore
    private GroupProperties groupProperties;

    private Boolean isFilter;

    @ManyToOne
    @JoinColumn(name = "type_filter_id")
    private TypeFilter typeFilter;


    @OneToMany(mappedBy = "property")
    @JsonIgnore
    private Set<PropertyValue> propertyValueSet;

}
