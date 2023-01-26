package se.jensenyh.javacourse.saltmerch.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.jensenyh.javacourse.saltmerch.backend.controller.CartController;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.service.CartService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;


    //Makes sure that PATCH /api/vi/carts/{id} returns a 200 response when using "add" as a RequestParam
    @Test
    public void addOrRemoveItemFromCart_ShouldReturnHttpStatusOk() {

        int id = 1;
        String action = "add";
        CartItem cartItem = new CartItem();
        when(cartService.addOrRemoveItemFromCart(action, cartItem)).thenReturn(1);

        ResponseEntity<CartItem> result = cartController.addOrRemoveItemFromCart(id, action, cartItem);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    //Makes sure that PATCH /api/v1/carts/{id} returns a 400 response when using an invalid string as RequestParam
    @Test
    public void addOrRemoveItemFromCart_ShouldReturnHttpStatusBadRequest() {

        int id = 1;
        String action = "invalid";
        CartItem cartItem = new CartItem();
        when(cartService.addOrRemoveItemFromCart(action, cartItem)).thenReturn(-1);

        ResponseEntity<CartItem> result = cartController.addOrRemoveItemFromCart(id, action, cartItem);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    //Makes sure that DELETE /api/v1/carts/{id} returns a 200 response when passing an id of 1 and buyout = true
    @Test
    public void clearCartContentsOrRestock_ShouldReturnHttpStatusOk() {

        int id = 1;
        boolean buyout = true;
        doNothing().when(cartService).emptyCart(buyout);

        ResponseEntity<CartItem> result = cartController.clearCartContentsOrRestock(id, buyout);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    //Makes sure that DELETE /api/v1/carts/{id} returns a 400 response when passing an invalid id and buyout = true
    @Test
    public void clearCartContentsOrRestock_ShouldReturnHttpStatusBadRequest() {

        int id = 2;
        boolean buyout = true;

        ResponseEntity<CartItem> result = cartController.clearCartContentsOrRestock(id, buyout);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
