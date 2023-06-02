<template>
  <n-card>
    <n-data-table
        striped
        :columns="facultiesColumns"
        :data="allFaculties"
        :pagination="paginationReactive"
        :bordered="false"
        :single-line="false"
        style="font-size: 15px;"
        @update:sorter="handleSorterChange"
    />
    <template #footer>
      <n-statistic label="一共有" tabular-nums class="">
        <n-number-animation  :from="0" :to="facultiesCount" />
        <template #suffix>
          个学院。
        </template>
      </n-statistic>
    </template>
  </n-card>
</template>

<script>
import {NButton, NDataTable, useMessage, NSpace, NPopconfirm, NStatistic, NNumberAnimation, NCard} from "naive-ui";
import {h, reactive, ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";

export default {
  components: {
    NCard,
    NNumberAnimation,
    NStatistic,
    NDataTable,
  },
  setup() {
    const store = useStore();
    const message = useMessage();
    let allFaculties = ref([]);
    let facultiesCount = ref(null);

    $.ajax({
      url: "https://data.lxcode.xyz/api/faculty/get-all/",
      type: "get",
      success(resp) {
        allFaculties.value = resp;
        facultiesCount.value = resp.length;
      }
    });

    const createFacultyColumns = ({
                                    deleteFaculty
                                  }) => {
      return [
        {
          title: "学院编号",
          key: "facultyId",
          sorter(rowA, rowB) {
            return Number(rowA.facultyId) - Number(rowB.facultyId);
          }
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
                secondary: "true",
                onClick: () => deleteFaculty(row)
              }
            ];

            const handlePositiveClick = (row) => {
              return () => {
                helper(row);
              };
            };

            const helper = (row) => {
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

            const handleNegativeClick = () => {};

            return h(
                "div",
                {},
                [
                  h(
                      NSpace,
                      { align: "center" },
                      buttons.map(({ text, color, onClick, secondary, }, index) =>
                          h(
                              NPopconfirm,
                              {
                                onPositiveClick: handlePositiveClick(row),
                                onNegativeClick: handleNegativeClick,
                                key: index
                              },
                              {
                                trigger: () =>
                                    h(
                                        NButton,
                                        {
                                          secondary,
                                          strong: true,
                                          type: color,
                                          size: "small",
                                          onClick
                                        },
                                        { default: () => text }
                                    ),
                                default: () =>
                                    h("span", {}, "您确定要删除该学院吗？")
                              }
                          )
                      )
                  )
                ]
            );
          }
        }



      ];
    };

    const paginationReactive = reactive({
      page: 1,
      pageSize: 7,
      showSizePicker: true,
      pageSizes: [3, 5, 7, 9, 11],
      onChange: (page) => {
        paginationReactive.page = page;
      },
      onUpdatePageSize: (pageSize) => {
        paginationReactive.pageSize = pageSize;
        paginationReactive.page = 1;
      }
    });


    return {
      facultiesCount,
      paginationReactive,
      allFaculties,
      facultiesColumns: createFacultyColumns({
        deleteFaculty() {
        },
      }),
      handleSorterChange (sorter) {
        allFaculties.value.forEach((column) => {
          /** column.sortOrder !== undefined means it is uncontrolled */
          if (column.sortOrder === undefined) return
          if (!sorter) {
            column.sortOrder = false
            return
          }
          if (column.key === sorter.columnKey) column.sortOrder = sorter.order
          else column.sortOrder = false
        })
      }
    }
  }
}

</script>

<style scoped>

</style>