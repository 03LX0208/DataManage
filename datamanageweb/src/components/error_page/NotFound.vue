<template>
  <div class="all">
    <NavBar/>
    <n-result style="margin-top: 150px" status="404" title="404 页面不存在" description="生活总归带点荒谬。" size="huge">
      <template #footer>
        <n-button @click="rick">找点乐子吧</n-button>
      </template>
    </n-result>
  </div>
</template>

<script>
import { NButton, NResult} from "naive-ui";
import NavBar from "@/components/NavBar";

export default {
  components: {
    NButton,
    NResult,
    NavBar,
  },
  setup() {
    const rick = () => {
      window.location.href = "https://www.bilibili.com/video/BV1GJ411x7h7/?spm_id_from=333.337.search-card.all.click&vd_source=493116500e2eda077f20efd943635303";
    };

    return {
      rick,
    }
  }
}

</script>

<style scoped>
* {
  font-family: si-yuan, sans-serif;
}
#all {
  min-height: 100vh;
  background-color: rgb(241,241,243);
}
</style>
