package g_app.controllers;

import g_app.dao.UserDao;
import g_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static g_app.controllers.SignInController.USERNAME_COOKIE_KEY;

@Controller
public class SignUpController {
    UserDao userDao;

    @GetMapping("/sign-up")
    public String registrationForm(@CookieValue(value = USERNAME_COOKIE_KEY, defaultValue = "") String username, Model model) {
        if (username.isEmpty()) {
            model.addAttribute("user", new User());
            return "sign-up";
        } else {
            return "redirect:/welcome";
        }
    }

    @PostMapping("/sign-up")
    public String registrationSubmit(HttpServletResponse response, @ModelAttribute User user, Model model) {
        if (!user.isValidUser()) {
            model.addAttribute("error", "Username or password format is incorrect.");
            return "sign-up";
        } else if (userDao.isUserRegistered(user.getName())) {
            model.addAttribute("error", "User with this name is already registered");
            return "sign-up";
        } else {
            userDao.createUser(user);
            response.addCookie(new Cookie(USERNAME_COOKIE_KEY, user.getName()));
            return "redirect:/welcome";
        }
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        Assert.notNull(userDao);
        this.userDao = userDao;
    }
}
