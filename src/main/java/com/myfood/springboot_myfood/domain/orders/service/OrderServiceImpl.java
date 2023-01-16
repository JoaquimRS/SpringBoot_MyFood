package com.myfood.springboot_myfood.domain.orders.service;

import com.myfood.springboot_myfood.domain.orders.dto.OrderDto;
import com.myfood.springboot_myfood.domain.orders.dto.OrderProductDto;
import com.myfood.springboot_myfood.domain.orders.entity.OrderEntity;
import com.myfood.springboot_myfood.domain.orders.entity.OrderProductEntity;
import com.myfood.springboot_myfood.domain.orders.repository.OrderRepository;
import com.myfood.springboot_myfood.domain.products.entity.ProductEntity;
import com.myfood.springboot_myfood.domain.products.repository.ProductRepository;
import com.myfood.springboot_myfood.domain.products.service.ProductService;
import com.myfood.springboot_myfood.domain.reserva.entity.ReserveEntity;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    
    @Autowired
    private ProductService pService;

    private HashMap formatProduct(OrderProductEntity entity) {        
        HashMap map = new HashMap<>(){{
            put("cantidad", entity.getCantidad());
            put("producto", pService.getProductById(entity.getId_producto()));
        }};
        
        return map;
    }

    private OrderEntity convertDtoToEntity(OrderDto dto) {
        return OrderEntity.builder()
                .id_pedido(dto.getId_pedido())
                .id_cliente(dto.getId_cliente())
                .fecha(dto.getFecha())
                .estado(dto.getEstado())
                .build();
    }
    private OrderDto converToDto(OrderEntity entity) {
        return OrderDto.builder()
                .id_cliente(entity.getId_cliente())
                .id_pedido(entity.getId_pedido())
                .fecha(entity.getFecha())
                .estado(entity.getEstado())
                .productos_pedidos(entity.getProductos_pedidos()
                            .stream()
                            .map(o -> formatProduct(o))
                            .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    @Override
    public List<OrderDto> getUserOrders(String client) {
        return this.orderRepository
                .getOrders(client)
                .stream()
                .map(this::converToDto)
                .toList();
    }

    @Transactional
    @Override
    public ResponseEntity addOrder(OrderDto newOrder, Set<OrderProductEntity> newOrderProducts) {
        OrderEntity newOrderEntity = convertDtoToEntity(newOrder);
        newOrderEntity.setProductos_pedidos(newOrderProducts);

        if(this.orderRepository.save(newOrderEntity) != null) {
            return new ResponseEntity<>(newOrder.getId_pedido(),HttpStatus.OK);
        }
        return new ResponseEntity<>("{'msg':'Hubo un error al crear el pedido'}", HttpStatus.BAD_REQUEST);
    }

}
