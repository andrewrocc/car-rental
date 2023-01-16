package infrastructure.controller;


import infrastructure.dto.UserDTO;
import infrastructure.model.User;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class EditUserController {

    private final UserService userService;

    @Secured("ROLE_admin")
    @GetMapping("/user-table.html")
    public ModelAndView getPageUserListTable(@RequestParam(value = "size", required = false, defaultValue = "5") Byte size,
                                             @RequestParam(value = "page", required = false, defaultValue = "0") Byte page) {
        System.out.println("getPageUserListTable controller + " + now());

//        if (!userService.isAdmin()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        Page<User> pageUser = userService.getUserRepository()
                .findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
        int noOfPages = (int) Math.ceil(userService.getUserSize() * 1.0 / size);
        ModelAndView view = new ModelAndView("user_table");
        view.addObject("pageUser", pageUser.getContent());
        view.addObject("currentPage", page);
        view.addObject("noOfPages", noOfPages);
        return view;
    }

    @GetMapping("/user-info.html")
    public ModelAndView getUserInfoPageByUserId(@RequestParam("id") long id) {
        System.out.println("getUserInfoPageByUserId call: " + now());
        UserDTO dto = userService.getUserDTOById(id);
        if (dto == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return new ModelAndView("user_info", Map.of("userInfo", userService.getUserDTOById(id)));
    }

    @PostMapping("/user-table.html")
    public ModelAndView updateUserInfo(@RequestParam("id") long id, UserDTO dto) {
        System.out.println("updateUserInfo call: " + now() + "\n" + dto);
        userService.updateUserInfo(id, dto);
        return getUserInfoPageByUserId(id);
    }
}
