package de.uulm.llam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Role.
 */
@Entity
@Table(name = "T_ROLE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "can_create")
    private Boolean canCreate;

    @Column(name = "can_discuss")
    private Boolean canDiscuss;

    @Column(name = "can_rate")
    private Boolean canRate;

    @Column(name = "can_delete")
    private Boolean canDelete;

    @Column(name = "can_grant_access")
    private Boolean canGrantAccess;

    @Column(name = "can_edit")
    private Boolean canEdit;

    @Column(name = "can_administrate")
    private Boolean canAdministrate;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanDiscuss() {
        return canDiscuss;
    }

    public void setCanDiscuss(Boolean canDiscuss) {
        this.canDiscuss = canDiscuss;
    }

    public Boolean getCanRate() {
        return canRate;
    }

    public void setCanRate(Boolean canRate) {
        this.canRate = canRate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean getCanGrantAccess() {
        return canGrantAccess;
    }

    public void setCanGrantAccess(Boolean canGrantAccess) {
        this.canGrantAccess = canGrantAccess;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getCanAdministrate() {
        return canAdministrate;
    }

    public void setCanAdministrate(Boolean canAdministrate) {
        this.canAdministrate = canAdministrate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", canCreate='" + canCreate + "'" +
                ", canDiscuss='" + canDiscuss + "'" +
                ", canRate='" + canRate + "'" +
                ", canDelete='" + canDelete + "'" +
                ", canGrantAccess='" + canGrantAccess + "'" +
                ", canEdit='" + canEdit + "'" +
                ", canAdministrate='" + canAdministrate + "'" +
                '}';
    }
}
