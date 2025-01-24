package br.com.app.consumer.app.adpter;

import java.util.HashMap;
import java.util.Map;

import br.com.app.consumer.app.enums.EmailProvider;

public class EmailResolverAdapter {
    private static final Map<String, EmailProvider> providerMap = new HashMap<>();

    static {
        providerMap.put("gmail.com", EmailProvider.GMAIL);
        providerMap.put("outlook.com", EmailProvider.OUTLOOK);
        providerMap.put("hotmail.com", EmailProvider.OUTLOOK);
        providerMap.put("yahoo.com", EmailProvider.YAHOO);
        providerMap.put("protonmail.com", EmailProvider.PROTONMAIL);
        providerMap.put("icloud.com", EmailProvider.ICLOUD);
        providerMap.put("zoho.com", EmailProvider.ZOHO);
    }

    public static EmailProvider resolveProvider(String email) {
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        return providerMap.getOrDefault(domain, null);
    }
}
