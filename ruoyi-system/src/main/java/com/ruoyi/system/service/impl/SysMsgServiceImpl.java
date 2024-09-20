package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMsgMapper;
import com.ruoyi.system.domain.SysMsg;
import com.ruoyi.system.service.ISysMsgService;

/**
 * sys_msgService业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-20
 */
@Service
public class SysMsgServiceImpl implements ISysMsgService 
{
    @Autowired
    private SysMsgMapper sysMsgMapper;

    /**
     * 查询sys_msg
     * 
     * @param id sys_msg主键
     * @return sys_msg
     */
    @Override
    public SysMsg selectSysMsgById(Long id)
    {
        return sysMsgMapper.selectSysMsgById(id);
    }

    /**
     * 查询sys_msg列表
     * 
     * @param sysMsg sys_msg
     * @return sys_msg
     */
    @Override
    public List<SysMsg> selectSysMsgList(SysMsg sysMsg)
    {
        return sysMsgMapper.selectSysMsgList(sysMsg);
    }

    /**
     * 新增sys_msg
     * 
     * @param sysMsg sys_msg
     * @return 结果
     */
    @Override
    public int insertSysMsg(SysMsg sysMsg)
    {
        return sysMsgMapper.insertSysMsg(sysMsg);
    }

    /**
     * 修改sys_msg
     * 
     * @param sysMsg sys_msg
     * @return 结果
     */
    @Override
    public int updateSysMsg(SysMsg sysMsg)
    {
        return sysMsgMapper.updateSysMsg(sysMsg);
    }

    /**
     * 批量删除sys_msg
     * 
     * @param ids 需要删除的sys_msg主键
     * @return 结果
     */
    @Override
    public int deleteSysMsgByIds(Long[] ids)
    {
        return sysMsgMapper.deleteSysMsgByIds(ids);
    }

    /**
     * 删除sys_msg信息
     * 
     * @param id sys_msg主键
     * @return 结果
     */
    @Override
    public int deleteSysMsgById(Long id)
    {
        return sysMsgMapper.deleteSysMsgById(id);
    }
}
