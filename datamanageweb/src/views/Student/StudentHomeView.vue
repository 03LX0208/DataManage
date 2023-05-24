<template>
  <div class="all">
    <NavBar/>
    <div class="container">
      <div class="row">
        <div class="col"></div>
        <div class="col-8">
          <n-card
              title="⭐ 常用功能"
              embedded
              :bordered="false"
              style="margin-top: 20px; margin-bottom: 20px;"
              hoverable
          >
           <n-space>
             <n-button strong secondary round type="info" size="large" @click="pushToSelect">
               选课系统
             </n-button>
           </n-space>
          </n-card>
        </div>
        <div class="col"></div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import {NCard, NSpace, NButton } from 'naive-ui';

const pushToSelect = () => {
  window.open("/student/select-course/", '_blank');
}

export default {
  components: {
    NavBar,
    NCard,
    NSpace,
    NButton,
  },
  setup() {
    return {
      pushToSelect,
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