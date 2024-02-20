package org.example.ebean.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@AllArgsConstructor
public class UserEntity {

    private final String id;

    @ManyToMany
    @JoinTable(
            name = "users_tenants",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tenant_id")
    )
    private final List<TenantEntity> tenants;

}
