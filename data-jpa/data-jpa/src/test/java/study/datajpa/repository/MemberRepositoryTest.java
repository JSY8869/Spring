package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Optional<Member> findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember.get().getId()).isEqualTo(member.getId());
        assertThat(findMember.get().getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember.get()).isEqualTo(member);
    }

    @Test
    void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void testQuery() {
        Team team = new Team("team");
        teamRepository.save(team);

        Member m1 = new Member("AAA",10, team);
        Member m2 = new Member("AAB", 20, team);


        memberRepository.save(m1);
        memberRepository.save(m2);


        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println(dto);
        }
    }

    @Test
    public void findNames() {
        Team team = new Team("team");
        teamRepository.save(team);

        Member m1 = new Member("AAA",10, team);
        Member m2 = new Member("AAB", 20, team);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> names = memberRepository.findByNames(Arrays.asList("AAA", "AAB"));
        for (Member name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void returnType(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("AAB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> listByUsername = memberRepository.findListByUsername("AAA");
        Member memberByUsername = memberRepository.findMemberByUsername("AAA");
        Optional<Member> optionalByUsername = memberRepository.findOptionalByUsername("AAA");
        System.out.println(listByUsername.get(0).getUsername());
        System.out.println(memberByUsername.getUsername());
        System.out.println(optionalByUsername.get().getUsername());
    }

    @Test
    public void paging(){
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAB", 10));
        memberRepository.save(new Member("AAC", 10));
        memberRepository.save(new Member("AAD", 10));
        memberRepository.save(new Member("AAE", 10));
        memberRepository.save(new Member("AAF", 10));

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(10, pageRequest);
        Page<MemberDto> memberDtos = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        List<Member> content = page.getContent();

        assertThat(content.size()).isEqualTo(3);
        assertThat(memberDtos.getTotalElements()).isEqualTo(6);
        assertThat(memberDtos.getNumber()).isEqualTo(0);
        assertThat(memberDtos.getTotalPages()).isEqualTo(2);
        assertThat(memberDtos.isFirst()).isTrue();
        assertThat(memberDtos.hasNext()).isTrue();

    }
}
