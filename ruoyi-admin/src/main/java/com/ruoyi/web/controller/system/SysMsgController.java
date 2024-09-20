package com.ruoyi.web.controller.system;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysMsg;
import com.ruoyi.system.service.ISysMsgService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

/**
 * sys_msgController
 *
 * @author ruoyi
 * @date 2024-09-20
 */
@RestController
@RequestMapping("/system/msg")
@Tag(name = "【sys_msg】管理")
public class SysMsgController extends BaseController
{
    @Autowired
    private ISysMsgService sysMsgService;

    /**
     * 查询sys_msg列表
     */
    @Operation(summary = "查询sys_msg列表")
    @PreAuthorize("@ss.hasPermi('system:msg:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMsg sysMsg)
    {
        startPage();
        List<SysMsg> list = sysMsgService.selectSysMsgList(sysMsg);
        return getDataTable(list);
    }

    /**
     * 导出sys_msg列表
     */
    @Operation(summary = "导出sys_msg列表")
    @PreAuthorize("@ss.hasPermi('system:msg:export')")
    @Log(title = "sys_msg", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMsg sysMsg)
    {
        List<SysMsg> list = sysMsgService.selectSysMsgList(sysMsg);
        ExcelUtil<SysMsg> util = new ExcelUtil<SysMsg>(SysMsg.class);
        util.exportExcel(response, list, "sys_msg数据");
    }

    /**
     * 获取sys_msg详细信息
     */
    @Operation(summary = "获取sys_msg详细信息")
    @PreAuthorize("@ss.hasPermi('system:msg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysMsgService.selectSysMsgById(id));
    }

    /**
     * 新增sys_msg
     */
    @Operation(summary = "新增sys_msg")
    @PreAuthorize("@ss.hasPermi('system:msg:add')")
    @Log(title = "sys_msg", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMsg sysMsg)
    {
        return toAjax(sysMsgService.insertSysMsg(sysMsg));
    }

    /**
     * 修改sys_msg
     */
    @Operation(summary = "修改sys_msg")
    @PreAuthorize("@ss.hasPermi('system:msg:edit')")
    @Log(title = "sys_msg", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMsg sysMsg)
    {
        return toAjax(sysMsgService.updateSysMsg(sysMsg));
    }

    /**
     * 删除sys_msg
     */
    @Operation(summary = "删除sys_msg")
    @PreAuthorize("@ss.hasPermi('system:msg:remove')")
    @Log(title = "sys_msg", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable( name = "ids" ) Long[] ids)
    {
        return toAjax(sysMsgService.deleteSysMsgByIds(ids));
    }
}
