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
@Schema(description = "职称DO实体")
public class TitleDO extends BaseInfo {

    private static final long serialVersionUID = -4556139940420769208L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id（关联职工表）")
    private Long pid;

    @Schema(title = "职称名称")
    private String titleName;

    @Schema(title = "职称证证号")
    private String titleNumber;

    @Schema(title = "职称取证日期")
    private Date obtainDate;

    @Schema(title = "职称证发证单位")
    private String issueUnit;


}
