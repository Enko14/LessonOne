package ru.stqa.pft.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<User> {
  private Set<User> delegate;

  public Users() {
    this.delegate = new HashSet<User>();
  }

  public Users(Users users) {
    this.delegate = new HashSet<User>(users.delegate);
  }

  public Users(Collection<User> user) {
    this.delegate = new HashSet<User>(user);
  }

  public Set<User> delegate() {
    return delegate;
  }
}
