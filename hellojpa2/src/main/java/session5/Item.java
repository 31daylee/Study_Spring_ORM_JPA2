package session5;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속과 관련
@DiscriminatorColumn // 상속과 관련된 엔티티의 이름이 DTYPE = "" 으로 저장된다 -> Joined 에서 사용된다
public abstract class Item { // 추상클래스로 만들시, 상속받는 클래스만 테이블이 생성되고 Item 테이블은 생성 X

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
