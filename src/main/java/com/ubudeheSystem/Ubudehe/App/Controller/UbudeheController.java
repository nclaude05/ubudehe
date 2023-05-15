package com.ubudeheSystem.Ubudehe.App.Controller;

import com.ubudeheSystem.Ubudehe.App.Domain.Ubudehe;
import com.ubudeheSystem.Ubudehe.App.Domain.User;
import com.ubudeheSystem.Ubudehe.App.Repositories.UbudeheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/ubudehe/")
public class UbudeheController {

    private final UbudeheRepository repo;

    @Autowired
    public UbudeheController(UbudeheRepository repo) {
        this.repo = repo;
    }

    @GetMapping("add")
    public String showRegisterForm(Ubudehe ubudehe) {
        return "./dashboard/add-citizen";
    }

    @GetMapping("list")
    public String showList(Model model) {
        model.addAttribute("viewCitizens", repo.findAll());
        return "./dashboard/viewCitizens";
    }

    @PostMapping("register")
    public String addUbudehe(@Valid Ubudehe ubudehe, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal, @AuthenticationPrincipal User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {
            if (result.hasErrors()) {
                return "./dashboard/add-citizen";
            }


            repo.save(ubudehe);
            redirectAttributes.addFlashAttribute("message", "Information saved successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Ubudehe ubudehe = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        model.addAttribute("ubudehe", ubudehe);
        return "./dashboard/edit-citizen";
    }

    @PostMapping("update/{id}")
    public String updateUbudehe(@PathVariable("id") long id, @Valid Ubudehe ubudehe, BindingResult result,
                                 Model model, RedirectAttributes redirectAttributes, Principal principal) {

        try {
            if (result.hasErrors()) {
                ubudehe.setId(id);
                return "./dashboard/edit-citizen";
            }
            repo.save(ubudehe);
            model.addAttribute("viewCitizens", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Information updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../list";
    }

    @GetMapping("delete/{id}")
    public String deleteUbudehe(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {

        try {
            Ubudehe ubudehe = repo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
            repo.delete(ubudehe);
            model.addAttribute("viewCitizens", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Information deleted successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../list";
    }

}
