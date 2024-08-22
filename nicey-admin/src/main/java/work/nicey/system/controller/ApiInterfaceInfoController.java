package work.nicey.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import work.nicey.common.annotation.Log;
import work.nicey.common.core.controller.BaseController;
import work.nicey.common.core.domain.AjaxResult;
import work.nicey.common.enums.BusinessType;
import work.nicey.system.domain.ApiInterfaceInfo;
import work.nicey.system.service.IApiInterfaceInfoService;
import work.nicey.common.utils.poi.ExcelUtil;
import work.nicey.common.core.page.TableDataInfo;

/**
 * 接口信息Controller
 * 
 * @author nicey
 * @date 2024-08-22
 */
@RestController
@RequestMapping("/system/ApiInterfaceInfo")
public class ApiInterfaceInfoController extends BaseController
{
    @Autowired
    private IApiInterfaceInfoService apiInterfaceInfoService;

    /**
     * 查询接口信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:ApiInterfaceInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApiInterfaceInfo apiInterfaceInfo)
    {
        startPage();
        List<ApiInterfaceInfo> list = apiInterfaceInfoService.selectApiInterfaceInfoList(apiInterfaceInfo);
        return getDataTable(list);
    }

    /**
     * 导出接口信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:ApiInterfaceInfo:export')")
    @Log(title = "接口信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiInterfaceInfo apiInterfaceInfo)
    {
        List<ApiInterfaceInfo> list = apiInterfaceInfoService.selectApiInterfaceInfoList(apiInterfaceInfo);
        ExcelUtil<ApiInterfaceInfo> util = new ExcelUtil<ApiInterfaceInfo>(ApiInterfaceInfo.class);
        util.exportExcel(response, list, "接口信息数据");
    }

    /**
     * 获取接口信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ApiInterfaceInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(apiInterfaceInfoService.selectApiInterfaceInfoById(id));
    }

    /**
     * 新增接口信息
     */
    @PreAuthorize("@ss.hasPermi('system:ApiInterfaceInfo:add')")
    @Log(title = "接口信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApiInterfaceInfo apiInterfaceInfo)
    {
        return toAjax(apiInterfaceInfoService.insertApiInterfaceInfo(apiInterfaceInfo));
    }

    /**
     * 修改接口信息
     */
    @PreAuthorize("@ss.hasPermi('system:ApiInterfaceInfo:edit')")
    @Log(title = "接口信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApiInterfaceInfo apiInterfaceInfo)
    {
        return toAjax(apiInterfaceInfoService.updateApiInterfaceInfo(apiInterfaceInfo));
    }

    /**
     * 删除接口信息
     */
    @PreAuthorize("@ss.hasPermi('system:ApiInterfaceInfo:remove')")
    @Log(title = "接口信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(apiInterfaceInfoService.deleteApiInterfaceInfoByIds(ids));
    }
}
