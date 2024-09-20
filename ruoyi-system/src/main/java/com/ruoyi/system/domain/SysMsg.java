package com.ruoyi.system.domain;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * sys_msg对象 sys_msg
 * 
 * @author ruoyi
 * @date 2024-09-20
 */
@Schema(description = "sys_msg对象")
public class SysMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** $column.columnComment */
    @Schema(title = "$column.columnComment")
    private Long id;

    /** $column.columnComment */
    @Schema(title = "$column.columnComment")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String msg;

    /** $column.columnComment */
    @Schema(title = "$column.columnComment")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private LocalDate date;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }


    public void setMsg(String msg) 
    {
        this.msg = msg;
    }

    public String getMsg() 
    {
        return msg;
    }


    public void setDate(LocalDate date) 
    {
        this.date = date;
    }

    public LocalDate getDate() 
    {
        return date;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("msg", getMsg())
            .append("date", getDate())
            .toString();
    }
}
