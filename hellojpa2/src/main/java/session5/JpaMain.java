package session5;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        // 엔티티 매니저는 데이터 변경시 트랜잭션 시작
        tx.begin(); // [트랜잭션] 시작

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            // JPQL 사용 시에 Member 가져오지만 Member에 딸린 Team이 즉시 로딩이기에 한 번 더 쿼리가 날라감
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();


            tx.commit(); // [트랜잭션] 커밋
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();




    }

    private static void printMember(Member member) {
        System.out.println("member : "+ member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username : "+username);
        Team team = member.getTeam();
        System.out.println("Team : "+ team.getName());
    }
}
