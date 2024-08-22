package work.nicey.system.service;

import java.util.List;
import work.nicey.system.domain.ApiInterfaceInfo;

/**
 * 接口信息Service接口
 * 
 * @author nicey
 * @date 2024-08-22
 */
public interface IApiInterfaceInfoService 
{
    /**
     * 查询接口信息
     * 
     * @param id 接口信息主键
     * @return 接口信息
     */
    public ApiInterfaceInfo selectApiInterfaceInfoById(Long id);

    /**
     * 查询接口信息列表
     * 
     * @param apiInterfaceInfo 接口信息
     * @return 接口信息集合
     */
    public List<ApiInterfaceInfo> selectApiInterfaceInfoList(ApiInterfaceInfo apiInterfaceInfo);

    /**
     * 新增接口信息
     * 
     * @param apiInterfaceInfo 接口信息
     * @return 结果
     */
    public int insertApiInterfaceInfo(ApiInterfaceInfo apiInterfaceInfo);

    /**
     * 修改接口信息
     * 
     * @param apiInterfaceInfo 接口信息
     * @return 结果
     */
    public int updateApiInterfaceInfo(ApiInterfaceInfo apiInterfaceInfo);

    /**
     * 批量删除接口信息
     * 
     * @param ids 需要删除的接口信息主键集合
     * @return 结果
     */
    public int deleteApiInterfaceInfoByIds(Long[] ids);

    /**
     * 删除接口信息信息
     * 
     * @param id 接口信息主键
     * @return 结果
     */
    public int deleteApiInterfaceInfoById(Long id);
}
