package session5;

import javax.persistence.*;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false,updatable = false)
    private Team team;
    // 단순히 JoinColumn만 사용하면 Member도 양방향의 주인이 되지만 insertable ~ 사용함으로써 단순한 읽기전용필드로 된다.

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}