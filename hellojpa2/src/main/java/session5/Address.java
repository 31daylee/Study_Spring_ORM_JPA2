package session5;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    // addreaa
    private String city;
    private String street;
    private String zipcode;

    public Address(){

    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    
    // 임베디드 타입(공유해서 사용하는)의 setter를 모두 private로 변경해주거나 setter를 삭제해주므로써 
    // 값이 변경되는 상황을 방지
    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
