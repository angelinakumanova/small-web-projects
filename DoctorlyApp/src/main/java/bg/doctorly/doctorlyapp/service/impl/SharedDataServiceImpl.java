package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.service.SharedDataService;
import bg.doctorly.doctorlyapp.service.entityService.CityService;
import bg.doctorly.doctorlyapp.service.entityService.SpecializationService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class SharedDataServiceImpl implements SharedDataService {

    private final SpecializationService specializationService;
    private final CityService cityService;


    public SharedDataServiceImpl(SpecializationService specializationService, CityService cityService) {
        this.specializationService = specializationService;
        this.cityService = cityService;
    }


    @Override
    public void addSharedAttributes(Model model) {
        model.addAttribute("specializations", specializationService.getAll());
        model.addAttribute("cities", cityService.getAll());
    }
}
