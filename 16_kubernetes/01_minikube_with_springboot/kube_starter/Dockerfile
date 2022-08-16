FROM adoptopenjdk/openjdk11
ENV APP_HOME=/Users/lines/sources/01_bonggit/templates_for_all/16_kubernetes/01_minikube_with_springboot/kube_starter/build/libs/
WORKDIR $APP_HOME
COPY ./*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
