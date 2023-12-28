package session5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
            team.getMembers().add(member);  //역방향(주인이 아닌 방향) 만 연관 관계설정
            em.persist(team);               // -> team 테이블이 아닌 member 테이블로 가서 update

            //em.flush(); // flush 로 영속성 컨텍스트 안에 있는것을 날려버린다
            //em.clear(); // 영속성 컨텍스트를 초기화 한다 -> DB에서 정보를 불러 올 수 있다.

            tx.commit(); // [트랜잭션] 커밋
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();




    }
}
