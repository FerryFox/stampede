package com.fox.cradle.features.stampSystem.controller;

import com.fox.cradle.configuration.security.jwt.JwtService;
import com.fox.cradle.features.appuser.model.AppUser;
import com.fox.cradle.features.appuser.service.AppUserService;
import com.fox.cradle.features.stampSystem.model.enums.StampCardCategory;
import com.fox.cradle.features.stampSystem.model.enums.StampCardSecurity;
import com.fox.cradle.features.stampSystem.model.enums.StampCardStatus;
import com.fox.cradle.features.stampSystem.model.template.NewTemplate;
import com.fox.cradle.features.stampSystem.model.template.TemplateEdit;
import com.fox.cradle.features.stampSystem.model.template.TemplateResponse;
import com.fox.cradle.features.stampSystem.service.template.TemplateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController
{
    private final JwtService jwtService;
    private final TemplateService templateService;
    private final AppUserService appUserService;

    @GetMapping("/all")
    public ResponseEntity<List<TemplateResponse>> getAllTemplates()
    {
        List<TemplateResponse> response = templateService.getAllTemplates();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/public")
    public ResponseEntity<List<TemplateResponse>> getAllPublicTemplates()
    {
        List<TemplateResponse> response = templateService.getAllPublic();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TemplateResponse>> getMyTemplates(HttpServletRequest httpServletRequest)
    {
        Optional<AppUser> AppUse =  appUserService.
                findUserByEmail(jwtService.extractUsernameFromRequest(httpServletRequest));

        if (AppUse.isEmpty()) return ResponseEntity.badRequest().build();
        else
        {
            List<TemplateResponse> response = templateService.getMyTemplates(AppUse.get());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTemplate(@PathVariable Long id)
    {
        templateService.deleteTemplate(id);
    }

    @PutMapping()
    public ResponseEntity<TemplateResponse> updateTemplate(@RequestBody TemplateEdit request, HttpServletRequest httpServletRequest)
    {
        Optional<AppUser> appUse =  appUserService.
                findUserByEmail(jwtService.extractUsernameFromRequest(httpServletRequest));

        if (appUse.isEmpty()) return ResponseEntity.badRequest().build();
        else
        {
            TemplateResponse response = templateService.updateTemplate(request);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<StampCardCategory[]> getTemplatesByCategory()
    {
        return ResponseEntity.ok(StampCardCategory.values());
    }

    @GetMapping("/security")
    public ResponseEntity<StampCardSecurity[]> getTemplatesBySecurity()
    {
        return ResponseEntity.ok(StampCardSecurity.values());
    }

    @GetMapping("/status")
    public ResponseEntity<StampCardStatus[]> getTemplatesByStatus()
    {
        return ResponseEntity.ok(StampCardStatus.values());
    }

    @PostMapping("/new-template")
    public ResponseEntity<TemplateResponse> createTemplate(@RequestBody NewTemplate request, HttpServletRequest httpServletRequest)
    {
        Optional<AppUser> AppUse =  appUserService.
                findUserByEmail(jwtService.extractUsernameFromRequest(httpServletRequest));

        if (AppUse.isEmpty()) return ResponseEntity.badRequest().build();

        TemplateResponse savedTemplate = templateService.createTemplate(request, AppUse.get());

        return ResponseEntity.created(null).body(savedTemplate);
    }
}


