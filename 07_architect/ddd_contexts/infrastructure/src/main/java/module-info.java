import com.lines.infrastructure.db.InMemoryOrderStore;
import com.lines.infrastructure.events.SimpleEventBus;

module com.lines.infrastructure {
    requires transitive com.lines.sharedkernel;
    requires transitive com.lines.ordercontext;
    requires transitive com.lines.shippingcontext;
    provides com.lines.sharedkernel.events.EventBus
            with SimpleEventBus;
    provides com.lines.ordercontext.repository.CustomerOrderRepository
            with InMemoryOrderStore;
    provides com.lines.shippingcontext.repository.ShippingOrderRepository
            with InMemoryOrderStore;
}