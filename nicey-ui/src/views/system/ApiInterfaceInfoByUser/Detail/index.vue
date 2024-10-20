<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header">
        <span>{{ interfaceInfo.name }}</span>
        <!-- TODO在线测试功能 -->
        <el-button style="float: right; padding: 3px 0" type="text">测试</el-button>
      </div>
      <div class="text item">
        接口地址：{{ interfaceInfo.url }}
      </div>
      <div class="text item">
        描述：{{ interfaceInfo.description }}
      </div>
      <div class="text item">
        请求头：{{ interfaceInfo.requestHeader }}
      </div>
      <div class="text item">
        响应头：{{ interfaceInfo.responseHeader }}
      </div>
      <div class="text item">
        请求类型：{{ interfaceInfo.method }}
      </div>
      <div class="text item">
        接口状态：{{ interfaceInfo.status === 1 ? '正常使用' : '维护中' }}
      </div>
      <div class="text item">
        创建时间：<time class="time">{{ interfaceInfo.createTime }}</time>
      </div>
      <div class="text item">
        更新时间：<time class="time">{{ interfaceInfo.updateTime }}</time>
      </div>

    </el-card>
  </div>
</template>

<script>
import { getApiInterfaceInfo } from "@/api/system/ApiInterfaceInfo";

export default {
  name: "ApiInterfaceInfoByUserDetail",
  data() {
    return {
      interfaceInfo: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询接口信息列表 */
    getList() {
      getApiInterfaceInfo(this.$route.params.id).then(response => {
        this.interfaceInfo = response.data;
        console.log(this.interfaceInfo)
      });
    },
  }
};
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.time {
  font-size: 13px;
  color: #999;
}
</style>
