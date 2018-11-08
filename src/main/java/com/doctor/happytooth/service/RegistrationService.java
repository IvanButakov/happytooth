package com.doctor.happytooth.service;

import com.doctor.happytooth.entity.Registration;
import com.doctor.happytooth.entity.User;
import com.doctor.happytooth.repository.RegistrationRepository;
import com.doctor.happytooth.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private MailSender mailSender;

    public void send(User user, String email, String phone, String idRegistration, String timeVisit){
        if (registrationRepository.findById(Long.valueOf(idRegistration)) != null && registrationRepository.findById(Long.valueOf(idRegistration)).isPresent()) {

            Optional<Registration> byId = registrationRepository.findById(Long.valueOf(idRegistration));

            Registration registration = byId.get();

            registration.setTimeVisit(timeVisit);

            registration.setPhone(phone);

            String message = String.format(
                    "Hello. You will have an appointment with a dentist %s %s at %s.",
                    user.getSurName(),
                    user.getName(),
                    timeVisit
            );

            mailSender.send(email, "Happy Tooth", message);
        }
    }

    public void add(User user, Registration registration, BindingResult bindingResult, Model model){
        String time = Utils.dateNow();
        registration.setTime(time);
        registration.setPhone("");
        registration.setTimeVisit("");

        registration.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = Utils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("registration", registration);
        } else {

            model.addAttribute("registration", null);

            registrationRepository.save(registration);
        }

        Iterable<Registration> registrations = registrationRepository.findAll();

        model.addAttribute("registrations", registrations);
    }

    public void filter(String filter, Model model){
        Iterable<Registration> registrationText = registrationRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            registrationText = registrationRepository.findByReason(filter);
        } else {
            registrationText = registrationRepository.findAll();
        }

        model.addAttribute("registrations", registrationText);
        model.addAttribute("filter" , filter);
    }
}
