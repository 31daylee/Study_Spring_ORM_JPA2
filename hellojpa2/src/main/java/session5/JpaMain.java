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

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);


            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1 = "+ m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = "+ reference.getClass());

            System.out.println("a == a : "+(m1 == reference)); // 한 영속성 컨텍스트 안에서 가져오기에 항상 true를 반환


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
