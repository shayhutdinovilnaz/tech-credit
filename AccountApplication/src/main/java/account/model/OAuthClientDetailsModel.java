package account.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "oauth_client_details")
@Data
public class OAuthClientDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id", nullable = false)
    private String clientId;
    @Column(name = "client_secret", nullable = false)
    private String clientSecret;
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;
    @Column(name = "scope")
    private String scope;
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    @Column(name = "resource_ids")
    private String resourceIds;
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    @Column(name = "authorities")
    private String authorities;
    @Column(name = "additional_information")
    private String additionalInformation;
    @Column(name = "autoapprove")
    private String autoApprove;
}
