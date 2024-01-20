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
            Address address = new Address("city","street","100");
            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello2");
            member2.setHomeAddress(address);
            em.persist(member2);

            // 임베디드 타입으로 공유하기에 member1 을 업데이트 했지만 mem1/2 전부 netCity로 업데이트 된다.
            member1.getHomeAddress().setCity("newCity");

            tx.commit(); // [트랜잭션] 커밋
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();




    }


}
