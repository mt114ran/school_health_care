package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "groups")
@NamedQueries({
    @NamedQuery(
        name = "getAllGroups",
        query = "SELECT g FROM Group AS g ORDER BY g.id DESC"
    ),
    @NamedQuery(
        name = "getAllGroupsCount",
        query = "SELECT COUNT(g) FROM Group AS g"
    ),
    @NamedQuery(
            name = "getNameGroupsCount",
            query = "SELECT COUNT(g) FROM Group AS g WHERE g.group_name = :group_name"
        ),
    @NamedQuery(
            name = "getGroupByGroupId",
            query = "SELECT g FROM Group AS g WHERE g.id = :group_id"
        ),
    @NamedQuery(
            name = "getGroupByGroupIdCount",
            query = "SELECT COUNT(g) FROM Group AS g WHERE g.id = :group_id"
        ),
    @NamedQuery(
            name = "getMyGroups",
            query = "SELECT g FROM Group AS g WHERE g.id in (SELECT gm.group.id FROM GroupMember As gm WHERE gm.user = :login_user )"
        ),
    @NamedQuery(
            name = "getMyGroupsCount",
            query = "SELECT COUNT(g) FROM Group AS g WHERE g.id in (SELECT gm.group.id FROM GroupMember As gm WHERE gm.user = :login_user )"
        )
})
@Entity
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "group_name", nullable = false, unique = true)
    private String group_name;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

}