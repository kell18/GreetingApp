package g_app;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static g_app.controllers.SignInController.USERNAME_COOKIE_KEY;
import g_app.controllers.GreetingController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.Cookie;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRedirectWithoutCookies() throws Exception {
        this.mockMvc.perform(get("/welcome")).andExpect(status().isFound());
    }

    @Test
    public void shouldGreetUserWithCookies() throws Exception {
        this.mockMvc.perform(get("/welcome").cookie(new Cookie(USERNAME_COOKIE_KEY, "testUser")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testUser")));
    }
}