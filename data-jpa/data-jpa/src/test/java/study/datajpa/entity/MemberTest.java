package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("memberA",10,teamA);
        Member member2 = new Member("memberB",20,teamB);
        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member member : memberList) {
            System.out.println(member.getUsername());
            System.out.println(member.getTeam().getName());
        }
    }

}