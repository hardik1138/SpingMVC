package com.narola.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private Validator bookValidator;

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(bookValidator);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello-book")
    public ResponseEntity<String> book1() {
        return ResponseEntity.ok("Hello book");
    }

    @GetMapping("/{bookId}/comments/{commentId}")
    public ResponseEntity<String> getBook(@PathVariable("bookId") int sdsasd, @PathVariable String commentId) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.valueOf(sdsasd) + "=" + commentId);
    }

    @GetMapping("/getbookV1")
    public ResponseEntity<String> getBook2(HttpServletRequest request, @RequestParam("bookId") int bookId) {
        return ResponseEntity.ok(request.getParameter("bookId") + "=" + bookId);
    }

    @GetMapping(value = "/getbookV1", headers = "x-api-key")
    public ResponseEntity<String> getBook1(HttpServletRequest request, @RequestParam("bookId") int bookId,
                                           @RequestHeader Map<String, String> headMap) {
        return ResponseEntity.ok(request.getParameter("bookId") + "=" + bookId);
    }

    @PostMapping(value = "/book-data-bind")
    public Book addBook(@Validated @RequestBody Book book, BindingResult bindingResult) {
        if (book.getName().equals("oracle")) {
            throw new ResourceNotFoundException("Custome message");
        } else if (book.getName().equals("oracle1")) {
            throw new NullPointerException("Custome message");
        } else if (book.getName().equals("oracle2")) {
            throw new EmployeeProflePicNotFound("EmployeeProflePicNotFound");
        }
        return book;
    }

    @GetMapping("/addBookForm")
    public String addBookForm(Model bookModel) {
        bookModel.addAttribute("bookName", "ORACLE");
        return "AddBookForm";
    }

    @PostMapping(value = "/addbook")
    public ModelAndView addBookV1(@Validated Book book, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("BookView");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @ModelAttribute
    public void modelAttribute1(Model model) {
        model.addAttribute("msg", "Welcome to Spring");
    }

    @ModelAttribute("tempBook")
    public Book modelAttribute1() {
        Book book = new Book();
        book.setName("TEMP1");
        book.setAuthor("TEMP2");
        return book;
    }
}
