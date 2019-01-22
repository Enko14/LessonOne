package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String name;
  private String surname;
  private String nickname;
  private String company;
  private String city;
  private String email;
  private String group;



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData contactData = (ContactData) o;
    return id == contactData.id &&
            Objects.equals(name, contactData.name) &&
            Objects.equals(surname, contactData.surname) &&
            Objects.equals(city, contactData.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, city);
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withCity(String city) {
    this.city = city;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", city='" + city + '\'' +
            '}';
  }


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getCity() {
    return city;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
