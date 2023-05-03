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
import java.util.concurrent.atomic.AtomicReference;

import static by.teachmeskills.ps.services.interfaces.FilesPathes.BANK_ACCOUNT_PATH;
import static by.teachmeskills.ps.services.interfaces.FilesPathes.MERCHANT_PATH;

public class MerchantService {
    private static List<Merchant> merchants;

    public MerchantService() {
        merchants = new ArrayList<>();
    }

    public BankAccount addBankAccount(String id, String bankAccountNum) throws IOException, IllegalArgumentException, MerchantNotFoundException {
        Merchant m = getMerchantById(id);
        AtomicReference<BankAccount> bankAccountAtomicReference = new AtomicReference<>();
        if (bankAccountNum.matches("[0-9a-zA-Z]{10}")) {
            Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(bankAccountNum)).findFirst();
            o.ifPresentOrElse(
                    (n) -> {
                        if (n.getStatus().equals(Status.DELETED)) {
                            n.setStatus(Status.ACTIVE);
                            bankAccountAtomicReference.set(n);
                        }
                    },
                    () -> {
                        BankAccount bankAccount = new BankAccount(m);
                        m.getBankAccounts().add(bankAccount);
                        bankAccountAtomicReference.set(bankAccount);
                    });
            writeBankAccountsToTxt();
        } else {
            throw new IllegalArgumentException("Incorrect accountNumber.");
        }
        return bankAccountAtomicReference.get();
    }

    public List<BankAccount> getMerchantBankAccounts(String id) throws NoBankAccountsFoundException {
        Optional<Merchant> o = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        AtomicReference<List<BankAccount>> bankAccounts = new AtomicReference<>();
        o.ifPresentOrElse(m -> {
            m.getBankAccounts().sort(Comparator.comparing(BankAccount::getCreatedAt));
            m.getBankAccounts().sort(Comparator.comparing(BankAccount::getStatus));
            bankAccounts.set(m.getBankAccounts());
        }, () -> {
            try {
                throw new NoBankAccountsFoundException("Merchant don't have bank accounts.");
            } catch (NoBankAccountsFoundException e) {
                System.out.println(e.getMessage());
            }
        });
        return bankAccounts.get();
    }

    public void updateBankAccount(String accountNum, String newAccountNum) throws
            IllegalArgumentException, BankAccountNotFoundException {
        if (!accountNum.matches("[0-9a-zA-Z]{10}}") && !newAccountNum.matches("[0-9a-zA-Z]{10}")) {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        merchants.forEach(m -> {
            Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(accountNum)).findFirst();
            if (o.isEmpty()) {
                try {
                    throw new BankAccountNotFoundException("Not found bank account.");
                } catch (BankAccountNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                o.get().setAccountNumber(newAccountNum);
                try {
                    writeBankAccountsToTxt();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void deleteBankAccount(String id, String accountNum) throws
            BankAccountNotFoundException, IllegalArgumentException, IOException, MerchantNotFoundException {
        Merchant m = getMerchantById(id);
        if (!accountNum.matches("[0-9a-zA-Z]{10}")) {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(accountNum)).findFirst();
        if (o.isEmpty()) {
            throw new BankAccountNotFoundException("Not found bank account.");
        } else {
            m.getBankAccounts().remove(o.get());
            writeBankAccountsToTxt();
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
        return merch.orElseThrow();
    }

    public boolean deleteMerchant(String id) throws MerchantNotFoundException, IOException {
        Optional<Merchant> merch = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        merch.ifPresentOrElse(m -> {
            merchants.remove(m);
        }, () -> {
            try {
                throw new MerchantNotFoundException("Merchant with " + id + " not found");
            } catch (MerchantNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
        writeMerchantsToTxt();
        writeBankAccountsToTxt();
        return true;
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
