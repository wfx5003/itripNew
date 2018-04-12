package itrip;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class itripController {

    @Autowired
    private UserRepository userRepository;

    static final String DBFILENAME = "addressserver.db.tsv";


    public itripController() {
    }


    @RequestMapping("/itriphomepage")
    public String itriphomepage(Model model) {
        return "itriphomepage";
    }
        @RequestMapping("/LookAround")
    public String LookAround(Model model) {
        return "LookAround";
    }
    @RequestMapping("/UrTrip")
    public String UrTrip(Model model) {
        return "UrTrip";
    }
 
    @RequestMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }
    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
    @RequestMapping("/yelp")
    public String yelp(Model model) {
        return "yelp";
    }

    @RequestMapping("/diytrip")
    public String diytrip(Model model) {
        return "diytrip";
    }

    @RequestMapping("/googlemap")
    public String googlemap(Model model, HttpSession session) {
        Object obj = session.getAttribute("user");
        if(!(obj instanceof UserModel)) {
            return "redirect:/login";
        }
        return "googlemap";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", defaultValue = "") String name,
                        @RequestParam(value = "password", defaultValue = "") String password,
                        Model model, HttpSession session) {

        Object obj = session.getAttribute("user");
        if(obj instanceof UserModel) {
            return "redirect:/googlemap";
        }

      if (!name.isEmpty() && !password.isEmpty()) {
          List<UserModel> users = userRepository.findByName(name);
          if (null != users && users.size() == 0) {
              return "login";
          } else {
              UserModel user = users.get(0);
              if (user.getPassword().equals(password)) {
                  session.setAttribute("user", user);
                  return "redirect:/googlemap";
              } else {
                  return "loginerror";
              }
          }
      }
      return "login";
    }



    @RequestMapping("/address")
    public String getAddress(@RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "message", defaultValue = "") String message, Model model) {
        AddressModel am = new AddressModel();
        /*
        am.setName(name);
        am.setEmail(email);
        am.setMessage(message);
        userRepository.save(am);*/
        am.setName("name");
        am.setEmail("email");
        am.setMessage("message");
        model.addAttribute("vm", am);
            return "address";
        }
    }


