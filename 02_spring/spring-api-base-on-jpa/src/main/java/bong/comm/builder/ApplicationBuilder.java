package bong.comm.builder;


import bong.comm.code.ApplicationLoaderType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.WebApplicationType;

@Getter
@Builder
public class ApplicationBuilder {
    private final Class[] parentApplicationContext;
    private final Class sourceApplicationContext;
    private final Class[] childApplicationContext;
    private final Class[] childNoneApplcationContext;
    private final Class[] sibilingApplicationContext;
    private final ApplicationLoaderType applicationLoaderType;
    private final Class classLoader;
    private final String[] args;
    private final WebApplicationType webApplicationType;
    private final String systemId;
    private final String profileId;

}
