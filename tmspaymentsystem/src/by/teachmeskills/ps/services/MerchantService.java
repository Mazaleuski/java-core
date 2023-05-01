package by.teachmeskills.ps.services;

import by.teachmeskills.ps.domain.BankAccount;
import by.teachmeskills.ps.domain.Merchant;
import by.teachmeskills.ps.domain.enums.Status;
import by.teachmeskills.ps.services.exceptions.BankAccountNotFoundException;
import by.teachmeskills.ps.services.exceptions.MerchantNotFoundException;
import by.teachmeskills.ps.services.exceptions.NoBankAccountsFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static by.teachmeskills.ps.services.interfaces.FilesPathes.BANK_ACCOUNT_PATH;
import static by.teachmeskills.ps.services.interfaces.FilesPathes.MERCHANT_PATH;

public class MerchantService {
    private static List<Merchant> merchants;

    public MerchantService() {
        merchants = new ArrayList<>();
    }

    public BankAccount addBankAccount(String id, String bankAccountNum) throws IOException, IllegalArgumentException, MerchantNotFoundException {
        Merchant m = getMerchantById(id);
        BankAccount bankAccount;
        if (bankAccountNum.matches("[0-9a-zA-Z]{10}")) {
            Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(bankAccountNum)).findFirst();
            if (o.isPresent()) {
                bankAccount = o.get();
                if (bankAccount.getStatus().equals(Status.DELETED)) {
                    bankAccount.setStatus(Status.ACTIVE);
                    writeBankAccountsToTxt();
                }
            } else {
                bankAccount = new BankAccount(m);
                m.getBankAccounts().add(bankAccount);
                writeBankAccountsToTxt();
            }
        } else {
            throw new IllegalArgumentException("Incorrect accountNumber.");
        }
        return bankAccount;
    }

    public List<BankAccount> getMerchantBankAccounts(String id) throws NoBankAccountsFoundException {
        Optional<Merchant> o = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (o.isPresent()) {
            Merchant merch = o.get();
            if (merch.getBankAccounts().size() == 0) {
                throw new NoBankAccountsFoundException("Merchant don't have bank accounts.");
            } else {
                merch.getBankAccounts().sort(Comparator.comparing(BankAccount::getCreatedAt));
                merch.getBankAccounts().sort(Comparator.comparing(BankAccount::getStatus));
                return merch.getBankAccounts();
            }
        }
        return null;
    }

    public void updateBankAccount(String accountNum, String newAccountNum) throws IllegalArgumentException, BankAccountNotFoundException {
        final AtomicBoolean isAccountFound = new AtomicBoolean();
        if (accountNum.matches("[0-9a-zA-Z]{10}}") && newAccountNum.matches("[0-9a-zA-Z]{10}")) {
            merchants.forEach(m -> {
                Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(accountNum)).findFirst();
                if (o.isPresent()) {
                    o.get().setAccountNumber(newAccountNum);
                    try {
                        writeBankAccountsToTxt();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    isAccountFound.set(true);
                }
            });
        } else {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        if (!isAccountFound.get()) {
            throw new BankAccountNotFoundException("Not found bank account.");
        }
    }

    public void deleteBankAccount(String id, String accountNum) throws BankAccountNotFoundException, IllegalArgumentException, IOException, MerchantNotFoundException {
        final AtomicBoolean isAccountFound = new AtomicBoolean();
        Merchant m = getMerchantById(id);
        if (accountNum.matches("[0-9a-zA-Z]{10}")) {
            Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(accountNum)).findFirst();
            if (o.isPresent()) {
                m.getBankAccounts().remove(o.get());
                writeBankAccountsToTxt();
                isAccountFound.set(true);
            }
        } else {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        if (!isAccountFound.get()) {
            throw new BankAccountNotFoundException("Not found bank account.");
        }
    }

    public Merchant createMerchant(Merchant merchant) throws IOException {
        merchants.add(merchant);
        writeMerchantsToTxt();
        return merchant;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public Merchant getMerchantById(String id) throws MerchantNotFoundException {
        Optional<Merchant> merch = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (merch.isPresent()) {
            return merch.get();
        } else {
            throw new MerchantNotFoundException("Merchant with " + id + " not found");
        }
    }

    public boolean deleteMerchant(String id) throws MerchantNotFoundException, IOException {
        Optional<Merchant> merch = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (merch.isPresent()) {
            merchants.remove(merch.get());
            writeMerchantsToTxt();
            writeBankAccountsToTxt();
            return true;
        } else {
            throw new MerchantNotFoundException("Merchant with " + id + " not found");
        }
    }

    private static void writeMerchantsToTxt() throws IOException {
        Files.deleteIfExists(MERCHANT_PATH);
        Files.createFile(MERCHANT_PATH);
        merchants.stream().map(Merchant::toString).forEach(s -> {
            try {
                Files.write(MERCHANT_PATH, s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private static void writeBankAccountsToTxt() throws IOException {
        Files.deleteIfExists(BANK_ACCOUNT_PATH);
        Files.createFile(BANK_ACCOUNT_PATH);
        merchants.stream().map(Merchant::getBankAccounts).forEach(n -> {
            try {
                Files.write(BANK_ACCOUNT_PATH, n.toString()
                        .replaceAll("[^a-zA-Z0-9\n -]", "").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
