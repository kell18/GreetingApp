package g_app.controllers;

import g_app.utils.Range;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {
    Map<Range<Integer>, String> partsOfDay = new HashMap<Range<Integer>, String>();

    @GetMapping("/")
    public String redirectToGreetingPage() {
        return "redirect:/welcome";
    }

    @GetMapping("welcome")
    public String greetingPage(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.isEmpty()) {
            return "redirect:/sign-in";
        } else {
            Date date=new Date();
            Integer hour = Integer.parseInt(new SimpleDateFormat("H").format(date));
            model.addAttribute("greeting",getGreetingFor(hour, username));
            model.addAttribute("username", username);
            return "welcome";
        }
    }

    @PostMapping("/welcome")
    public String logout() {
        return "redirect:/sign-in";
    }

    private String getGreetingFor(Integer hour, String username) {
        for (Map.Entry<Range<Integer>, String> entry : partsOfDay.entrySet()) {
            if (entry.getKey().isInRabge(hour)) {
                return "Good " + entry.getValue() + ", " + username + "!";
            }
        }
        throw new RuntimeException("partsOfDay not contains value for hour = " + hour);
    }

    public GreetingController() {
        partsOfDay.put(new Range<>(6, 10), "morning");
        partsOfDay.put(new Range<>(10, 18), "day");
        partsOfDay.put(new Range<>(18, 22), "evening");
        partsOfDay.put(new Range<>(22, 25), "night");
        partsOfDay.put(new Range<>(0, 6), "night");
    }
}