package org.adobe.demo.assetmanager.service;

import java.util.List;

import org.adobe.demo.assetmanager.model.Asset;
import org.adobe.demo.assetmanager.repository.AssetRepository;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    private final AssetRepository repository;

    public AssetService(AssetRepository repository) {
        this.repository = repository;
    }

    // MÉTHODE "LEGACY" (Parfaite pour la démo Cmd+K)
    public long calculateTotalStorageForUser(String ownerId) {
        long totalSize = 0;
        List<Asset> allAssets = repository.findAll();
        
        for (int i = 0; i < allAssets.size(); i++) {
            Asset currentAsset = allAssets.get(i);
            if (currentAsset.getOwnerId() != null && currentAsset.getOwnerId().equals(ownerId)) {
                totalSize = totalSize + currentAsset.getSizeInBytes();
            }
        }
        return totalSize;
    }
}