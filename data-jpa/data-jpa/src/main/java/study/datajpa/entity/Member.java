package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "age", "username"})
public class Member extends JpaBaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private int age;
    private String username;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_name")
    private Team team;

    public Member(String username, int age, Team team) {
        this.age = age;
        this.username = username;
        if (team != null){
            this.team = team;
        }
    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
