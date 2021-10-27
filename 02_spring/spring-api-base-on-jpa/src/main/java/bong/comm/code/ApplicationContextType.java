package bong.comm.code;

public enum ApplicationContextType {
    PROFILE_ID(1, "PROFILE_ID"),
    SYSTEM_ID(2, "SYSTEM_ID"),
    CONTEXT_LIST(3, "CONTEXT_LIST"),
    ROOT_CONTEXT(4, "ROOT_CONTEXT"),
    SOURCE_CONTEXT(5, "SOURCE_CONTEXT"),
    USE_YN(6, "USE_YN"),
    SERVLET_TYPE_LIST(7, "SERVLET_TYPE_LIST");

    private final int value;
    private final String applicationType;

    ApplicationContextType(int value, String applicationType) {
        this.value = value;
        this.applicationType = applicationType;
    }

    public int value() {
        return this.value;
    }

    public String getApplicationType() {
        return applicationType;
    }
}
