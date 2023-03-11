package com.company.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "publisher")
public class Publisher implements Serializable {

    @Id
    @Column(name = "publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String postalCode;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @OneToMany(mappedBy = "publisherId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Book> books;


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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return getId() == publisher.getId()
                && Objects.equals(getName(), publisher.getName())
                && Objects.equals(getStreet(), publisher.getStreet())
                && Objects.equals(getCity(), publisher.getCity())
                && Objects.equals(getState(), publisher.getState())
                && Objects.equals(getPostalCode(), publisher.getPostalCode())
                && Objects.equals(getPhone(), publisher.getPhone())
                && Objects.equals(getEmail(), publisher.getEmail())
                && Objects.equals(getBooks(), publisher.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getStreet(),
                getCity(),
                getState(),
                getPostalCode(),
                getPhone(),
                getEmail(),
                getBooks());
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", books=" + books +
                '}';
    }
}
