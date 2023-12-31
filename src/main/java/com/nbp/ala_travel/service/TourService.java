package com.nbp.ala_travel.service;

import com.nbp.ala_travel.model.Tour;
import com.nbp.ala_travel.model.Tourguide;
import com.nbp.ala_travel.repository.TourRepository;
import com.nbp.ala_travel.repository.TourguideRepository;
import com.nbp.ala_travel.repository.TouristRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TourService {
    private final TourRepository repository;
    private final TouristRepository touristRepository;
    private final TourguideRepository tourguideRepository;

    public String createTour(Long new_starting_point, Long new_tour_guide_id, Long new_city_id,
                             String new_title, String new_desc, Integer new_duration, Integer new_max_participants) {
        try {
            repository.createTour(new_starting_point, new_tour_guide_id, new_city_id, new_title, new_desc,
                    new_duration, new_max_participants);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
    }

    public List<Tour> getToursFromPersonId(Long personId) {
        Tourguide tourguide = tourguideRepository.findByPersonid(personId);
        return repository.getToursByTourguideid(tourguide.getId());
    }
}
