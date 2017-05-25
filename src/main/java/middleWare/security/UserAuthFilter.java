package middleWare.security;

import configs.C;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthenticationException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class UserAuthFilter extends AuthFilter<UserCredentials, AuthUser> {
    private OHOUserAuthenticator authenticator;

    public UserAuthFilter(OHOUserAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Optional<AuthUser> authenticatedUser;

        try {
            UserCredentials credentials = getCredentials(requestContext);
            authenticatedUser = authenticator.authenticate(credentials);
        } catch (AuthenticationException e) {
            throw new WebApplicationException("Unable to validate credentials", Response.Status.UNAUTHORIZED);
        }

        if (authenticatedUser.isPresent())
        {
            Integer userId = parseUserId(requestContext);
            SecurityContext securityContext = new UserSecurityContext(authenticatedUser.get(), userId, requestContext.getSecurityContext());
            requestContext.setSecurityContext(securityContext);
        }
        else
        {
            throw new WebApplicationException("Credentials not valid", Response.Status.UNAUTHORIZED);
        }
    }

    private Integer parseUserId(ContainerRequestContext requestContext)
    {
        try
        {
            return Integer.valueOf(requestContext.getUriInfo().getPathParameters().getFirst("userId"));
        } catch (Exception e) {
            throw new WebApplicationException("No user ID in path", Response.Status.FORBIDDEN);
        }
    }

    private UserCredentials getCredentials(ContainerRequestContext requestContext)
    {
        UserCredentials credentials = new UserCredentials();

        try {
            String rawToken = requestContext
                    .getCookies()
                    .get("auth_token")
                    .getValue();

            String rawUserId = requestContext
                    .getCookies()
                    .get("auth_user")
                    .getValue();

            String rawUserToken = requestContext
                    .getCookies()
                    .get(C.USER_SESSION_STR)
                    .getValue();

            credentials.setToken(UUID.fromString(rawToken));
            credentials.setUserId(Integer.valueOf(rawUserId));
            credentials.setSessionToken(rawUserToken);
        }
        catch (Exception e)
        {
            throw new WebApplicationException("Unable to parse credentials", Response.Status.UNAUTHORIZED);
        }


        return credentials;
    }
}
