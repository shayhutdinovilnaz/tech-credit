package account.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "oauth_access_token")
@Data
public class OAuthAccessToken {
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;
    @Column(name = "token_id")
    private String tokenId;
    @Lob
    @Column(name = "token", columnDefinition="BLOB")
    private byte[] token;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "client_id")
    private String clientId;
    @Lob
    @Column(name = "authentication", columnDefinition="BLOB")
    private byte[] authentication;
    @Column(name = "refresh_token")
    private String refreshToken;
}
