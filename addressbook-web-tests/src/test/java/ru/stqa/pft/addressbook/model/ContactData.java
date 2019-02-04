package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String name;
  @Expose
  @Column(name = "lastname")
  private String surname;
  @Column(name = "nickname")
  private String nickname;
  @Column(name = "company")
  private String company;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String city;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Transient
  private String group;
  @Column(name = "home")
  @Type(type = "text")
  private String homeTel;
  @Column(name = "work")
  @Type(type = "text")
  private String workTel;
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobileTel;
  @Transient
  private String allPhones;
  @Column(name = "photo")
  @Type(type = "text")
  private String pictire;

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  @Transient
  private String allEmails;


  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }


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

  public String getHomeTel() {
    return homeTel;
  }

  public ContactData withHomeTel(String homeTel) {
    this.homeTel = homeTel;
    return this;
  }

  public String getWorkTel() {
    return workTel;
  }

  public ContactData withWorkTel(String workTel) {
    this.workTel = workTel;
    return this;
  }

  public String getMobileTel() {
    return mobileTel;
  }

  public ContactData withMobileTel(String mobileTel) {
    this.mobileTel = mobileTel;
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

  public File getPictire() {
    return new File(pictire);
  }

  public ContactData withPictire(File pictire) {
    this.pictire = pictire.getPath();
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

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getGroup() {
    return group;
  }

  public ContactData withAllphones(String allphones) {
    this.allPhones = allphones;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }
}
