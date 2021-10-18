package bong.lines.realapi.model;

public class Response {

    private String outYN;
    private String outMSG;
    private Object outRefCursor;

    public String getOutYN() {
        return outYN;
    }

    public void setOutYN(String outYN) {
        this.outYN = outYN;
    }

    public String getOutMSG() {
        return outMSG;
    }

    public void setOutMSG(String outMSG) {
        this.outMSG = outMSG;
    }

    public Object getOutRefCursor() {
        return outRefCursor;
    }

    public void setOutRefCursor(Object outRefCursor) {
        this.outRefCursor = outRefCursor;
    }

}
