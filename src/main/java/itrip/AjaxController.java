package itrip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class AjaxController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    static final String DBFILENAME = "addressserver.db.tsv";


    public AjaxController() {
    }

    @RequestMapping("/addCollection")
    public CollectionModel addCollection(@RequestParam(value = "name", defaultValue = "") String name,
                                         Model model,
                                         HttpSession session,
                                         HttpServletResponse rsp) {
        rsp.setHeader("Content-Type", "application/json;charset=UTF-8");
        Object obj = session.getAttribute("user");
        UserModel user = (UserModel)obj;


        CollectionModel  cm = new CollectionModel();


        cm.setName(name);
        cm.setType(0);
        Integer userId = user.getId();
        cm.setOwner(userId);
        collectionRepository.save(cm);
        return cm;
    }

}