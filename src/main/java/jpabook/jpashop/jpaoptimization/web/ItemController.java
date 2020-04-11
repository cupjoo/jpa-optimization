package jpabook.jpashop.jpaoptimization.web;

import jpabook.jpashop.jpaoptimization.domain.Item.Book;
import jpabook.jpashop.jpaoptimization.domain.Item.Item;
import jpabook.jpashop.jpaoptimization.service.ItemService;
import jpabook.jpashop.jpaoptimization.web.dto.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm form) {
        Book book = form.toEntity();
        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book book = (Book) itemService.findOne(itemId);
        BookForm form = BookForm.builder()
                .name(book.getName()).price(book.getPrice())
                .stockQuantity(book.getStockQuantity())
                .author(book.getAuthor()).isbn(book.getIsbn()).build();
        form.loadId(book.getId());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form) {
        itemService.updateItem(form.getId(), form.getName(), form.getPrice());
        return "redirect:/items";
    }
}
