package by.teachmeskills.homeworks.hw_10032023.part2;

import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EmptyProductListException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityAlreadyExistsException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> list = new ArrayList<>();

    public void addProduct(Product product) {
        for (Product p : list) {
            if (p.getId() == product.getId()) {
                try {
                    throw new EntityAlreadyExistsException("Product exist.");
                } catch (EntityAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }
        list.add(product);
    }

    public List<Product> getAllProducts() {
        if (list.isEmpty()) {
            try {
                throw new EmptyProductListException("No products found.");
            } catch (EmptyProductListException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    public void deleteProduct(int id) {
        if (list.isEmpty()) {
            try {
                throw new EmptyProductListException("No products found.");
            } catch (EmptyProductListException e) {
                System.out.println(e.getMessage());
                return;
            }
        } else {
            for (Product p : list) {
                if (p.getId() == id) {
                    list.remove(p);
                    return;
                }
            }

        }
        try {
            throw new EntityNotFoundException("Product with id " + id + " not found.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(int id, int newId, String newName, int newPrice) {
        if (list.isEmpty()) {
            try {
                throw new EmptyProductListException("No products found.");
            } catch (EmptyProductListException e) {
                System.out.println(e.getMessage());
            }
        } else {
            for (Product p : list) {
                if (p.getId() == id) {
                    p.setId(newId);
                    p.setName(newName);
                    p.setPrice(newPrice);
                    return;
                }
            }
            try {
                throw new EntityNotFoundException("Product with id " + id + " not found.");
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
