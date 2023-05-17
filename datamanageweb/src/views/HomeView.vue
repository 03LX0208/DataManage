<template>
  <div id="all">
    <NavBar/>
    <div class="container" v-if="$store.state.user.identity === 'student'">
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

    <div class="container" v-if="$store.state.user.identity === 'admin'">
      <div class="row">
        <div class="col-12">
          <n-card content-style="padding: 0;">
            <n-tabs
                type="line"
                size="large"
                :tabs-padding="20"
                pane-style="padding: 20px;"
            >
              <n-tab-pane name="用户管理">
                <n-data-table
                    :columns="usersColumns"
                    :data="allUsers"
                    :pagination="false"
                    :bordered="false"
                    :single-line="false"
                    style="font-size: 15px;"
                />
              </n-tab-pane>
              <n-tab-pane name="别的">

              </n-tab-pane>
            </n-tabs>
          </n-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar"
import {NCard, NList, NListItem, NThing,  NTabs, NTabPane, NDataTable} from 'naive-ui';
import $ from 'jquery'
import {useStore} from "vuex";
import {ref} from 'vue';

const createUsersColumns = () => {
  return [
    {
      title: "教学号",
      key: "username"
    },
    {
      title: "身份",
      key: "identity",
    },
  ];
};


export default {
  components: {
    NavBar,
    NCard,
    NList,
    NListItem,
    NThing,
    NTabs,
    NTabPane,
    NDataTable,
  },
  setup() {
    const store = useStore();
    let allUsers = ref([]);

    if (store.state.user.identity === "admin") {
      // 获得管理员掌握的所有用户
      $.ajax({
        url: "https://data.lxcode.xyz/api/admin/get-all-user/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          allUsers.value = resp;
        }
      });
    }

    return {
      allUsers,
      usersColumns: createUsersColumns({

      }),
    }
  }
}
</script>

<style scoped>
#all {
  min-height: 100vh;
  background-color: rgb(241,241,243);
}
</style>
