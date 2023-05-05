package by.teachmeskills.ps.domain;

import by.teachmeskills.ps.domain.enums.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class BankAccount {
    private String id;
    private String merchantId;
    private Status status;
    private String accountNumber;
    private LocalDateTime createdAt;

    public BankAccount(Merchant m) {
        this.id = String.valueOf(UUID.randomUUID());
        this.merchantId = m.getId();
        this.status = Status.ACTIVE;
        this.accountNumber = UUID.randomUUID().toString().substring(0,11).replaceAll("-", "");
        this.createdAt = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return merchantId + " " + status + " " + accountNumber + " "
                + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm")) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (status != that.status) return false;
        if (!Objects.equals(accountNumber, that.accountNumber))
            return false;
        return Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

}
