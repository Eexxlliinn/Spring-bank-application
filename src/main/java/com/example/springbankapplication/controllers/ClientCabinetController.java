package com.example.springbankapplication.controllers;

import com.example.springbankapplication.entities.*;
import com.example.springbankapplication.forms.AccountForm;
import com.example.springbankapplication.forms.ClientForm;
import com.example.springbankapplication.forms.CreditCardForm;
import com.example.springbankapplication.forms.TransactionForm;
import com.example.springbankapplication.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/cabinet")
public class ClientCabinetController {

    private ClientRepository clientRepo;
    private CreditCardRepository creditCardRepo;
    private AccountRepository accountRepo;
    private TransactionRepository transactionRepo;
    private UserRepository userRepo;

    @Autowired
    public ClientCabinetController(ClientRepository clientRepo, CreditCardRepository creditCardRepo, AccountRepository accountRepo, TransactionRepository transactionRepo, UserRepository userRepo) {
        this.clientRepo = clientRepo;
        this.creditCardRepo = creditCardRepo;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String getCabinet() {
        return "client_cabinet";
    }


    @GetMapping("/showInfo")
    public String getShowInfo(Model model) {
        User user = loadCurrentUser();
        model.addAttribute("client", clientRepo.findByUser(user));
        return "client_show_info";
    }

    @GetMapping("/setInfo")
    public String getSetInfo() {
        return "client_set_info";
    }
    @PostMapping("/setInfo")
    public String postSetInfo(ClientForm form) {
        User user = loadCurrentUser();
        Client existingClient = clientRepo.findByUser(user);
        if (existingClient != null) {
            existingClient.setFullName(form.getFullName());
            existingClient.setAge(form.getAge());
            existingClient.setAddress(form.getAddress());
            existingClient.setPhoneNumber(form.getPhoneNumber());
            clientRepo.save(existingClient);
        } else {
            form.setUser(user);
            clientRepo.save(form.toClient());
        }
        return "redirect:/cabinet";
    }

    @GetMapping("/showCards")
    public String getShowCards(Model model) {
        model.addAttribute("cards", loadCurrentCreditCards());
        return "client_show_cards";
    }
    @GetMapping("/setCards")
    public String getSetCards(Model model) {
        model.addAttribute("accounts", accountRepo.findByStatus("Available"));
        return "client_set_cards";
    }

    @PostMapping("/setCards")
    public String postSetCards(CreditCardForm form) {
        CreditCard card = form.toCreditCard();
        card.setClient(loadCurrentClient());
        creditCardRepo.save(card);

        Optional<Account> existedAccount = accountRepo.findById(form.getAccount());
        existedAccount.ifPresent(account -> {
            account.setCreditCard(card);
            account.setStatus("Not available");
            accountRepo.save(account);
        });
        return "redirect:/cabinet";
    }
    @GetMapping("/showAccounts")
    public String getShowAccounts(Model model) {
        model.addAttribute("accounts", loadCurrentAccounts());
        return "client_show_accounts";
    }
    @GetMapping("/setAccounts")
    public String getSetAccounts(Model model) {
        model.addAttribute("client", loadCurrentClient());
        return "client_set_accounts";
    }

    @PostMapping("/setAccounts")
    public String postSetAccounts(AccountForm form) {
        form.setClient(loadCurrentClient());
        accountRepo.save(form.toAccount());
        return "redirect:/cabinet";
    }

    @GetMapping("/makeTransaction")
    public String getMakeTransaction(Model model) {
        model.addAttribute("accounts", accountRepo.findByBlock("Not blocked"));
        return "client_make_transaction";
    }
    @PostMapping("/makeTransaction")
    public String postMakeTransaction(TransactionForm form) {
        transactionRepo.save(form.toTransaction());
        Account existedAccount = form.getAccount();
        if (form.getType().equals("Replenishment")) {
            Long value = Long.parseLong(existedAccount.getMoneyAmount()) + Long.parseLong(form.getAmount());
            existedAccount.setMoneyAmount(value.toString());
        } else {
            Long value = Long.parseLong(existedAccount.getMoneyAmount()) - Long.parseLong(form.getAmount());
            if (value < 0) {
                value = 0L;
            }
            existedAccount.setMoneyAmount(value.toString());
        }
        accountRepo.save(existedAccount);
        return "redirect:/cabinet";
    }

    @GetMapping("/showTransactions")
    public String getShowTransactions(Model model) {
        model.addAttribute("transactions", transactionRepo.findByAccount_CreditCard_Client(loadCurrentClient()));
        return "client_show_transactions";
    }

    @GetMapping("/block")
    public String getBlock(Model model) {
        model.addAttribute("accounts", accountRepo.findByBlock("Not blocked"));
        return "client_block";
    }

    @PostMapping("/block")
    public String postBlock(@RequestParam("account") Account selectedAccount) {
        selectedAccount.setBlock("Blocked");
        accountRepo.save(selectedAccount);
        return "redirect:/cabinet";
    }


    private User loadCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userRepo.findByUsername(username);
    }

    private Client loadCurrentClient() {
        return clientRepo.findByUser(loadCurrentUser());
    }
    private List<CreditCard> loadCurrentCreditCards() {
        return creditCardRepo.findByClient(loadCurrentClient());
    }

    private List<Account> loadCurrentAccounts() {
        return accountRepo.findByClient(loadCurrentClient());
    }
}
