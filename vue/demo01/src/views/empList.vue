<script setup>
import request from "@/utils/request";
</script>

<template>
  <div style="width:70%;margin:auto">
    <el-button @click="handleAdd()" type="primary">新增</el-button>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="empno" label="员工编号" />
      <el-table-column prop="ename" label="员工名称" />
      <el-table-column prop="bossName" label="boss" />
      <el-table-column prop="hiredate" label="入职时间" />
      <el-table-column prop="job" label="职位" />
      <el-table-column prop="sal" label="员工薪资" />
      <el-table-column prop="cOMM" label="员工奖金" />
      <el-table-column prop="dname" label="所属部门" />
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)">修改</el-button>
          <el-popconfirm @confirm="handleDelete(scope.row.empno)" title="确认删除?">
            <template #reference>
              <el-button type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination v-model:current-page="currentPage" :page-size="pageSize" :page-sizes="[5,15, 20, 25]"
      layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
      @current-change="handleCurrentChange" />


    <el-dialog v-model="dialogTableVisible" width="470px" title="修改">
      <el-form :model="form" label-width="120px" style="max-width: 360px">
        <el-form-item label="员工名称">
          <el-input v-model="form.ename" />
        </el-form-item>
        <el-form-item label="员工岗位">
          <el-select v-model="form.job" class="m-2" placeholder="选择岗位">
            <el-option v-for="item in jobList" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="员工上司">
          <el-select v-model="form.mgr" class="m-2" placeholder="选择上司">
            <el-option v-for="item in mgrList" :key="item.empno" :label="item.ename" :value="item.empno" />
          </el-select>
        </el-form-item>
        <el-form-item label="入职时间">
          <el-date-picker v-model="form.hiredate" align="center" type="date" format="YYYY-MM-DD" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="员工工资">
          <el-input-number v-model="form.sal" :precision="2" :step="0.1" :max="9999999" />
        </el-form-item>
        <el-form-item label="员工奖金">
          <el-input-number v-model="form.cOMM" :precision="2" :step="0.1" :max="9999999" />
        </el-form-item>
        <el-form-item label="所属部门">
          <el-select v-model="form.deptno" class="m-2" placeholder="选择部门">
            <el-option v-for="item in deptList" :key="item.deptno" :label="item.dname" :value="item.deptno" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogTableVisible = false">返回</el-button>
          <el-button type="primary" @click="edit()">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'empList',
  components: {
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 15,
      total: 0,

      dialogTableVisible: false,
      mgrList: [],
      jobList: [],
      deptList: [],
      form: {

      }
    }
  },
  created() {
    this.load()
    this.load1()
  },
  mounted() {

  },
  methods: {
    load() {
      request.get("/ajaxtest/emp?method=selectPage", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
      }).then((res) => {
        if (res.code == "0") {
          this.tableData = res.data.records
          this.total = res.data.total
        }
      });
      
    },
    load1(){
      request.get("/ajaxtest/emp?method=getMgrList").then((res) => {
        this.mgrList = res.data
      });
      request.get("/ajaxtest/emp?method=getJobList").then((res) => {
        this.jobList = res.data
      });
      request.get("/ajaxtest/dept?method=getDeptList").then((res) => {
        this.deptList = res.data
      });
    },
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(currentPage) {  // 改变当前页码触发
      this.currentPage = currentPage
      this.load()
    },

    handleAdd() {
      this.dialogTableVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.dialogTableVisible = true
      this.form = row
    },
    edit() {
      let that = this
      if (this.form.empno) {
        request.put("/ajaxtest/emp?method=update", this.form).then((res) => {
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "修改成功",
          });
          that.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
      }else{
        request.put("/ajaxtest/emp?method=add", this.form).then((res) => {
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "新增成功",
            });
            that.load()
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
      }
      this.dialogTableVisible = false
      this.form = {}
    },

    handleDelete(empno) {
      request.delete("/ajaxtest/emp?method=delete&empno=" + empno).then((res) => {
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "删除成功",
          });
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
    }

  },

}
</script>
<style scoped></style>