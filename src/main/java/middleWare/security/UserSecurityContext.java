package middleWare.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class UserSecurityContext implements SecurityContext {
  private final AuthUser principal;
  private final Integer userId;
  private final SecurityContext securityContext;

  public UserSecurityContext(AuthUser principal, Integer userId, SecurityContext securityContext) {
    this.principal = principal;
    this.userId = userId;
    this.securityContext = securityContext;
  }

  @Override
  public Principal getUserPrincipal() {
    return principal;
  }

  @Override
  public boolean isUserInRole(String role) {
    return principal.getUserId().equals(userId);
  }

  @Override
  public boolean isSecure() {
    return securityContext.isSecure();
  }

  @Override
  public String getAuthenticationScheme() {
    return "CUSTOM_TOKEN";
  }
}
