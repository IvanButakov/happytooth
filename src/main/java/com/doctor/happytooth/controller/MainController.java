package com.doctor.happytooth.controller;

import com.doctor.happytooth.entity.Registration;
import com.doctor.happytooth.entity.User;
import com.doctor.happytooth.repository.RegistrationRepository;
import com.doctor.happytooth.repository.UserRepository;
import com.doctor.happytooth.service.MailSender;
import com.doctor.happytooth.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    private final RegistrationService registrationService;

    public MainController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        registrationService.filter(filter, model);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Registration registration,
            BindingResult bindingResult,
            Model model){

        registrationService.add(user, registration, bindingResult, model);

        return "main";
    }

    @PostMapping(value = "/main", params = {"email", "phone", "idRegistration", "timeVisit"})
    public String send(@AuthenticationPrincipal User user,
                       @RequestParam String email,
                       @RequestParam String phone,
                       @RequestParam String idRegistration,
                       @RequestParam String timeVisit
                       ){

        registrationService.send(user, email, phone, idRegistration, timeVisit);

        return "redirect:/main";
    }
}
