package com.smartdev.ufoss.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ROLE")
public class RoleEntity extends AbstractEntity implements GrantedAuthority {

    @Column(unique = true, name = "role")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role"),
            inverseJoinColumns = @JoinColumn(name = "permission"))
    private Set<PermissionEntity> permissions = new HashSet<PermissionEntity>();

    @JsonIgnore
    @JsonBackReference
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REMOVE)
    private Set<UserEntity> users;

    public RoleEntity(String name, Set<PermissionEntity> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    public void addPermission(PermissionEntity permissionEntity) {
        this.permissions.add(permissionEntity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return permissions.stream().map(PermissionEntity::getName)
                .collect(Collectors.joining(","));
    }

    public String getRole() {
        return "ROLE_" + name;
    }
}
