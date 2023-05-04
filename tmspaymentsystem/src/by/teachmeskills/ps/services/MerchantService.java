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

    public List<BankAccount> getMerchantBankAccounts(String id) throws NoBankAccountsFoundException, MerchantNotFoundException {
        Merchant merch = merchants.stream().filter(m -> m.getId().equals(id))
                .findAny().orElseThrow(() -> new MerchantNotFoundException("Merchant with " + id + " not found."));
        if (merch.getBankAccounts().size() == 0) {
            throw new NoBankAccountsFoundException("Merchant don't have bank accounts.");
        } else {
            merch.getBankAccounts().sort(Comparator.comparing(BankAccount::getCreatedAt));
            merch.getBankAccounts().sort(Comparator.comparing(BankAccount::getStatus));
        }
        return merch.getBankAccounts();
    }

    public void updateBankAccount(String accountNum, String newAccountNum) throws
            BankAccountNotFoundException, RuntimeException {
        if (!accountNum.matches("[0-9a-zA-Z]{10}}") && !newAccountNum.matches("[0-9a-zA-Z]{10}")) {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        merchants.forEach(m -> {
            Optional<BankAccount> o = m.getBankAccounts().stream().filter(b -> b.getAccountNumber().equals(accountNum)).findFirst();
            o.ifPresentOrElse(ba -> {
                ba.setAccountNumber(newAccountNum);
                try {
                    writeBankAccountsToTxt();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                throw new RuntimeException("Not found bank account.");
            });
        });
    }

    public void deleteBankAccount(String id, String accountNum) throws
            BankAccountNotFoundException, IllegalArgumentException, MerchantNotFoundException, IOException {
        Merchant m = getMerchantById(id);
        if (!accountNum.matches("[0-9a-zA-Z]{10}")) {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        BankAccount bankAccount = m.getBankAccounts().stream().filter(b -> b.getAccountNumber()
                .equals(accountNum)).findFirst().orElseThrow(() -> new BankAccountNotFoundException("Not found bank account."));
        m.getBankAccounts().remove(bankAccount);
        writeBankAccountsToTxt();
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
        Merchant merch = merchants.stream().filter(m -> m.getId().equals(id)).findFirst()
                .orElseThrow(() -> new MerchantNotFoundException("Merchant with " + id + " not found"));
        merchants.remove(merch);
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
