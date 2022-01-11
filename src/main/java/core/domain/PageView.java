package core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
@AllArgsConstructor
public class PageView {

    String user;
    String page;
    String ipAddress;

}
