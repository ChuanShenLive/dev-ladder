<template>
  <div>
    <div style="display: flex; justify-content: space-between">
      <div>
        <el-input placeholder="请输入员工名进行搜索, 可以直接按回车搜索..." prefix-icon="el-icon-search" size="mini"
                  style="width: 300px; margin-right: 8px"></el-input>
        <el-button icon="el-icon-search" type="primary" size="mini">搜索</el-button>
        <el-button type="primary" size="mini"><i class="fa fa-angle-double-down" aria-hidden="true">高级搜索</i></el-button>
      </div>
      <div>
        <el-button size="mini" type="success"><i class="fa fa-level-up" aria-hidden="true">导入数据</i></el-button>
        <el-button size="mini" type="success"><i class="fa fa-level-down" aria-hidden="true">导出数据</i></el-button>
        <el-button type="primary" size="mini" icon="el-icon-plus">添加用户</el-button>
      </div>
    </div>
    <div style="margin-top: 8px;">
      <el-table :data="emps" stripe border style="width: 100%" borderv-loading="londing" element-loading-text="正在加载..." elelemt-loading-spinner="el-icon-loading" element-loading-background="rgba(0,0,0,0.8">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="name" label="姓名" width="100" fixed align="left"></el-table-column>
        <el-table-column prop="workID" label="工号" align="left" width="85"></el-table-column>
        <el-table-column prop="birthday" label="出生日期" align="left" width="95"></el-table-column>
        <el-table-column prop="idCard" label="身份证号" align="left" width="150"></el-table-column>
        <el-table-column prop="wedlock" label="婚姻状况" width="70"></el-table-column>
        <el-table-column prop="nation.name" label="婚姻状况" width="50"></el-table-column>
        <el-table-column prop="nativePlace" label="籍贯" width="80"></el-table-column>
        <el-table-column prop="politicsStatus.name" label="政治面貌"></el-table-column>
        <el-table-column prop="email" width="180" align="left" label="电子邮件"></el-table-column>
        <el-table-column prop="phone" width="100" align="left" label="电话号码"></el-table-column>
        <el-table-column prop="address" width="220" align="left" label="联系地址"></el-table-column>
        <el-table-column prop="department.name" width="100" align="left" label="所属部门"></el-table-column>
        <el-table-column prop="position.name" width="100" label="职位"></el-table-column>
        <el-table-column prop="jobLevel.name" width="100" label="职称"></el-table-column>
        <el-table-column prop="engageForm" width="100" align="left" label="聘用形式"></el-table-column>
        <el-table-column prop="beginDate" width="95" align="left" label="入职日期"></el-table-column>
        <el-table-column prop="conversionTime" width="95" align="left" label="转正日期"></el-table-column>
        <el-table-column prop="beginContract" width="95" align="left" label="合同起始日期"></el-table-column>
        <el-table-column prop="endContract" width="95" align="left" label="合同终止日期"></el-table-column>
        <el-table-column width="100" align="left" label="合同期限">
          <template slot-scope="scope"><el-tag>{{scope.row.contractTerm}}</el-tag>年</template>
        </el-table-column>
        <el-table-column prop="tiptopDegree" label="最高学历"></el-table-column>
        <el-table-column label="操作" fixed="right" width="200">
          <template slot-scope="scope">
            <el-button style="padding: 3px" size="mini">编辑</el-button>
            <el-button style="padding: 3px" size="mini" type="primary">查看高级资料</el-button>
            <el-button style="padding: 3px" size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "EmpBasic",
  data() {
    return {
      emps: [],
      total: 0,
      londing: false,
    }
  },
  mounted() {
    this.initEmps();
  },
  methods: {
    initEmps() {
      this.londing = true
      this.getRequest("/emp/basic/").then(resp=> {
        this.londing = false;
        if(resp) {
          this.emps = resp.data;
          this.total = resp.total;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
