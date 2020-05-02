package account.facade.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserData implements Serializable {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
