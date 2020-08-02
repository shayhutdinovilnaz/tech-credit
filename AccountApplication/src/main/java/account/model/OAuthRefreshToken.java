package account.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "oauth_refresh_token")
@Data
public class OAuthRefreshToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "authentication", columnDefinition="BLOB")
    private byte[] authentication;
    @Column(name = "token_id")
    private String tokenId;
    @Lob
    @Column(name = "token", columnDefinition="BLOB")
    private byte[] token;
}
