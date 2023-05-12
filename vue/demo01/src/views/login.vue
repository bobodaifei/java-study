<script setup>
import request from "@/utils/request";
</script>
<template>
  <div>
    <el-form :model="form" label-width="120px">
      <el-form-item label="账号">
        <el-input v-model="form.ename" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.empno" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login()">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'login',
  components: {
  },
  data() {
    return {
      form: {},

    }
  },
  created() {
    
  },
  mounted() {
  },
  methods: {
    login() {
      request.post("/ajaxtest/emp?method=login", this.form).then((res) => {
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "登录成功",
          });
          localStorage.setItem('token', res.data.token);
          this.$router.push('/empList')
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