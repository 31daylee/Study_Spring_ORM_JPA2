package jpql;

import javax.persistence.*;
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

            Member member = new Member();
            member.setUsername("member2");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            /*TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class); // member 클래스 전체
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // String 이란 확실한 타입이 존재하는 경우
            Query query3 = em.createQuery("select m.username, m.age from Member m"); // 타입이 다중인 경우 -> 반환타입이 불명확

            List<Member> resultList = query.getResultList(); // 리턴되는 값이 여러 개일때
            for(Member m : resultList){
                System.out.println("member = "+ m);
            }*/

            em.flush();
            em.clear();
            // session 10 프로젝션
           /* List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username,m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO = "+memberDTO.getUsername());
            System.out.println("memberDTO = "+memberDTO.getAge());*/

            // session 10 페이징
            /*List<Member> result = em.createQuery("select m from Member m order by m.age desc ", Member.class)
                        .setFirstResult(0)
                        .setMaxResults(10)
                        .getResultList();

            for(Member member1 : result){
                System.out.println("member1 = "+member1);
            }*/
            String query = "select m from Member m inner join m.team t where t.name = :teamName";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();


            tx.commit(); // [트랜잭션] 커밋
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();




    }


}
