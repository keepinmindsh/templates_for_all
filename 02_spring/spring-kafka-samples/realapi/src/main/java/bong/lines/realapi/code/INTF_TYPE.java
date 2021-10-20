package bong.lines.realapi.code;

public enum INTF_TYPE {
    ROOM_KEY("ROOM_KEY"),
    ROOM_CLEAN("ROOM_CLEAN"),
    APPROVAL("APPROVAL"),
    BIG_PRINT("BIG_PRINT"),
    EXCEL_DOWN("EXCEL_DOWN"),
    DIRECT_PRINT("DIRECT_PRINT");

    private final String value;

    private INTF_TYPE(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
