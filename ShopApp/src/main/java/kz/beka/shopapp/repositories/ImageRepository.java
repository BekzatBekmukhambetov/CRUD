package kz.beka.shopapp.repositories;

import kz.beka.shopapp.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
