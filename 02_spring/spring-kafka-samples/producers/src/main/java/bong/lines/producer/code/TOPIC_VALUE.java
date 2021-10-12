package bong.lines.producer.code;

public enum TOPIC_VALUE {
    BONG_LINES("bong-lines"),
    NEW_TOPICS("wingsent5-machine");

    private final String value;

    TOPIC_VALUE(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
