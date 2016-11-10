package g_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GreetingController {

    @GetMapping("/wellcome")
    public String greetingPage(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.isEmpty()) {
            return "redirect:/login";
        } else {
            model.addAttribute("username", username);
            return "wellcome";
        }
    }

    @PostMapping("/wellcome")
    public String logout() {
        return "redirect:/login";
    }
}
