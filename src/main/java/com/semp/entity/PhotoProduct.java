package com.semp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class PhotoProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;


    @Column(nullable = false)
    private String path;

    private boolean isMainPhoto;

    @ManyToOne
    @JoinColumn(name = "product_remaining_by_property_id")
    @JsonIgnore
    private ProductRemainingByProperty productRemainingByProperty;
}
