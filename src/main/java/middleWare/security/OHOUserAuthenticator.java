package middleWare.security;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class OHOUserAuthenticator implements Authenticator<UserCredentials, AuthUser>
{
    @Override
    public Optional<AuthUser> authenticate(UserCredentials userCredentials) throws AuthenticationException {
        return null;
    }
    /*private TokenDAO tokenDAO;
    private CompanyRelationShipDAO companyRelationShipDAO;

    public OHOUserAuthenticator(TokenDAO tokenDAO, CompanyRelationShipDAO companyRelationShipDAO) {
        this.tokenDAO = tokenDAO;
        this.companyRelationShipDAO = companyRelationShipDAO;
    }

    @Override
    @UnitOfWork
    public Optional<AuthUser> authenticate(UserCredentials credentials) throws AuthenticationException
    {
        AuthUser authenticatedUser = null;
        UserModel user = null ;//tokenDAO.getUser(credentials.getSessionToken());

        if (user != null)
        {
            authenticatedUser = new AuthUser(user.getName(), user.getId());
        }

        return Optional.ofNullable(authenticatedUser);
    }*/
}
