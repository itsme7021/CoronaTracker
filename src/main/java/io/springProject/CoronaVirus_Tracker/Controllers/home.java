package io.springProject.CoronaVirus_Tracker.Controllers;

import io.springProject.CoronaVirus_Tracker.Service.service;
import io.springProject.CoronaVirus_Tracker.model.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class home {

    @Autowired
    service coronaVirusTrackerService;

    @GetMapping("/")
    public String home(Model model)
    {
        List<LocationStats> allStats = coronaVirusTrackerService.getAllStats();
        long totalCases = allStats.stream().mapToLong(s -> Long.valueOf(s.getRecordstillnow())).sum();
        long newCases = allStats.stream().mapToLong(s -> Long.valueOf(s.getDiffFromLastDay())).sum();
        model.addAttribute("LocationStats" , allStats);
        model.addAttribute("totalCases" , totalCases);
        model.addAttribute("newCases" , newCases);
        return "home";
    }
}
