package by.teachmeskills.ps.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Merchant {
    private String id;
    private String name;
    private List<BankAccount> bankAccounts;
    private LocalDateTime createdAt;

    public Merchant(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.bankAccounts = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm")) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Merchant merchant = (Merchant) o;

        if (!Objects.equals(id, merchant.id)) return false;
        if (!Objects.equals(name, merchant.name)) return false;
        if (!Objects.equals(bankAccounts, merchant.bankAccounts))
            return false;
        return Objects.equals(createdAt, merchant.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bankAccounts != null ? bankAccounts.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
