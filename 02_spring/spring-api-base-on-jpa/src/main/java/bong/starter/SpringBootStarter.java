package bong.starter;


import bong.comm.builder.ApplicationBuilder;
import bong.comm.starter.ApplicationStarter;
import bong.db.EnableDBConnection;
import bong.starter.module.SampleContext;
import org.springframework.boot.WebApplicationType;


public class SpringBootStarter {
    public static void main(String[] args) throws Exception {
        ApplicationStarter.run(
                ApplicationBuilder
                        .builder()
                        .args(args)
                        .systemId("WINGS")
                        .parentApplicationContext(new Class[]{
                                SpringBootStarter.class
                        })
                        .webApplicationType(WebApplicationType.SERVLET)
                        .childApplicationContext(new Class[]{
                                SampleContext.class
                        }).build());
    }
}
