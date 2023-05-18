<template>
  <n-data-table
      :columns="usersColumns"
      :data="allUsers"
      :pagination="false"
      :bordered="false"
      :single-line="false"
      style="font-size: 15px;"
  />
</template>

<script>
import {NButton, NDataTable} from "naive-ui";
import {h, ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";

const createUsersColumns = ({
                              updateUser, deleteUser
                            }) => {
  return [
    {
      title: "教学号",
      key: "username"
    },
    {
      title: "身份",
      key: "identity",
    },
    {
      title: "操作",
      render(row) {
        const buttons = [
          {
            text: "修改用户信息",
            color: "info",
            onClick: () => updateUser(row)
          },
          {
            text: "删除用户",
            color: "error",
            onClick: () => deleteUser(row)
          }
        ];

        return h(
            "div",
            {},
            buttons.map(({ text, color, onClick }, index) =>
                h(
                    NButton,
                    {
                      strong: true,
                      type: color,
                      size: "small",
                      onClick,
                      key: index
                    },
                    { default: () => text }
                )
            )
        );
      }
    },
  ];
};

export default {
  components: {
    NDataTable,
  },
  setup() {
    const store = useStore();
    let allUsers = ref([]);

    if (store.state.user.identity === "admin") {
      // 获得管理员的管理的所有用户
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
        updateUser(row) {
          alert("还没写" + row.id);
        },
        deleteUser(row) {
          alert("还没写哈" + row.id);
        },
      }),
    }
  }
}
</script>

<style scoped>

</style>