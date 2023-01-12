package com.example.springmvccrud.controller.admin;

import com.example.springmvccrud.dto.PostDTO;
import com.example.springmvccrud.dto.UserDTO;
import com.example.springmvccrud.entity.UserEntity;
import com.example.springmvccrud.services.PostService;
import com.example.springmvccrud.services.UserService;
import com.example.springmvccrud.util.FileUploadUtil;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping("/admin/posts")
    public String adminPosts() {
        return "admin/posts";
    }

    @GetMapping("/admin/401")
    public String admin401() {
        return "admin/401";
    }

    @GetMapping("/admin/404")
    public String admin404() {
        return "admin/404";
    }

    @GetMapping("/admin/500")
    public String admin500() {
        return "admin/500";
    }

    @GetMapping("/admin/charts")
    public String adminCharts() {
        return "admin/charts";
    }

    @GetMapping("/admin/layout-light")
    public String adminLayoutLight() {
        return "admin/layout-sidenav-light";
    }

    @GetMapping("/admin/layout-static")
    public String adminLayoutStatic() {
        return "admin/layout-static";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin/login";
    }

    @GetMapping("/admin/password")
    public String adminPassword() {
        return "admin/password";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/adminHome";
    }

    @GetMapping("/admin/tables")
    public String adminTables() {
        return "admin/tables";
    }

    @GetMapping("/admin/users")
    public String adminUsers(Model model) {
        List<UserDTO> list = userService.listAll();
        model.addAttribute("listUsers", list);
        return "admin/users";
    }

    @GetMapping("/admin/register")
    public String adminRegister(Model model) {
        model.addAttribute("user", new UserDTO());
        return "admin/register";
    }

    @PostMapping("/admin/register")
    public String adminUsersPost(UserDTO userDTO, RedirectAttributes ra) {
        boolean bool = userService.save(userDTO);
        if (bool) {
            ra.addFlashAttribute("message", "the user has been saved succesfully");
            return "admin/users";
        } else {
            ra.addFlashAttribute("message", "the user has not been saved");
            return "/admin/register";
        }
    }

    @RequestMapping(value = "/admin/addPost", method = RequestMethod.POST)
    public RedirectView adminPostsAdd(PostDTO postDTO,
                                      @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        postDTO.setImg(fileName);
        PostDTO savedUser = postService.save(postDTO);
        String uploadDir = "admin-posts/" + savedUser.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/admin/posts", true);
    }

}
