package wang.sunnly.modules.admin.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "base_resources_role")
public class ResourcesRole {
    /**
     * 角色资源关联ID
     */
    @Id
    @Column(name = "res_role_id")
    private Long resRoleId;

    /**
     * 资源ID
     */
    @Column(name = "res_id")
    private Long resId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;
}