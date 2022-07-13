module com.lines.mainapp {
    uses com.lines.sharedkernel.events.EventBus;
    uses com.lines.ordercontext.service.OrderService;
    uses com.lines.ordercontext.repository.CustomerOrderRepository;
    uses com.lines.shippingcontext.repository.ShippingOrderRepository;
    uses com.lines.shippingcontext.service.ShippingService;
    requires transitive com.lines.infrastructure;
}