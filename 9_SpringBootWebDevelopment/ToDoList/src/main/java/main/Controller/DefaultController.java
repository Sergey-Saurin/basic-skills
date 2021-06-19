package main.Controller;
import main.module.Business;
import main.module.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController
{
    @Autowired
    private BusinessRepository businessRepository;

    @Value("${parametrTest.value}")
    private Integer parametrTest;

    @RequestMapping("/")
    public String index(Model model)
    {
        List<Business> businesses = new ArrayList<>();
        for (Business b : businessRepository.findAll()) {
            businesses.add(b);
        }
        model.addAttribute("businesses", businesses);
        model.addAttribute("parametrTest", parametrTest);
        return "index";
    }

}
