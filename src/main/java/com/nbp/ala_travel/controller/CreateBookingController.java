package com.nbp.ala_travel.controller;

import com.nbp.ala_travel.service.CreateBookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("createBookingController")
public class CreateBookingController {
    private final CreateBookingService service;

    @PostMapping("/create")
    public String createBooking(Model model,
                                @RequestParam Long tourist_id,
                                @RequestParam Long tour_instance_id,
                                @RequestParam Integer number_of_participants) {
        model.addAttribute("insertIntoReviewAndCheck",
                service.createBooking(number_of_participants, tourist_id, tour_instance_id));
        model.addAttribute("tourist_id", tourist_id);
        model.addAttribute("tour_instance_id", tour_instance_id);
        model.addAttribute("number_of_participants", number_of_participants);
        model.addAttribute("bodyContent", "create-booking");
        return "master-template";
    }
}