package work.nicey.system.service.impl;

import java.util.List;
import work.nicey.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.nicey.system.mapper.ApiInterfaceInfoMapper;
import work.nicey.system.domain.ApiInterfaceInfo;
import work.nicey.system.service.IApiInterfaceInfoService;

/**
 * 接口信息Service业务层处理
 * 
 * @author nicey
 * @date 2024-08-22
 */
@Service
public class ApiInterfaceInfoServiceImpl implements IApiInterfaceInfoService 
{
    @Autowired
    private ApiInterfaceInfoMapper apiInterfaceInfoMapper;

    /**
     * 查询接口信息
     * 
     * @param id 接口信息主键
     * @return 接口信息
     */
    @Override
    public ApiInterfaceInfo selectApiInterfaceInfoById(Long id)
    {
        return apiInterfaceInfoMapper.selectApiInterfaceInfoById(id);
    }

    /**
     * 查询接口信息列表
     * 
     * @param apiInterfaceInfo 接口信息
     * @return 接口信息
     */
    @Override
    public List<ApiInterfaceInfo> selectApiInterfaceInfoList(ApiInterfaceInfo apiInterfaceInfo)
    {
        return apiInterfaceInfoMapper.selectApiInterfaceInfoList(apiInterfaceInfo);
    }

    /**
     * 新增接口信息
     * 
     * @param apiInterfaceInfo 接口信息
     * @return 结果
     */
    @Override
    public int insertApiInterfaceInfo(ApiInterfaceInfo apiInterfaceInfo)
    {
        apiInterfaceInfo.setCreateTime(DateUtils.getNowDate());
        return apiInterfaceInfoMapper.insertApiInterfaceInfo(apiInterfaceInfo);
    }

    /**
     * 修改接口信息
     * 
     * @param apiInterfaceInfo 接口信息
     * @return 结果
     */
    @Override
    public int updateApiInterfaceInfo(ApiInterfaceInfo apiInterfaceInfo)
    {
        apiInterfaceInfo.setUpdateTime(DateUtils.getNowDate());
        return apiInterfaceInfoMapper.updateApiInterfaceInfo(apiInterfaceInfo);
    }

    /**
     * 批量删除接口信息
     * 
     * @param ids 需要删除的接口信息主键
     * @return 结果
     */
    @Override
    public int deleteApiInterfaceInfoByIds(Long[] ids)
    {
        return apiInterfaceInfoMapper.deleteApiInterfaceInfoByIds(ids);
    }

    /**
     * 删除接口信息信息
     * 
     * @param id 接口信息主键
     * @return 结果
     */
    @Override
    public int deleteApiInterfaceInfoById(Long id)
    {
        return apiInterfaceInfoMapper.deleteApiInterfaceInfoById(id);
    }
}
