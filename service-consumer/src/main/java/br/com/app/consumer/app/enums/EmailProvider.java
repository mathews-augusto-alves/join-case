package br.com.app.consumer.app.enums;

public enum EmailProvider {
    GMAIL("smtp.gmail.com", 587, true),
    OUTLOOK("smtp.office365.com", 587, true),
    YAHOO("smtp.mail.yahoo.com", 587, true),
    PROTONMAIL("smtp.protonmail.com", 465, true),
    ICLOUD("smtp.mail.me.com", 587, true),
    ZOHO("smtp.zoho.com", 587, true);

    private final String host;
    private final int port;
    private final boolean useTls;

    EmailProvider(String host, int port, boolean useTls) {
        this.host = host;
        this.port = port;
        this.useTls = useTls;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isUseTls() {
        return useTls;
    }
}
