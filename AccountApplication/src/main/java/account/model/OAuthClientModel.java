package account.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "oauth_client_token")
@Data
public class OAuthClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
}
