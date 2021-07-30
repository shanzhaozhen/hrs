package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_contract")
@Schema(description = "合同信息DO实体")
public class ContractDO extends BaseInfo {

    private static final long serialVersionUID = 8068127469741268793L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id")
    private Long staffId;

    @Schema(title = "合同名称")
    private String name;

    @Schema(title = "合同编号")
    private String number;

    @Schema(title = "业务类型")
    private String type;

    @Schema(title = "业务发生日期")
    private Date occurrenceDate;

    @Schema(title = "合同期限类型")
    private String periodType;

    @Schema(title = "合同期限")
    private Integer period;

    @Schema(title = "合同期限单位")
    private String periodUnit;

    @Schema(title = "合同开始日期")
    private Date startDate;

    @Schema(title = "合同结束日期")
    private Date endDate;

    @Schema(title = "是否需要试用")
    private String hasProbation;

    @Schema(title = "试用期限")
    private Integer probationTerm;

    @Schema(title = "试用期限单位")
    private Integer probationTermUnit;

    @Schema(title = "试用开始日期")
    private Date probationStartDate;

    @Schema(title = "试用结束日期")
    private Date probationEndDate;

    @Schema(title = "合同主体单位")
    private String company;

    @Schema(title = "业务发生组织")
    private String organization;

    @Schema(title = "附件ID")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
