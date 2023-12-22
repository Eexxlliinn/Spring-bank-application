package com.example.springbankapplication.controllers;

import com.example.springbankapplication.entities.Account;
import com.example.springbankapplication.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/adminCabinet")
public class AdminCabinetController {

    private AccountRepository accountRepo;

    @Autowired
    public AdminCabinetController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public String getCabinet() {
        return "admin_cabinet";
    }

    @GetMapping("/showAccounts")
    public String getShowAccounts(Model model) {
        model.addAttribute("accounts", accountRepo.findAll());
        return "client_show_accounts";
    }

    @GetMapping("/unblock")
    public String getUnblock(Model model) {
        model.addAttribute("accounts", accountRepo.findByBlock("Blocked"));
        return "admin_unblock";
    }
    @PostMapping("/unblock")
    public String postUnblock(@RequestParam("account") Account selectedAccount) {
        selectedAccount.setBlock("Not blocked");
        accountRepo.save(selectedAccount);
        return "redirect:/adminCabinet";
    }

    @GetMapping("/block")
    public String getBlock(Model model) {
        model.addAttribute("accounts", accountRepo.findByBlock("Not blocked"));
        return "admin_block";
    }
    @PostMapping("/block")
    public String postBlock(@RequestParam("account") Account selectedAccount) {
        selectedAccount.setBlock("Blocked");
        accountRepo.save(selectedAccount);
        return "redirect:/adminCabinet";
    }
}
