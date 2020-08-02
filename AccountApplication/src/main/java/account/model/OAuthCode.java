package account.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "oauth_code")
@Data
public class OAuthCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "authentication", columnDefinition="BLOB")
    private byte[] authentication;
    @Column(name = "code")
    private String code;

}
