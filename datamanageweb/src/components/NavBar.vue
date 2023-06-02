<template>
  <nav class="navbar navbar-expand-lg" data-bs-theme="dark" style="background-color: #0066CA; margin: 0; padding: 2px;">
    <div class="container-fluid align-items-center">
      <router-link :to="{name: 'home'}" class="navbar-brand white-font d-flex" style="margin-left: 40vh; margin-right: 80vh; font-size: 18px; font-family: sans-serif; font-weight: bold;">
        <img src="../assets/images/logo.png" alt="Logo" width="32" height="32" class="d-inline-block" style="margin-right: 10px">
        野鸡大学教务管理系统
      </router-link>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0" v-if="$store.state.user.is_login">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle white-font" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="../assets/images/user.png" alt="Logo" width="26" height="26" style="margin-right: 3px">
            {{ $store.state.user.name }}
          </a>
          <ul class="dropdown-menu">
<!--            <li><router-link :to="{name: '404'}" class="dropdown-item">个人中心</router-link></li>-->
<!--            <li><router-link :to="{name: '404'}" class="dropdown-item">重置选课密码</router-link></li>-->
            <li><hr class="dropdown-divider"></li>
            <li><a @click="logout" class="dropdown-item" href="#">退出</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { useStore } from "vuex";
import { useMessage } from "naive-ui";
import router from "@/router";

export default {
  setup() {
    const store = useStore();
    const message = useMessage();

    const logout = () => {
      store.dispatch("logout", {
        success() {
          message.success("退出登录成功！");
          setTimeout(() => {
            router.push({name: 'login'});
          }, 800);
        }
      });
    }

    return {
      logout,
    }
  }
}
</script>

<style scoped>
.white-font {
  color: white;
}
</style>