package g_app.controllers;

import g_app.model.User;
import g_app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {
    UserDao userDao;

    @GetMapping("/sign-in")
    public String signInForm(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        // todo get username from cookie
        // todo show exit msg
        // todo: colorize msg
        if (username.isEmpty()) {
            model.addAttribute("user", new User());
            return "sign-in";
        } else {
            return "redirect:/welcome";
        }
    }

    @PostMapping("/sign-in")
    public String signInSubmit(HttpServletResponse response, @ModelAttribute User user, Model model) {
        if (userDao.isAuthorised(user)) {
            response.addCookie(new Cookie("username", user.getName()));
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", "Invalid sign-in or password.");
            return "sign-in";
        }
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        Assert.notNull(userDao);
        this.userDao = userDao;
    }
}
