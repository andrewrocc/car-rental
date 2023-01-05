package infrastructure.controller;

import infrastructure.model.User;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class EditUserController {

    private final UserService userService;

//    @GetMapping("/edit-user.html")
//    public ModelAndView getUserListTable() {
//        List<User> users = userService.getAllUsers();
//        ModelAndView view = new ModelAndView("user_table");
//        view.addObject("users", users);
//        return view;
//    }

    @GetMapping("/edit-user.html")
    public ModelAndView getPageUserListTable(@RequestParam(value = "size", required = false, defaultValue = "5") Byte size,
                                             @RequestParam(value = "page", required = false, defaultValue = "0") Byte page) {
        System.out.println("EditUserController controller + " + now());
        Page<User> pageUser = userService.getUserRepository()
                .findAll(PageRequest.of(page, size, Sort.by("id").ascending()));          // TODO to service module

        int noOfPages = (int) Math.ceil(userService.getUserSize() * 1.0 / size);
        ModelAndView view = new ModelAndView("user_table");
        view.addObject("pageUser", pageUser.getContent());
        view.addObject("currentPage", page);
        view.addObject("noOfPages", noOfPages);
        return view;
    }
}
