package itrip;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class itripController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    static final String DBFILENAME = "addressserver.db.tsv";


    public itripController() {
    }


    @RequestMapping("/itriphomepage")
    public String itriphomepage(Model model) {
        return "itriphomepage";
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


    @RequestMapping("/googlemap")
    public String googlemap(Model model, HttpSession session) {
        Object obj = session.getAttribute("user");
        if(!(obj instanceof UserModel)) {
            return "redirect:/login";
        }
        return "googlemap";
    }

    @RequestMapping("/collection")
    public String collection(Model model, HttpSession session) {
        Object obj = session.getAttribute("user");
        if(!(obj instanceof UserModel)) {
            return "redirect:/login";
        }
        Integer userid = ((UserModel) obj).getId();
        List<CollectionModel> collections = collectionRepository.findByOwner(userid);
        model.addAttribute("collections", collections);
        return "collection";
    }

    @RequestMapping("/delcollection")
    public String delcollection(@RequestParam(value = "id", defaultValue = "") Integer id,
                          Model model) {
        collectionRepository.delete(id);
        return "redirect:/collection";
    }

    @RequestMapping("/savecollection")
    public String savecollection(@RequestParam(value = "id", defaultValue = "") Integer id,
                                 @RequestParam(value = "type", defaultValue = "") Integer type,
                                Model model) {

        CollectionModel cl = collectionRepository.findOne(id);
        cl.setType(type);
        collectionRepository.save(cl);
        return "redirect:/collection";
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

    @RequestMapping("/sign")
    public String sign(@RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "password", defaultValue = "") String password,
                          Model model, HttpSession session) {

        if (name.isEmpty() && password.isEmpty()) {
            return "signup";
        }

        UserModel user = new UserModel();
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/login";
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


