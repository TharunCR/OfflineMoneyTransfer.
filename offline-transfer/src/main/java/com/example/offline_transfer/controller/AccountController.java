package com.example.offline_transfer.controller;

import com.example.offline_transfer.model.Account;
import com.example.offline_transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    private final Long ACCOUNT_ID = 1L; // Single user for simplicity

    @GetMapping("/")
    public String dashboard(Model model) {
        Account account = accountService.getAccount(ACCOUNT_ID);
        model.addAttribute("account", account);
        model.addAttribute("mode", accountService.getMode().toString());
        return "dashboard";
    }

    @PostMapping("/toggle")
    public String toggleMode() {
        accountService.toggleMode();
        return "redirect:/";
    }

    @PostMapping("/send")
    public String sendMoney(@RequestParam double amount,
                            @RequestParam Long receiverId,
                            @RequestParam String pin) {
        accountService.sendMoney(ACCOUNT_ID, receiverId, amount, pin);
        return "redirect:/";
    }
}
