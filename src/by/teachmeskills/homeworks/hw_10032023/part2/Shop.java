package by.teachmeskills.homeworks.hw_10032023.part2;

import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EmptyProductListException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityAlreadyExistsException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> list = new ArrayList<>();

    public void addProduct(Product product) throws EntityAlreadyExistsException {
        for (Product p : list) {
            if (p.getId() == product.getId()) {
                throw new EntityAlreadyExistsException("Product exist.");
            }
        }
        list.add(product);
    }

    public List<Product> getAllProducts() throws EmptyProductListException {
        if (list.isEmpty()) {
            throw new EmptyProductListException("No products found.");
        }
        return list;
    }

    public void deleteProduct(int id) throws EmptyProductListException, EntityNotFoundException {
        if (list.isEmpty()) {
            throw new EmptyProductListException("No products found.");
        } else {
            for (Product p : list) {
                if (p.getId() == id) {
                    list.remove(p);
                    return;
                }
            }
        }
        throw new EntityNotFoundException(String.format("Product with id %d not found.", id));
    }

    public void updateProduct(int id, int newId, String newName, int newPrice) throws EmptyProductListException, EntityNotFoundException {
        if (list.isEmpty()) {
            throw new EmptyProductListException("No products found.");
        } else {
            for (Product p : list) {
                if (p.getId() == id) {
                    p.setId(newId);
                    p.setName(newName);
                    p.setPrice(newPrice);
                    return;
                }
            }
            throw new EntityNotFoundException(String.format("Product with id %d not found.", id));
        }
    }
}
