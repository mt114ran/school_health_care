package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "messages")
@NamedQueries({
    @NamedQuery(
        name = "getAllMessages",
        query = "SELECT m FROM Message AS m ORDER BY m.id DESC"
    ),
    @NamedQuery(
        name = "getMessagesCount",
        query = "SELECT COUNT(m) FROM Message AS m"
    ),
    @NamedQuery(
        name = "getMyAllMessages",
        query = "SELECT m FROM Message AS m WHERE m.user = :user ORDER BY m.id DESC"
    ),
    @NamedQuery(
        name = "getMyMessagesCount",
        query = "SELECT COUNT(m) FROM Message AS m WHERE m.user = :user"
    ),
    @NamedQuery(
        name = "getMyGroupMessages",
        query = "SELECT m FROM Message AS m WHERE m.group.id in (SELECT gm.group.id FROM GroupMember As gm WHERE gm.user = :user) "
    ),
    @NamedQuery(
        name = "getMyGroupMessagesCount",
        query = "SELECT COUNT(m) FROM Message AS m WHERE m.group.id in (SELECT gm.group.id FROM GroupMember As gm WHERE gm.user = :user) "
    )
})
@Entity
public class Message{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "message_date", nullable = false)
    private Date message_date;

    @Lob
    @Column(name = "message", nullable = true)
    private String message;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getMessage_date() {
        return message_date;
    }

    public void setMessage_date(Date message_date) {
        this.message_date = message_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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