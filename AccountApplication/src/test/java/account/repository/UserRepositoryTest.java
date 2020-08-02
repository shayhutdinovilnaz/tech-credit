package account.repository;

import account.model.UserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository underTest;

    @Before
    public void createTestDates() {
        final UserModel user1 = new UserModel();
        user1.setEmail("");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setEnabled(true);
        user1.setFirstName("John");
        user1.setLastName("Wayne");
        user1.setUsername("Johnny");
        user1.setPassword("Password");
        entityManager.persist(user1);
    }

    @Test
    public void testFindByUsername() {
        final Optional<UserModel> result = underTest.findByUsernameIgnoreCase("Johnny");
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("Johnny", result.get().getUsername());
    }


}