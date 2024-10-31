package top.anyel.cache.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.anyel.cache.models.Producto;
import top.anyel.cache.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Cacheable("productos")
    public List<Producto> obtenerTodosLosProductos() {
        log.info("Consultando todos los productos desde la base de datos");
        List<Producto> productos = productoRepository.findAll();
        log.info("Cantidad de productos obtenidos: {}", productos.size());
        return productos;
    }

    @Cacheable(value = "producto", key = "#id")
    public Optional<Producto> obtenerProductoPorId(Long id) {
        log.info("Consultando producto con id={} desde la base de datos", id);
        Optional<Producto> producto = productoRepository.findById(id);
        log.info("Producto encontrado: {}", producto.isPresent());
        return producto;
    }

    @CacheEvict(value = "productos", allEntries = true)
    public Producto guardarProducto(Producto producto) {
        log.info("Guardando nuevo producto en la base de datos: {}", producto);
        Producto productoGuardado = productoRepository.save(producto);
        log.info("Producto guardado con id={}", productoGuardado.getId());
        return productoGuardado;
    }

    @CacheEvict(value = "productos", allEntries = true)
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        log.info("Actualizando producto con id={}", id);
        if (productoRepository.existsById(id)) {
            productoActualizado.setId(id);
            Producto producto = productoRepository.save(productoActualizado);
            log.info("Producto actualizado: {}", producto);
            return producto;
        }
        log.warn("Producto con id={} no encontrado para actualizar", id);
        return null;
    }

    @CacheEvict(value = "productos", allEntries = true)
    public void eliminarProducto(Long id) {
        log.info("Eliminando producto con id={} de la base de datos", id);
        productoRepository.deleteById(id);
        log.info("Producto con id={} eliminado", id);
    }
}
