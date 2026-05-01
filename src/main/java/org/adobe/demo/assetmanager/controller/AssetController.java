package org.adobe.demo.assetmanager.controller;

import org.adobe.demo.assetmanager.service.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/user/{ownerId}/storage")
    public long getUserStorage(@PathVariable String ownerId) {
        return assetService.calculateTotalStorageForUser(ownerId);
    }
}