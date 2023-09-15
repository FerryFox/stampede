package com.fox.cradle.features.stamp.model;

import com.fox.cradle.features.appuser.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class StampCardTemplate
{
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private String image;

    private String createdBy;

    private Instant createdDate = Instant.now();

    @Enumerated(EnumType.STRING)
    private StampCardCategory stampCardCategory;

    @Enumerated(EnumType.STRING)
    private StampCardSecurity stampCardSecurity;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser appUser;
}