package com.example.offline_transfer.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // This method previously caused a conflict. It's now removed or updated.
    // You can keep this controller for future use, just avoid mapping "/"

    @GetMapping("/dashboard-info")
    public String dashboardInfo(Model model, HttpSession session) {
        // Placeholder logic for future features
        model.addAttribute("info", "Dashboard Info Page");
        return "dashboard-info";
    }
}
