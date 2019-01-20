package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String name;
  private final String surname;
  private final String nickname;
  private final String company;
  private final String city;
  private final String email;


  private final String group;


  public ContactData(String id, String name, String surname, String nickname, String company, String city, String email, String group) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.nickname = nickname;
    this.company = company;
    this.city = city;
    this.email = email;
    this.group = group;
  }

  public ContactData(String name, String surname, String nickname, String company, String city, String email, String group) {
    this.id = null;
    this.name = name;
    this.surname = surname;
    this.nickname = nickname;
    this.company = company;
    this.city = city;
    this.email = email;
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(city, that.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, city);
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

  public String getId() {
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
