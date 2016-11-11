package g_app;

import g_app.dao.UserDaoImpl;
import g_app.model.User;
import g_app.dao.UserDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class UserDaoTest {

    private EmbeddedDatabase db;

    UserDao userDao;

	String testName = "test";
	String testPassword = "test";

    @Before
    public void setUp() {
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.HSQL)
    		.build();
    }

    @Test
    public void testFindByname() {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	UserDao userDao = new UserDaoImpl(template);

    	boolean	isRegistred = userDao.isUserRegistered(testName);
		User user = new User();
		user.setName(testName);
		user.setPassword(testPassword);
    	boolean	isAuthorized = userDao.isAuthorised(user);

    	Assert.assertEquals(true, isRegistred);
    	Assert.assertEquals(true, isAuthorized);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}