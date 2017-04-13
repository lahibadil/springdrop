package me.farhan.springdrop.controllers;

import me.farhan.springdrop.services.AssetService;
import me.farhan.springdrop.services.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "assets")

public class AssetsController {
    private final static Log logger = LogFactory.getLog(AssetsController.class);

    @Autowired
    private AssetService assetService;


    @GetMapping
    public String index(Model model) {
        return "assets/index";
    }

    @GetMapping("/new")
    public String newAsset(Model model) {
        return "assets/form";
    }

    @PostMapping("/create")
    public String createAsset(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
        logger.debug("From Create");
        assetService.saveFile(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/assets";
    }



}
