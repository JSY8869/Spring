package jpabook.jpashop.api;

import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

//    @GetMapping("/api/v1/simple-orders") // 엔티티를 반환 -> 사용x
//    public List<Order> ordersV1(){
//        List<Order> orderList = orderRepository.findAllByString(new OrderSearch());
//        return orderList;
//    }
//
//    @GetMapping("/api/v2/simple-orders") // DTO 사용
//    public List<SimpleOrderQueryDto> ordersV2(){
//        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
//        List<SimpleOrderQueryDto> collect = orders.stream()
//                .map(m -> new SimpleOrderQueryDto(m))
//                .collect(Collectors.toList());
//        return collect;
//    }
//
//    @GetMapping("/api/v3/simple-orders") // fetch join 사용 -> DTO 변환
//    public List<OrderSimpleQueryDto> ordersV3(){
//        List<Order> orders = orderRepository.findAllByWithMemberDelivery();
//        List<OrderSimpleQueryDto> collect = orders.stream()
//                .map(m -> new OrderSimpleQueryDto(m))
//                .collect(Collectors.toList());
//        return collect;
//    }

    @GetMapping("/api/v4/simple_orders") // 처음부터 DTO로 불러옴
    public List<OrderSimpleQueryDto> ordersV4(){
        return orderSimpleQueryRepository.findOrderDtos();
    }
}
