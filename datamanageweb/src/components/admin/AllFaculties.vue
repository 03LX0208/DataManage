<template>
  <n-data-table
      :columns="facultiesColumns"
      :data="allFaculties"
      :pagination="false"
      :bordered="false"
      :single-line="false"
      style="font-size: 15px;"
  />
</template>

<script>
import {NButton, NDataTable, NSpace, useMessage} from "naive-ui";
import {h, ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";

const createFacultyColumns = ({
                              deleteFaculty
                            }) => {
  return [
    {
      title: "学院编号",
      key: "facultyId"
    },
    {
      title: "学院名称",
      key: "facultyName"
    },
    {
      title: "学院地址",
      key: "facultySite"
    },
    {
      title: "操作",
      render(row) {
        const buttons = [
          {
            text: "删除学院",
            color: "error",
            onClick: () => deleteFaculty(row)
          }
        ];

        return h(
            "div",
            {},
            [
              h(
                  NSpace,
                  { align: "center" },
                  buttons.map(({ text, color, onClick }, index) =>
                      h(
                          NButton,
                          {
                            strong: true,
                            type: color,
                            size: "small",
                            onClick,
                            key: index,
                          },
                          { default: () => text }
                      )
                  )
              ),
            ]
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
    const message = useMessage();
    let allFaculties = ref([]);

    $.ajax({
      url: "https://data.lxcode.xyz/api/faculty/get-all/",
      type: "get",
      success(resp) {
        allFaculties.value = resp;
      }
    });


    return {
      allFaculties,
      facultiesColumns: createFacultyColumns({
        deleteFaculty(row) {
          $.ajax({
            url: "https://data.lxcode.xyz/api/faculty/delete/",
            type: "post",
            headers: {
              Authorization: "Bearer " + store.state.user.token,
            },
            data: {
              faculty_id: row.facultyId,
            },
            success(resp) {
              if (resp.error_message === "success") {
                message.success("删除成功！");
                setTimeout(() => { location.reload(); }, 800)
              } else {
                message.error(resp.error_message);
              }
            }
          });
        }
      })
    }
  }
}

</script>

<style scoped>

</style>