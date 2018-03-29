package itrip;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @RequestMapping("/address")
    public String getAddress(@RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "message", defaultValue = "") String message, Model model) {
        AddressModel am = new AddressModel();
        am.setName(name);
        am.setEmail(email);
        am.setMessage(message);
        
        userRepository.save(am);
        model.addAttribute("vm", am);
            return "address";
        }
    
    }


