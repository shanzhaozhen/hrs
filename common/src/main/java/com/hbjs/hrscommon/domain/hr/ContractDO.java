package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_contract")
@Schema(description = "家庭成员DO实体")
public class ContractDO extends BaseInfo {

    private static final long serialVersionUID = 8068127469741268793L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id")
    private Long pid;

    @Schema(title = "合同名称")
    private String name;

    @Schema(title = "合同编号")
    private String number;

    @Schema(title = "合同类型")
    private String type;

    @Schema(title = "附件ID")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
