package work.nicey.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import work.nicey.common.annotation.Excel;
import work.nicey.common.core.domain.BaseEntity;

/**
 * 接口信息对象 api_interface_info
 * 
 * @author nicey
 * @date 2024-08-22
 */
public class ApiInterfaceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 接口地址 */
    @Excel(name = "接口地址")
    private String url;

    /** 请求头 */
    @Excel(name = "请求头")
    private String requestHeader;

    /** 响应头 */
    @Excel(name = "响应头")
    private String responseHeader;

    /** 接口状态（0-关闭，1-开启） */
    @Excel(name = "接口状态", readConverterExp = "0=-关闭，1-开启")
    private Long status;

    /** 请求类型 */
    @Excel(name = "请求类型")
    private String method;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setRequestHeader(String requestHeader) 
    {
        this.requestHeader = requestHeader;
    }

    public String getRequestHeader() 
    {
        return requestHeader;
    }
    public void setResponseHeader(String responseHeader) 
    {
        this.responseHeader = responseHeader;
    }

    public String getResponseHeader() 
    {
        return responseHeader;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("url", getUrl())
            .append("requestHeader", getRequestHeader())
            .append("responseHeader", getResponseHeader())
            .append("status", getStatus())
            .append("method", getMethod())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
