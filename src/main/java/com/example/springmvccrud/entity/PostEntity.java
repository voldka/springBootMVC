package com.example.springmvccrud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class PostEntity {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idposts", nullable = false)
    private Long id;

    @Column(name = "img", nullable = true, length = 255)
    private String img;

    @Transient
    public String getPhotosImagePath() {
        if (img == null || id == null) return null;
        return "/admin-posts/" + id + "/" + img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
