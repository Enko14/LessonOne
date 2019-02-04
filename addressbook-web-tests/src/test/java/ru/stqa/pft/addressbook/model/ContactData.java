package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
  @Expose
  @Column(name = "nickname")
  private String nickname;
  @Expose
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(nickname, that.nickname) &&
            Objects.equals(company, that.company) &&
            Objects.equals(city, that.city) &&
            Objects.equals(email, that.email) &&
            Objects.equals(address2, that.address2) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, nickname, company, city, email, address2, email2, email3);
  }

  @Expose
  @Column(name = "address2")
  @Type(type = "text")
  private String address2;
  @Transient
  private String allEmails;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();
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


  public String getAddress2() {
    return address2;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
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
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", company='" + company + '\'' +
            ", city='" + city + '\'' +
            ", email='" + email + '\'' +
            ", address2='" + address2 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withAllphones(String allphones) {
    this.allPhones = allphones;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
