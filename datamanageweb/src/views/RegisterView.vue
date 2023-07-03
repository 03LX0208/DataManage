<template>
  <div class="all">
    <NavBar/>
    <section class="h-100">
      <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
          <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
            <div class="text-center my-5">
              <img src="../assets/images/logo.png" style="color: #0066CA" alt="logo">
            </div>
            <div class="card shadow-lg">
              <div class="card-body p-5">
                <h1 class="fs-4 card-title fw-bold mb-4">注册</h1>
                <form onsubmit="event.preventDefault();" class="needs-validation" novalidate="" autocomplete="off">
                  <div class="mb-3">
                    <label class="mb-2 text-muted" for="name">教学号</label>
                    <input v-model="username" id="name" type="text" class="form-control" required autofocus>
                  </div>

                  <div class="mb-3">
                    <label class="mb-2 text-muted" for="password">密码</label>
                    <input v-model="password" type="password" class="form-control"  required>
                  </div>

                  <div class="mb-3">
                    <label class="mb-2 text-muted" for="password">确认密码</label>
                    <input v-model="confirmPassword" id="password" type="password" class="form-control" name="password" required>
                  </div>

                  <div class="align-items-center d-flex">
                    <button  class="btn btn-primary ms-auto" @click="register">
                      注册
                    </button>
                  </div>
                </form>
              </div>
              <div class="card-footer py-3 border-0">
                <div class="text-center">
                  已有账户?
                  <router-link :to="{name: 'login'}" class="text-dark">登录</router-link>
                </div>
              </div>
            </div>
            <div class="text-center mt-5 text-muted">
              Copyright &copy; 2023-2023 &mdash; 野鸡大学
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import $ from 'jquery';
import { ref } from 'vue';
import { useMessage } from 'naive-ui';
import router from "@/router";

export default {
  components: {
    NavBar,
  },
  setup() {
    let username = ref(''); // 用户名
    let password = ref(''); // 密码
    let confirmPassword = ref('');  // 确认密码
    const message = useMessage(); // 弹窗信息

    // 注册事件
    const register = () => {
      $.ajax({
        url: "https://data.lxcode.xyz/api/user/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmPassword: confirmPassword.value
        },
        success(resp) {
          if (resp.error_message === "success") { // 成功就跳转到登录页面
            message.success("注册成功，即将跳转到登录..", {
              duration: 2000
            });
            setTimeout(()=> {
              router.push({
                name: 'login'
              });
            }, 2000);
          } else {  // 错误 提示用户信息
            message.error(resp.error_message);
          }
        }
      })
    };

    return {
      register,
      username,
      password,
      confirmPassword,
    }
  }
}
</script>

<style scoped>
.all {
  min-height: 100vh;
  background-color: rgb(241,241,243);
}
</style>