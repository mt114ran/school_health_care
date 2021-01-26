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

@Table(name = "users")
@NamedQueries({
    @NamedQuery(
        name = "getAllUsers",
        query = "SELECT u FROM User AS u"
    ),
    @NamedQuery(
        name = "getUsersCount",
        query = "SELECT COUNT(u) FROM User AS u"
    ),
    @NamedQuery(
        name = "getUserByUserCode",
        query = "SELECT u FROM User AS u Where u.code = :user_code"
    ),
    @NamedQuery(
        name = "getUserByUserCodeCount",
        query = "SELECT COUNT(u) FROM User AS u Where u.code = :user_code"
    ),
    @NamedQuery(
        name = "checkRegisteredCode",
        query = "SELECT COUNT(u) FROM User AS u WHERE u.code = :code"
    ),
    @NamedQuery(
        name = "checkLoginCodeAndPassword",
        query = "SELECT u FROM User AS u WHERE u.delete_flag = 0 AND u.code = :code AND u.password = :pass"
    ),
    @NamedQuery(
        name = "getGroupMembers",
        query = "SELECT u FROM User AS u WHERE u.id in (SELECT gm.user.id FROM GroupMember As gm WHERE gm.group.id = :group_id)"
    ),
    @NamedQuery(
        name = "getGroupMembersCount",
        query = "SELECT COUNT(u) FROM User AS u WHERE u.id in (SELECT gm.user.id FROM GroupMember As gm WHERE gm.group.id = :group_id)"
    ),
    @NamedQuery(
        name = "getStudent",
        query = "SELECT u FROM User AS u Where u.acc_inf = 1"
    ),
    @NamedQuery(
        name = "getStudentCount",
        query = "SELECT COUNT(u) FROM User AS u Where u.acc_inf = 1"
    ),
    @NamedQuery(
        name = "getParent",
        query = "SELECT u FROM User AS u Where u.acc_inf = 2"
    ),
    @NamedQuery(
        name = "getParentCount",
        query = "SELECT COUNT(u) FROM User AS u Where u.acc_inf = 2"
    ),
    @NamedQuery(
        name = "getMyFamilyUsers",
        query = "SELECT u FROM User AS u Where u.id in (SELECT f.student.id FROM Family As f WHERE f.parent = :login_user)"
    ),
    @NamedQuery(
        name = "getMyFamilyUsersCount",
        query = "SELECT COUNT(u) FROM User AS u Where u.id in (SELECT f.student.id FROM Family As f WHERE f.parent = :login_user)"
    ),
})
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "acc_inf", nullable = false)
    private Integer acc_inf;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAcc_inf() {
        return acc_inf;
    }

    public void setAcc_inf(Integer acc_inf) {
        this.acc_inf = acc_inf;
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

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}