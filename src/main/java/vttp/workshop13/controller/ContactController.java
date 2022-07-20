package vttp.workshop13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.workshop13.model.Contact;
import vttp.workshop13.services.DatabaseService;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {

    @Autowired
    private DatabaseService dbSvc;

    @PostMapping(consumes = "application/x-www-form-urlencoded", produces = "text/html")
                                // read from body
    public String postContact(@RequestBody MultiValueMap<String, String> form, Model model) {

        Contact c = new Contact();
        // must tally with html names
        c.setName(form.getFirst("name"));
        c.setEmail(form.getFirst("email"));
        c.setPhone(form.getFirst("phone"));

        System.out.printf(c.toString());
        dbSvc.save(c);

        model.addAttribute("contact", c);

        return "showContact";

    }

    @GetMapping(value = "/{id}", produces = "text/html")
    public String getContact(@PathVariable("id") String id,Model model) { 

        Contact c = new Contact();
        c = dbSvc.read(id);

        System.out.printf(">> id: %s", c);
        model.addAttribute("contact", c);
        return "showContact";

    }
}
