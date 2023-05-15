<template>
  <div id="all">
    <NavBar/>
    <div class="container">
      <div class="row">
        <div class="col"></div>

        <div class="col-2">
          <n-list hoverable clickable style="margin-top: 20px;">
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
          </n-list>
        </div>

        <div class="col-6">
            <n-card
                title="📖 如何成功"
                embedded
                :bordered="false"
                style="margin-top: 20px; margin-bottom: 20px;"
                hoverable
            >
              如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
              996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
            </n-card>
          <n-card
              title="⭐ 我的收藏"
              embedded
              :bordered="false"
              hoverable
          >
            如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
            996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
          </n-card>
          <div class="row">
            <div class="col">
              <n-card
                  title="📖 如何成功"
                  embedded
                  :bordered="false"
                  style="margin-top: 20px; margin-bottom: 20px;"
                  hoverable
              >
                如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
                996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
              </n-card>
            </div>
            <div class="col">
              <n-card
                  title="📖 如何成功"
                  embedded
                  :bordered="false"
                  style="margin-top: 20px; margin-bottom: 20px;"
                  hoverable
              >
                如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
                996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
              </n-card>
            </div>
          </div>
        </div>
        <div class="col"></div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar"
import {NCard, NList, NListItem, NThing} from 'naive-ui';
import $ from 'jquery'

export default {
  components: {
    NavBar,
    NCard,
    NList,
    NListItem,
    NThing,
  },
  setup() {
    $.ajax({
      url: "http://localhost:4080/test/",
      type: "get",
      data: {
        id: 3,
        name: "王成宇",
        sex: "woman",
      },
      success(resp) {
        console.log(resp)
      },
    });
  }
}
</script>

<style scoped>
#all {
  min-height: 100vh;
  background-color: rgb(241,241,243);
}
</style>
