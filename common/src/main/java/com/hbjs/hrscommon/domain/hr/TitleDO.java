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
@TableName("hr_title")
@Schema(description = "职称信息DO实体")
public class TitleDO extends BaseInfo {

    private static final long serialVersionUID = -4556139940420769208L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联ID")
    private Long staffId;

    @Schema(title = "职称")
    private String title;

    @Schema(title = "职称等级")
    private String level;

    @Schema(title = "证书编号")
    private String number;

    @Schema(title = "评定日期")
    private Date evaluationDate;

    @Schema(title = "终止日期")
    private Date endDate;

    @Schema(title = "评定机构")
    private String issueCompany;

    @Schema(title = "是否最高")
    private String highest;

    @Schema(title = "附件")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
