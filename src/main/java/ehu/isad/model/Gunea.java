package ehu.isad.model;

public class Gunea {
    private Integer id;
    private String path;
    private String md5;
    private String version;

    public Gunea(Integer id, String path, String md5, String version) {
        this.id = id;
        this.path = path;
        this.md5 = md5;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
