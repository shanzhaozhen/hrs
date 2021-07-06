package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "家庭成员DO实体")
public class FamilyDTO extends BaseInfo {

    private static final long serialVersionUID = 5927571360955765778L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "关联ID")
    private Long pid;

    @Schema(title = "姓名")
    private String name;

    @Schema(title = "关系")
    private String relation;

    @Schema(title = "出生日期")
    private Date birthday;

    @Schema(title = "政治面貌")
    private String politics;

    @Schema(title = "工作单位")
    private String workUnit;

    @Schema(title = "职务")
    private String duty;

    @Schema(title = "移动电话")
    private String mobilePhone;

    @Schema(title = "固话")
    private String landlinePhone;

}
