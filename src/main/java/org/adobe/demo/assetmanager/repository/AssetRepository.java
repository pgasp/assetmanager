package org.adobe.demo.assetmanager.repository;

import java.util.List;

import org.adobe.demo.assetmanager.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByOwnerId(String ownerId);
}