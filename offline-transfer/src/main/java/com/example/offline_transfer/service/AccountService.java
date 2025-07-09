package com.example.offline_transfer.service;

import com.example.offline_transfer.model.Account;
import com.example.offline_transfer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private boolean isOnline = false;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    // ✅ Mode: Get current mode
    public String getMode() {
        return isOnline ? "ONLINE" : "OFFLINE";
    }

    // ✅ Toggle Mode (online <-> offline)
    public void toggleMode() {
        isOnline = !isOnline;
        if (isOnline) {
            // On toggling to ONLINE: sync balances
            Account account = getAccountById(1L);
            account.setSavingsBalance(account.getLedgerBalance());
            accountRepository.save(account);
        }
    }
    public void syncBalances(Long userId) {
        Account account = getAccountById(userId);
        account.setSavingsBalance(account.getLedgerBalance());
        accountRepository.save(account);
    }
    public String transferFunds(Long senderId, Long receiverId, double amount, String pin, boolean isOnline) {
        Account sender = getAccountById(senderId);
        Account receiver = getAccountById(receiverId);

        if (!sender.getPin().equals(pin)) {
            return "Invalid PIN";
        }

        if (isOnline) {
            if (sender.getSavingsBalance() < amount) {
                return "Insufficient savings balance";
            }
            sender.setSavingsBalance(sender.getSavingsBalance() - amount);
            receiver.setSavingsBalance(receiver.getSavingsBalance() + amount);
        } else {
            if (sender.getLedgerBalance() < amount) {
                return "Insufficient ledger balance";
            }
            sender.setLedgerBalance(sender.getLedgerBalance() - amount);
            receiver.setLedgerBalance(receiver.getLedgerBalance() + amount);
        }

        accountRepository.save(sender);
        accountRepository.save(receiver);
        return "Transfer successful";
    }



    // ✅ Send Money
    public void sendMoney(Long senderId, Long receiverId, double amount, String pin) {
        Account sender = getAccountById(senderId);
        Account receiver = getAccountById(receiverId);

        if (!sender.getPin().equals(pin)) {
            throw new RuntimeException("Invalid PIN");
        }

        if (isOnline) {
            if (sender.getSavingsBalance() < amount) {
                throw new RuntimeException("Insufficient savings balance");
            }
            sender.setSavingsBalance(sender.getSavingsBalance() - amount);
            receiver.setSavingsBalance(receiver.getSavingsBalance() + amount);
        } else {
            if (sender.getLedgerBalance() < amount) {
                throw new RuntimeException("Insufficient ledger balance");
            }
            sender.setLedgerBalance(sender.getLedgerBalance() - amount);
            receiver.setLedgerBalance(receiver.getLedgerBalance() + amount);
        }

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
}
