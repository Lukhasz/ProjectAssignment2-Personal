package se.jensenyh.javacourse.saltmerch.backend.controller;


import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.service.CartService;

@CrossOrigin(origins = "http://localhost:3010")
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    CartService cartService;


    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartId() {
        cartService.getCart();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CartItem> addOrRemoveItemFromCart(@PathVariable("id") int id,
                                                            @RequestParam String action,
                                                            @RequestBody CartItem cartItem) {
        if (id == 1) {
            int fromCartService = cartService.addOrRemoveItemFromCart(action, cartItem);

            switch (fromCartService) {
                case 1:
                    return new ResponseEntity<>(HttpStatus.OK);
                case -1:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case -2:
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CartItem> clearCartContentsOrRestock(@PathVariable("id") int id,
                                                               @RequestParam @Nullable boolean buyout) {

        if (id == 1) {
            cartService.emptyCart(buyout);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
