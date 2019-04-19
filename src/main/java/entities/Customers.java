package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "personal_number")
    private String personalNumber;
    @Column(name = "street_address")
    private String streetAddress;

    public Customers() {
    }

    public Customers(String name, String personalNumber, String streetAddress) {
        this.name = name;
        this.personalNumber = personalNumber;
        this.streetAddress = streetAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }
}
