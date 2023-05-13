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
                <h1 class="fs-4 card-title fw-bold mb-4">登录</h1>
                <form onsubmit="event.preventDefault();" class="needs-validation" novalidate="" autocomplete="off">
                  <div class="mb-3">
                    <label class="mb-2 text-muted" for="email">教学号</label>
                    <input v-model="username" class="form-control" name="email"  required autofocus>
                  </div>

                  <div class="mb-3">
                    <div class="mb-2 w-100">
                      <label class="text-muted" for="password">密码</label>
                    </div>
                    <input v-model="password" id="password" type="password" class="form-control" name="password" required>
                  </div>

                  <div class="d-flex align-items-center">
                    <button @click="login" class="btn btn-primary ms-auto">
                      登录
                    </button>
                  </div>
                </form>
              </div>
              <div class="card-footer py-3 border-0">
                <div class="text-center">
                  没有账户？
                  <router-link :to="{name: 'register'}" class="text-dark">注册</router-link>
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
import {ref} from 'vue';
import {useMessage} from "naive-ui";
import router from "@/router";
import { useStore } from 'vuex';

export default {
  components: {
    NavBar,
  },
  setup() {
    let username = ref(''); // 用户名
    let password = ref(''); // 密码
    const message = useMessage();
    const store = useStore(); // 调用全局更新

    // 按钮触发登录事件
    const login = () => {
      store.dispatch("login", {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch("getInfo");
          message.success("登录成功！", {
            duration: 1200
          });
          setTimeout(()=> {
            router.push({name: 'home'});
          }, 1000);
        },
        error() {
          message.error("账号或密码有误！");
        }
      });
    }

    return {
      username,
      password,
      login,
    }
  }
}
</script>

<style scoped>
.all {
  min-height: 100vh;
  background-color: lightblue;
}
</style>