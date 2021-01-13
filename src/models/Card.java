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

@Table(name = "cards")
@NamedQueries({
    @NamedQuery(
        name = "getAllCards",
        query = "SELECT c FROM Card AS c ORDER BY c.id DESC"
    ),
    @NamedQuery(
        name = "getCardsCount",
        query = "SELECT COUNT(c) FROM Card AS c"
    ),
})
@Entity
public class Card{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "card_date", nullable = false)
    private Date card_date;

    @Column(name = "temperature", nullable = false)
    private Integer temperature;

    @Column(name = "attendance", nullable = false)
    private Integer attendance;

    @Lob
    @Column(name = "comment", nullable = true)
    private String comment;

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

    public Date getCard_date() {
        return card_date;
    }

    public void setCard_date(Date card_date) {
        this.card_date = card_date;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTempareture(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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