package bong.lines.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/greeting")
public class ScreenController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam("value") String value, Model model){

        model.addAttribute("value", value);

        return "greeting";
    }

}
