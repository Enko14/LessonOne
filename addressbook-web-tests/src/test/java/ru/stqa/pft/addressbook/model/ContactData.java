package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String surname;
  private final String nickname;
  private final String company;
  private final String city;
  private final String email;


  private final String group;

  public ContactData(String name, String surname, String nickname, String company, String city, String email, String group) {
    this.name = name;
    this.surname = surname;
    this.nickname = nickname;
    this.company = company;
    this.city = city;
    this.email = email;
    this.group = group;
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
