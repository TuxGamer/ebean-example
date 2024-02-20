package org.example.ebean.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "tenants")
public class TenantEntity {

    @Id
    private final String id;

    @ManyToMany(mappedBy = "tenants")
    private final List<UserEntity> users;

}
