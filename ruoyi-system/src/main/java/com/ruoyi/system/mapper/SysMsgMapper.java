package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysMsg;

/**
 * sys_msgMapper接口
 * 
 * @author ruoyi
 * @date 2024-09-20
 */
public interface SysMsgMapper 
{
    /**
     * 查询sys_msg
     * 
     * @param id sys_msg主键
     * @return sys_msg
     */
    public SysMsg selectSysMsgById(Long id);

    /**
     * 查询sys_msg列表
     * 
     * @param sysMsg sys_msg
     * @return sys_msg集合
     */
    public List<SysMsg> selectSysMsgList(SysMsg sysMsg);

    /**
     * 新增sys_msg
     * 
     * @param sysMsg sys_msg
     * @return 结果
     */
    public int insertSysMsg(SysMsg sysMsg);

    /**
     * 修改sys_msg
     * 
     * @param sysMsg sys_msg
     * @return 结果
     */
    public int updateSysMsg(SysMsg sysMsg);

    /**
     * 删除sys_msg
     * 
     * @param id sys_msg主键
     * @return 结果
     */
    public int deleteSysMsgById(Long id);

    /**
     * 批量删除sys_msg
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMsgByIds(Long[] ids);
}
