package account.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "oauth_approvals")
@Data
public class OAuthApprovals implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userId")
    private String userId;
    @Column(name = "clientId")
    private String clientId;
    @Column(name = "scope")
    private String scope;
    @Column(name = "status")
    private String status;
    @Column(name = "expiresAt")
    private Date expiresAt;
    @Column(name = "lastModifiedAt")
    private Date lastModifiedAt;
}
