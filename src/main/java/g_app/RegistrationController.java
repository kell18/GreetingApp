package g_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(HttpServletResponse response, @ModelAttribute User user, Model model) {
        // todo: validate
        // todo: add to db
        // todo: add to cookie
        response.addCookie(new Cookie("username", user.getName()));
        return "redirect:/wellcome";
    }
}
