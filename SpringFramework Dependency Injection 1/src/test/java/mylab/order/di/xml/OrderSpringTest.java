package mylab.order.di.xml;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private OrderService service;

    @Test
    public void testShoppingCart() {
        assertNotNull("cart가 주입되지 않았습니다.", cart);

        assertNotNull("products가 null 입니다. XML에서 products list 주입 확인하세요.", cart.getProducts());
        assertEquals("products 개수가 2가 아닙니다.", 2, cart.getProducts().size());

        assertEquals("노트북", cart.getProducts().get(0).getName());
        assertEquals("스마트폰", cart.getProducts().get(1).getName());

        // (선택) 디버깅 출력: toString() 확인
        System.out.println("[Cart] " + cart);
        System.out.println("[Cart total] " + cart.getTotalPrice());
    }

    @Test
    public void testOrderService() {
        assertNotNull("service가 주입되지 않았습니다.", service);
        assertNotNull("service.shoppingCart가 null 입니다. XML에서 shoppingCart 주입 확인하세요.", service.getShoppingCart());

        double total = service.calculateOrderTotal();
        assertEquals(950000.0, total, 0.001);

        // (선택) 디버깅 출력
        System.out.println("[Service] " + service);
        System.out.println("[Order total] " + total);
    }
}