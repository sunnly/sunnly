package wang.sunnly.modules.admin.domain;

import lombok.Data;

/**
 * Rules
 *
 * @author Sunnly
 * @since 2020/12/23
 */
@Data
public class DataRules {

    private String id;
    private String rule;
    private boolean permit;

}
