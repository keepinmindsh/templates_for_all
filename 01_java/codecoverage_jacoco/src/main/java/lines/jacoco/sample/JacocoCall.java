package lines.jacoco.sample;

public class JacocoCall {

    public String call(String value){
        if("Hello".equals(value)){
            return value;
        }else{
            return "Nothing";
        }
    }
}
