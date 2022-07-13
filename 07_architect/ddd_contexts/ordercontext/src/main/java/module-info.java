module com.lines.ordercontext {
    requires com.lines.sharedkernel;
    exports com.lines.ordercontext.service;
    exports com.lines.ordercontext.model;
    exports com.lines.ordercontext.repository;
    provides com.lines.ordercontext.service.OrderService
            with com.lines.ordercontext.service.CustomerOrderService;
}