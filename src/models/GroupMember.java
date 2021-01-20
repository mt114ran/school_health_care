package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "group_members")
@NamedQueries({
    @NamedQuery(
        name = "getGroupMemberRelation",
        query = "SELECT gm FROM GroupMember AS gm Where gm.user = :user and gm.group = :group"
    ),
@NamedQuery(
        name = "getGroupMemberRelationCount",
        query = "SELECT COUNT(gm) FROM GroupMember AS gm Where gm.user = :user and gm.group = :group"
    ),
@NamedQuery(
        name = "getGroupMemberRelationCountByUserId",
        query = "SELECT COUNT(gm) FROM GroupMember AS gm Where gm.user.id = :user_id and gm.group = :group"
    ),
})
@Entity
public class GroupMember{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getMember() {
        return user;
    }

    public void setMember(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}