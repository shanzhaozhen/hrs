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
@TableName("hr_driver_license")
@Schema(description = "驾驶证信息DO实体")
public class DriverLicenseDO extends BaseInfo {

    private static final long serialVersionUID = -5097483736246726659L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联ID")
    private Long pid;

    @Schema(title = "准驾车型")
    private String modal;

    @Schema(title = "证件号码")
    private String number;

    @Schema(title = "获得日期")
    private Date obtainDate;

    @Schema(title = "有效期至")
    private Date expirationDate;

    @Schema(title = "内部驾照")
    private String inside;

    @Schema(title = "内部驾照有效期")
    private Date insideExpirationDate;

    @Schema(title = "附件")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
