package spring;

public class VersionPrinter {
    private int majorVersion;
    private int minorVersion;

    public void print() {
        System.out.printf("Ver %d.%d\n\n", majorVersion, minorVersion);
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
}
