package org.example.entity;

public class Secret {
    private String username;
    private String password;
    private String engine;
    private String host;
    private String port;
    private String dbInstanceIdentifier;
    private String dbname;

    public Secret() {
    }

    public Secret(String username, String password, String engine, String host, String port, String dbInstanceIdentifier, String dbname) {
        this.username = username;
        this.password = password;
        this.engine = engine;
        this.host = host;
        this.port = port;
        this.dbInstanceIdentifier = dbInstanceIdentifier;
        this.dbname = dbname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbInstanceIdentifier() {
        return dbInstanceIdentifier;
    }

    public void setDbInstanceIdentifier(String dbInstanceIdentifier) {
        this.dbInstanceIdentifier = dbInstanceIdentifier;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    @Override
    public String toString() {
        return "Secret{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", engine='" + engine + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", dbInstanceIdentifier='" + dbInstanceIdentifier + '\'' +
                ", dbname='" + dbname + '\'' +
                '}';
    }
}
