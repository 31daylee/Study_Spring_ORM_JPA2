package session5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            //Member findmember = em.find(Member.class, member.getId());
            Member findmember = em.getReference(Member.class, member.getId());
            System.out.println("findMember :"+ findmember.getClass());
            System.out.println("findMember.id : "+ findmember.getId());
            System.out.println("findMember.userName : "+ findmember.getUsername());

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
