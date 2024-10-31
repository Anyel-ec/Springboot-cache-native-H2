package top.anyel.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.anyel.cache.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
