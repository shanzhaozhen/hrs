package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "工作履历DO实体")
public class WorkExperienceDTO extends BaseInfo {

    private static final long serialVersionUID = 8718486404924647421L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "关联id")
    private Long pid;

    @Schema(title = "工作单位")
    private String workCompany;

    @Schema(title = "开始时间")
    private Date startDate;

    @Schema(title = "结束时间")
    private Date endDate;

    @Schema(title = "部门")
    private String department;

    @Schema(title = "职务/岗位")
    private String duty;

    @Schema(title = "单位性质")
    private String companyType;

    @Schema(title = "月薪")
    private BigDecimal salary;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

    @Schema(title = "备注")
    private String remarks;

}
