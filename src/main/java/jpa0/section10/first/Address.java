package jpa0.section10.first;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    private String city;
    private String street;
    private int zipCode;

    public Address() {
    }

    public Address(String city, String street, int zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
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

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode && Objects.equals(city, address.city) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipCode);
    }
}
