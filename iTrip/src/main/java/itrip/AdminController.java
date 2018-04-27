package itrip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    static final String DBFILENAME = "addressserver.db.tsv";


    public AdminController() {
    }


    @RequestMapping("/admin")
    public String admin(Model model, HttpSession session) {
        Object obj = session.getAttribute("admin");
        if(obj == null) {
            return "redirect:/adminlogin";
        }

        List<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/adduser")
    public String adduser(@RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "password", defaultValue = "") String password,
                          Model model, HttpSession session) {


        Object obj = session.getAttribute("admin");
        if(obj == null) {
            return "redirect:/adminlogin";
        }

        if (name.isEmpty() && password.isEmpty()) {
            return "adduser";
        }

        UserModel user = new UserModel();
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("/adminlogin")
    public String adminlogin(@RequestParam(value = "name", defaultValue = "") String name,
                        @RequestParam(value = "password", defaultValue = "") String password,
                        Model model, HttpSession session) {

        Object obj = session.getAttribute("admin");
        if(obj != null) {
            return "redirect:/admin";
        }

        if (!name.isEmpty() && !password.isEmpty()) {
            if (name.equals("admin") && password.equals("1234")) {
                session.setAttribute("admin", new Object());
                return "redirect:/admin";
            } else {
                return "loginerror";
            }
        }
        return "adminlogin";
    }

    @RequestMapping("/deluser")
    public String deluser(@RequestParam(value = "id", defaultValue = "") Integer id,
                          Model model) {
        userRepository.delete(id);
        return "redirect:/admin";
    }
}


