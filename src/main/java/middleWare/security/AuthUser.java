package middleWare.security;

import java.security.Principal;

public class AuthUser implements Principal
{
  private final String name;
  private final Integer userId;

  public AuthUser(String name, Integer userId) {
    this.name = name;
    this.userId = userId;
  }

  @Override
  public String getName() {
    return null;
  }

  public Integer getUserId() {
    return userId;
  }
}
