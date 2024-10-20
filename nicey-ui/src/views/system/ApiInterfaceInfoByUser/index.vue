<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="请求类型" prop="method">
        <el-input
          v-model="queryParams.method"
          placeholder="请输入请求类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ApiInterfaceInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="描述" align="center" prop="description" />
<!--      <el-table-column label="接口地址" align="center" prop="url" />-->
<!--      <el-table-column label="请求头" align="center" prop="requestHeader" />-->
<!--      <el-table-column label="响应头" align="center" prop="responseHeader" />-->
      <el-table-column label="请求类型" align="center" prop="method" />
      <el-table-column label="接口状态" align="center" prop="status" >
        <template slot-scope="scope">
          <svg-icon v-if="scope.row.status === 1" icon-class="online" style="font-size: 30px" class-name='custom-class' />
          <svg-icon v-if="scope.row.status === 0" icon-class="offline" style="font-size: 30px" class-name='custom-class' />
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="toApiInterfaceInfoByUserDetail(scope.row)"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listApiInterfaceInfo, getApiInterfaceInfo, delApiInterfaceInfo, addApiInterfaceInfo, updateApiInterfaceInfo } from "@/api/system/ApiInterfaceInfo";

export default {
  name: "ApiInterfaceInfoByUser",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 接口信息表格数据
      ApiInterfaceInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        description: null,
        url: null,
        requestHeader: null,
        responseHeader: null,
        status: null,
        method: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        url: [
          { required: true, message: "接口地址不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "接口状态不能为空", trigger: "change" }
        ],
        method: [
          { required: true, message: "请求类型不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    toApiInterfaceInfoByUserDetail(row) {
      const interfaceId = row.id;
      this.$router.push("/ApiInterfaceInfoByUser/detail/" + interfaceId);
    },
    /** 查询接口信息列表 */
    getList() {
      this.loading = true;
      listApiInterfaceInfo(this.queryParams).then(response => {
        this.ApiInterfaceInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        description: null,
        url: null,
        requestHeader: null,
        responseHeader: null,
        status: null,
        method: null,
        userId: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加接口信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApiInterfaceInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改接口信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApiInterfaceInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.userId = this.$store.state.user.id;
            addApiInterfaceInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除接口信息编号为"' + ids + '"的数据项？').then(function() {
        return delApiInterfaceInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/ApiInterfaceInfo/export', {
        ...this.queryParams
      }, `ApiInterfaceInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
