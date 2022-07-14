package com.semp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(indexes = @Index(name = "slug_index", columnList = "slug",unique = true))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(length = 32, nullable = false)
    @JsonIgnore
    private String slug;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isLeaf;

    @JsonIgnore
    private Long parentId;

}
