package g_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(HttpServletResponse response, @ModelAttribute User user) {
        // todo check user
        response.addCookie(new Cookie("username", user.getName()));
        return "redirect:/wellcome";
    }

}
