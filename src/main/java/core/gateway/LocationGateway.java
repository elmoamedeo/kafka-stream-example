package core.gateway;

import java.io.IOException;

public interface LocationGateway {

    String checkLocation(String ipAddress) throws IOException;
}
