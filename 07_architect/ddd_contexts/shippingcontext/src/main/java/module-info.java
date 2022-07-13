module com.lines.shippingcontext {
    requires com.lines.sharedkernel;
    exports com.lines.shippingcontext.service;
    exports com.lines.shippingcontext.model;
    exports com.lines.shippingcontext.repository;
    provides com.lines.shippingcontext.service.ShippingService
            with com.lines.shippingcontext.service.ParcelShippingService;
}