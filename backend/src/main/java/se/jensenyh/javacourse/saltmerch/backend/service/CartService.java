package se.jensenyh.javacourse.saltmerch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.repository.CartRepository;

import java.util.Objects;


//Used a Service Class for separation between Repository and Controller
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;


    public void getCart() {
        cartRepository.selectAllItems();
    }


    public int addOrRemoveItemFromCart(String action, CartItem cartItem) {
        if (Objects.equals(action, "add")) {
            return cartRepository.insertOrIncrementItem(cartItem);
        }
        if (Objects.equals(action, "remove")) {
            return cartRepository.deleteOrDecrementItem(cartItem);
        }
        if (action == null) {
            return -2;
        } else return -1;
    }


    public void emptyCart(boolean buyout) {
        cartRepository.deleteAllItems(!buyout);
    }

}
