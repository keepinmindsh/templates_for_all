package bong.comm.starter;

import bong.comm.builder.ApplicationBuilder;
import bong.comm.code.ApplicationContextType;
import bong.comm.provider.WINGSContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.util.*;

public class ApplicationStarter {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void run(ApplicationBuilder applicationBuilder) throws ClassNotFoundException {

        if(Optional.ofNullable(applicationBuilder.getArgs()).isPresent()){
            logger.info(Arrays.toString(applicationBuilder.getArgs()));
        }

        String profileId = "";

        if(System.getProperty("profiles") != null){
            profileId = System.getProperty("profiles");
            System.setProperty("spring.profiles.active", profileId);
            logger.info("Spring " + System.getProperty("profiles") + " Setting");
        }else {
            if(Optional.ofNullable(applicationBuilder.getProfileId()).isPresent()){
                System.setProperty("spring.profiles.active", profileId);
                profileId = applicationBuilder.getProfileId();
                logger.info("Spring Custom Setting");
            }else{
                System.setProperty("spring.profiles.active", profileId);
                profileId = "default";
                logger.info("Spring Default Setting");
            }
        }

        if(Optional.ofNullable(applicationBuilder.getApplicationLoaderType()).isPresent()){
            switch (applicationBuilder.getApplicationLoaderType()){
                case PROPERTIES:
                        callApplicationByProperties(applicationBuilder, profileId);
                    break;
                default:
                    logger.error("Specify Application Loader Type!");
                    break;
            }

        }else{
            callApplicationByDB(applicationBuilder, profileId);
        }
    }

    private static void callApplicationByProperties(ApplicationBuilder applicationBuilder, String profileId){
        Properties properties = new Properties();
        Map<ApplicationContextType, String> applicationMap = new HashMap<>();

        if(!Optional.ofNullable(profileId).isPresent() || profileId.isEmpty()){
            logger.error("Please Set specific profile id : java -Dprofiles=your profile id -jar <args>");
            logger.error("Current your profile id : {}", profileId );
            return;
        }

        String propertiesPath = "loader/application-" + profileId +"-loader.properties";

        try {
            properties.load(applicationBuilder.getClassLoader().getClassLoader().getResourceAsStream(propertiesPath));

            applicationMap.put(ApplicationContextType.PROFILE_ID, properties.getProperty(ApplicationContextType.PROFILE_ID.getApplicationType()));
            applicationMap.put(ApplicationContextType.SYSTEM_ID, properties.getProperty(ApplicationContextType.SYSTEM_ID.getApplicationType()));
            applicationMap.put(ApplicationContextType.ROOT_CONTEXT, properties.getProperty(ApplicationContextType.ROOT_CONTEXT.getApplicationType()));
            applicationMap.put(ApplicationContextType.CONTEXT_LIST, properties.getProperty(ApplicationContextType.CONTEXT_LIST.getApplicationType()));
            applicationMap.put(ApplicationContextType.SOURCE_CONTEXT, properties.getProperty(ApplicationContextType.SOURCE_CONTEXT.getApplicationType()));
            applicationMap.put(ApplicationContextType.USE_YN, properties.getProperty(ApplicationContextType.USE_YN.getApplicationType()));
            applicationMap.put(ApplicationContextType.SERVLET_TYPE_LIST, properties.getProperty(ApplicationContextType.SERVLET_TYPE_LIST.getApplicationType()));

            logger.info("Profile Id : " + applicationMap.get(ApplicationContextType.PROFILE_ID));
            logger.info("System Id : " + applicationMap.get(ApplicationContextType.SYSTEM_ID));
            logger.info("Root Context : " + applicationMap.get(ApplicationContextType.ROOT_CONTEXT));
            logger.info("Context List : " + applicationMap.get(ApplicationContextType.CONTEXT_LIST));
            logger.info("Source Context : " + applicationMap.get(ApplicationContextType.SOURCE_CONTEXT));
            logger.info("Dynamic Application Context : " + applicationMap.get(ApplicationContextType.USE_YN));
            logger.info("SERVLET TYPE LIST : " + applicationMap.get(ApplicationContextType.SERVLET_TYPE_LIST));

            String[] servletList = Optional.ofNullable(applicationMap.get(ApplicationContextType.SERVLET_TYPE_LIST)).orElse("").toString().split(",");
            String[] contextList = Optional.ofNullable(applicationMap.get(ApplicationContextType.CONTEXT_LIST)).orElse("").toString().split(",");

            String useYN = Optional.ofNullable(applicationMap.get(ApplicationContextType.USE_YN)).orElse("N").toString();

            if ("Y".equals(useYN) && contextList.length > 0) {
                logger.info("Setting By Property Config");

                int contextSeqNo = 0;

                Class[] classList = new Class[contextList.length];
                ArrayList<Class> classServletArrayList = new ArrayList<>();
                ArrayList<Class> classNoneArrayList = new ArrayList<>();
                ArrayList<Class> classReactiveServletArrayList = new ArrayList<>();

                for (String s : servletList) {

                    switch (s){
                        case "NONE" :
                            classNoneArrayList.add(Class.forName(contextList[contextSeqNo]));
                            break;
                        case "WEB" :
                            classServletArrayList.add(Class.forName(contextList[contextSeqNo]));
                            break;
                        case "REACTIVE" :
                            classReactiveServletArrayList.add(Class.forName(contextList[contextSeqNo]));
                            break;
                    }

                    contextSeqNo++;
                }

                if(classNoneArrayList.size() > 0 && classServletArrayList.size() > 0 ){
                    new SpringApplicationBuilder()
                            .parent(Class.forName(Optional.ofNullable(applicationMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                            .child(classNoneArrayList.toArray(classList)).web(WebApplicationType.NONE)
                            .child(classServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                            .run(applicationBuilder.getArgs());
                }else if(classNoneArrayList.size() > 0 && classReactiveServletArrayList.size() > 0 ){
                    new SpringApplicationBuilder()
                            .parent(Class.forName(Optional.ofNullable(applicationMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                            .child(classNoneArrayList.toArray(classList)).web(WebApplicationType.NONE)
                            .child(classReactiveServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                            .run(applicationBuilder.getArgs());
                }else if(classServletArrayList.size() > 0) {
                    new SpringApplicationBuilder()
                            .parent(Class.forName(Optional.ofNullable(applicationMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                            .child(classServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                            .run(applicationBuilder.getArgs());
                }else if (classReactiveServletArrayList.size() > 0){
                    new SpringApplicationBuilder()
                            .parent(Class.forName(Optional.ofNullable(applicationMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                            .child(classReactiveServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                            .run(applicationBuilder.getArgs());
                }else if(classNoneArrayList.size() > 0){
                    new SpringApplicationBuilder()
                            .parent(Class.forName(Optional.ofNullable(applicationMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                            .child(classNoneArrayList.toArray(classList)).web(WebApplicationType.NONE)
                            .run(applicationBuilder.getArgs());
                }else{
                    throw new ClassNotFoundException("설정할 값이 존재하지 않습니다. TB_IS_CONTEXT_MAPPING 및 Profile ID를 확인해주세요.");
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error(exception.getMessage());


        }
    }

    private static void callApplicationByDB(ApplicationBuilder applicationBuilder, String profileId) throws ClassNotFoundException {
        WINGSContextProvider wingsContextProvider = new WINGSContextProvider();
        Map<ApplicationContextType, Object> datMap = wingsContextProvider.getApplicationContextInfo(applicationBuilder.getSystemId(), profileId);

        logger.info("Profile Id : " + datMap.get(ApplicationContextType.PROFILE_ID));
        logger.info("System Id : " + datMap.get(ApplicationContextType.SYSTEM_ID));
        logger.info("Root Context : " + datMap.get(ApplicationContextType.ROOT_CONTEXT));
        logger.info("Context List : " + datMap.get(ApplicationContextType.CONTEXT_LIST));
        logger.info("Source Context : " + datMap.get(ApplicationContextType.SOURCE_CONTEXT));
        logger.info("Dynamic Application Context : " + datMap.get(ApplicationContextType.USE_YN));
        logger.info("SERVLET TYPE LIST : " + datMap.get(ApplicationContextType.SERVLET_TYPE_LIST));

        String[] servletList = Optional.ofNullable(datMap.get(ApplicationContextType.SERVLET_TYPE_LIST)).orElse("").toString().split(",");
        String[] contextList = Optional.ofNullable(datMap.get(ApplicationContextType.CONTEXT_LIST)).orElse("").toString().split(",");

        String useYN = Optional.ofNullable(datMap.get(ApplicationContextType.USE_YN)).orElse("N").toString();

        if ("Y".equals(useYN) && contextList.length > 0) {
            logger.info("Setting By DB Config");

            int contextSeqNo = 0;

            Class[] classList = new Class[contextList.length];
            ArrayList<Class> classServletArrayList = new ArrayList<>();
            ArrayList<Class> classNoneArrayList = new ArrayList<>();
            ArrayList<Class> classReactiveServletArrayList = new ArrayList<>();

            for (String s : servletList) {

                switch (s){
                    case "NONE" :
                        classNoneArrayList.add(Class.forName(contextList[contextSeqNo]));
                        break;
                    case "WEB" :
                        classServletArrayList.add(Class.forName(contextList[contextSeqNo]));
                        break;
                    case "REACTIVE" :
                        classReactiveServletArrayList.add(Class.forName(contextList[contextSeqNo]));
                        break;
                }

                contextSeqNo++;
            }

            if(classNoneArrayList.size() > 0 && classServletArrayList.size() > 0 ){
                new SpringApplicationBuilder()
                        .parent(Class.forName(Optional.ofNullable(datMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                        .child(classNoneArrayList.toArray(classList)).web(WebApplicationType.NONE)
                        .child(classServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                        .run(applicationBuilder.getArgs());
            }else if(classNoneArrayList.size() > 0 && classReactiveServletArrayList.size() > 0 ){
                new SpringApplicationBuilder()
                        .parent(Class.forName(Optional.ofNullable(datMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                        .child(classNoneArrayList.toArray(classList)).web(WebApplicationType.NONE)
                        .child(classReactiveServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                        .run(applicationBuilder.getArgs());
            }else if(classServletArrayList.size() > 0) {
                new SpringApplicationBuilder()
                        .parent(Class.forName(Optional.ofNullable(datMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                        .child(classServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                        .run(applicationBuilder.getArgs());
            }else if (classReactiveServletArrayList.size() > 0){
                new SpringApplicationBuilder()
                        .parent(Class.forName(Optional.ofNullable(datMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                        .child(classReactiveServletArrayList.toArray(classList)).web(WebApplicationType.SERVLET)
                        .run(applicationBuilder.getArgs());
            }else if(classNoneArrayList.size() > 0){
                new SpringApplicationBuilder()
                        .parent(Class.forName(Optional.ofNullable(datMap.get(ApplicationContextType.ROOT_CONTEXT)).orElse("").toString())).web(WebApplicationType.NONE)
                        .child(classNoneArrayList.toArray(classList)).web(WebApplicationType.NONE)
                        .run(applicationBuilder.getArgs());
            }else{
                throw new ClassNotFoundException("설정할 값이 존재하지 않습니다. TB_IS_CONTEXT_MAPPING 및 Profile ID를 확인해주세요.");
            }

        } else {
            logger.info("Setting By Customer");

            if(Optional.ofNullable(applicationBuilder.getChildNoneApplcationContext()).isPresent()){
                new SpringApplicationBuilder()
                        .parent(applicationBuilder.getParentApplicationContext()).web(WebApplicationType.NONE)
                        .child(applicationBuilder.getChildNoneApplcationContext()).web(WebApplicationType.NONE)
                        .listeners(new ApplicationPidFileWriter())
                        .run(applicationBuilder.getArgs());
            }else{
                new SpringApplicationBuilder()
                        .parent(applicationBuilder.getParentApplicationContext()).web(WebApplicationType.NONE)
                        .child(applicationBuilder.getChildApplicationContext()).web(Optional.ofNullable(applicationBuilder.getWebApplicationType()).orElse(WebApplicationType.SERVLET))
                        .run(applicationBuilder.getArgs());
            }
        }
    }
}
