/*
package com.autentia.restservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableOAuth2Client
public class OAuthClientConfiguration {
    private static final String OAUTH_CLIENT = "5894eeb2414f4ee090c6eae1115e430f";

    private static final String SECRET = "2b91b3c65e0e4c51ba00ea54276f818c";

    private static final String OAUTH_TOKEN_URI = "https://accounts.spotify.com/api/token";

    private static final String OAUTH_AUTHORIZATION_URI = "https://accounts.spotify.com/authorize";

    private static final String OATH_REDIRECT_URI ="https://accounts.spotify.com/authorize";

    private static final List<String> SCOPES = Arrays.asList("user-read-private",
            "user-read-email",
            "playlist-read-private",
            "user-library-read",
            "user-library-modify",
            "user-top-read",
            "playlist-read-collaborative",
            "playlist-modify-public",
            "playlist-modify-private",
            "user-follow-read",
            "user-follow-modify",
            "user-read-playback-state",
            "user-read-currently-playing",
            "user-modify-playback-state",
            "user-read-recently-played");

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    public OAuthClientConfiguration(){
        //Para Spring
    }

    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(buildResourceForOAuthClient(), oauth2ClientContext);
    }

    private OAuth2ProtectedResourceDetails buildResourceForOAuthClient(){
        return buildResourceDetails(OAUTH_CLIENT, SECRET, OAUTH_TOKEN_URI, OAUTH_AUTHORIZATION_URI,OATH_REDIRECT_URI,
                SCOPES);
    }

    private OAuth2ProtectedResourceDetails buildResourceDetails(final String clientId, final String secret,
                                                                final String accessTokenUri, final String userAuthorizationUri,
                                                                String redirectUri, final List<String> scopes) {
        final AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId(clientId);
        resource.setClientSecret(secret);
        resource.setAccessTokenUri(accessTokenUri);
        resource.setUserAuthorizationUri(userAuthorizationUri);
        resource.setScope(scopes);
        resource.setPreEstablishedRedirectUri(redirectUri);
        return resource;
    }
}*/
