package session5;

import org.hibernate.metamodel.internal.MapMember;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
            member1.setHomeAddress(new Address("homeCity", "street","1021"));

            member1.getFavoriteFoods().add("치킨");
            member1.getFavoriteFoods().add("족발");
            member1.getFavoriteFoods().add("피자");

            member1.getAddressHistory().add(new AddressEntity("old1", "street","10"));
            member1.getAddressHistory().add(new AddressEntity("old2", "street","101"));

            em.persist(member1);

            em.flush();
            em.clear();

            System.out.println("=======START========");
            Member findMember = em.find(Member.class, member1.getId());

/*            List<Address> addressHistory = findMember.getAddressHistory();
            for(Address address : addressHistory){
                System.out.println("address : "+address.getCity());
                System.out.println("address : "+address.getStreet());
                System.out.println("address : "+address.getZipcode());
            }
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String food : favoriteFoods){
                System.out.println("food : " +food);
            }*/
            // homeCity -> newCity 값변경
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(),a.getZipcode()));

            // 컬렉션타입 값 변경
            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");
            
            // 실행 결과, 한 번의 delete과 두 번의 insert query가 날라간 것을 확인 -> Entity로 변경하여 일대다 관계에서 적용으로 방식 전환
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street","10"));
            findMember.getAddressHistory().add(new AddressEntity("new1", "street","10"));

            tx.commit(); // [트랜잭션] 커밋
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();




    }


}
