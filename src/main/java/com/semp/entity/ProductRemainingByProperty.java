package com.semp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semp.serializer.ProductRemainingByPropertySerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@JsonSerialize(using = ProductRemainingByPropertySerializer.class)
public class ProductRemainingByProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_property_for_remaining_id")
    @JsonIgnore
    private ProductPropertyForRemaining lastProperty;

    @Column(nullable = false)
    private Float remaining;

    @Column(nullable = false)
    private Float price;

    @OneToMany (mappedBy = "productRemainingByProperty")
    private List<PhotoProduct> photo;
}
