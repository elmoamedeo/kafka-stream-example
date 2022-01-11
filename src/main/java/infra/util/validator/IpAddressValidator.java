package infra.util.validator;

import java.util.regex.Pattern;

public class IpAddressValidator {

    private static final String IPV4_REGEX =
            "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static boolean isValidIpAddress(final String ipAddress) {
        if (ipAddress == null)
            return false;

        if (!IPV4_PATTERN.matcher(ipAddress).matches())
            return false;

        String[] parts = ipAddress.split("\\.");

        try {
            for (String segment: parts)
            {
                if (Integer.parseInt(segment) > 255 ||
                        (segment.length() > 1 && segment.startsWith("0"))) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
