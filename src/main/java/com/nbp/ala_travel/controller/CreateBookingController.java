package com.nbp.ala_travel.controller;

import com.nbp.ala_travel.model.CreateBookingResponse;
import com.nbp.ala_travel.service.CreateBookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("createBookingController")
public class CreateBookingController {
    private final CreateBookingService service;

    @GetMapping("/create-form/{tour_instance_id}")
    public String createBookingForm(Model model,
                                    @PathVariable Long tour_instance_id) {
        model.addAttribute("tour_instance_id", tour_instance_id);
        model.addAttribute("bodyContent", "create-booking-form");
        return "master-template";
    }

    @PostMapping("/create")
    public String createBooking(Model model,
                                @RequestParam Long tourist_id,
                                @RequestParam Long tour_instance_id,
                                @RequestParam Integer number_of_participants) {
        CreateBookingResponse result = service.createBooking(number_of_participants, tourist_id, tour_instance_id);
        if (result.message.equals("OK")) {
            model.addAttribute("insertIntoReviewAndCheck", result.booking);
            model.addAttribute("tourist_id", tourist_id);
            model.addAttribute("tour_instance_id", tour_instance_id);
            model.addAttribute("number_of_participants", number_of_participants);
            return "redirect:/home";
        } else {
            model.addAttribute("errorMsg", result.message);
            model.addAttribute("bodyContent","error");
            return "master-template";
        }

    }

}
