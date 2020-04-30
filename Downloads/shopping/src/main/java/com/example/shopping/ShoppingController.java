package com.example.shopping;

import com.example.shopping.dto.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    ShoppingService shoppingService;

    @RequestMapping(value = "/buy", method = {RequestMethod.POST})

    @ResponseBody
    public Cart buy(@RequestBody ShoppingForm shoppingForm) {
        return shoppingService.addItem(shoppingForm.getId());
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public Cart delete(@RequestBody ShoppingForm shoppingForm) {
        return shoppingService.deleteItem(shoppingForm.getCode());
    }
}
