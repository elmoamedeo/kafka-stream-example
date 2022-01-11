package infra.rest.location;

import core.gateway.LocationGateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static infra.util.helper.PropertyValueHelper.getConfigPropValueFromField;
import static infra.util.validator.IpAddressValidator.isValidIpAddress;

public class LocationGatewayImpl implements LocationGateway {

    private static final String IP_STACK_GET = "http://api.ipstack.com/%s?access_key=%s";

    @Override
    public String checkLocation(final String ipAddress) throws IOException {
        if (!isValidIpAddress(ipAddress))
            throw new IllegalArgumentException("Invalid ipv4 address: " + ipAddress);

        final StringBuilder result = new StringBuilder();
        final String getUrl = String.format(IP_STACK_GET, ipAddress, getIpStackKey());
        System.out.println(getUrl);
        URL url = new URL(getUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    private String getIpStackKey() throws IOException {
        return getConfigPropValueFromField("key");
    }
}
