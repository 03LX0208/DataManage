<template>
  <div class="all">
    <NavBar/>
    <div class="container-fluid">
      <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
          <n-card title="选课系统" style="margin-top: 20px" hoverable>
            <template #header-extra>
              <n-switch size="large" checked-value="true" unchecked-value="false" @update:value="handleSwitch">
                <template #checked>
                  进入选课页面
                </template>
                <template #unchecked>
                  查看所选课程
                </template>
              </n-switch>
            </template>
            <n-data-table
                v-if="!switchOK"
                striped
                :columns="sectionCols"
                :data="allSections"
                :pagination="paginationReactive"
                :bordered="false"
                :single-line="false"
                style="font-size: 15px;"
                @update:sorter="handleSorterChange"
            />
            <n-data-table
                v-if="switchOK"
                striped
                :columns="chosenSectionCols"
                :data="chosenSections"
                :pagination="paginationReactive"
                :bordered="false"
                :single-line="false"
                style="font-size: 15px;"
            />
          </n-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import $ from "jquery";
import {reactive, ref, h} from "vue";
import { NDataTable, NCard, NButton, NSwitch, useMessage } from "naive-ui";
import {useStore} from "vuex";

const createChosenSectionColumns = ({
                                deleteSection,
                              }) => {
  return [
    {
      title: "课程编号",
      key: "section_id"
    },
    {
      title: "课程名称",
      key: "course_name"
    },
    {
      title: "学时",
      key: "course_period"
    },
    {
      title: "学分",
      key: "course_credit"
    },
    {
      title: "授课老师",
      key: "teacher_name"
    },
    {
      title: "上课时间",
      key: "class_time"
    },
    {
      title: "学院",
      key: "faculty_name"
    },
    {
      title: "教室",
      key: "site"
    },
    {
      title: '退课',
      render(row) {
        return h(
            NButton,
            {
              strong: true,
              secondary: true,
              type: "warning",
              size: 'small',
              onClick: () => deleteSection(row)
            },
            {default: () => '退课'}
        )
      }
    },
  ];
};

export default {
  components: {
    NavBar,
    NDataTable,
    NCard,
    NSwitch,
  },
  setup() {
    const store = useStore();
    const message = useMessage();

    const createSectionColumns = ({
                                    selectSection, pushToGraph
                                  }) => {
      return [
        {
          title: "课程编号",
          key: "section_id",
          sorter(rowA, rowB) {
            return Number(rowA.section_id) - Number(rowB.section_id);
          }
        },
        {
          title: "课程名称",
          key: "course_name"
        },
        {
          title: "学时",
          key: "course_period",
          sorter(rowA, rowB) {
            return Number(rowA.course_period) - Number(rowB.course_period);
          }
        },
        {
          title: "学分",
          key: "course_credit",
          sorter(rowA, rowB) {
            return Number(rowA.course_credit) - Number(rowB.course_credit);
          }
        },
        {
          title: "授课老师",
          key: "teacher_name"
        },
        {
          title: "上课时间",
          key: "class_time"
        },
        {
          title: "学院",
          key: "faculty_name"
        },
        {
          title: "已选人数",
          key: "now_capacity",
          sorter(rowA, rowB) {
            return Number(rowA.now_capacity) - Number(rowB.now_capacity);
          }
        },
        {
          title: "课程容量",
          key: "total_capacity",
          sorter(rowA, rowB) {
            return Number(rowA.total_capacity) - Number(rowB.total_capacity);
          }
        },
        {
          title: "教室",
          key: "site"
        },
        {
          title: '先导课程',
          render(row) {
            return h(
                NButton,
                {
                  strong: true,
                  secondary: true,
                  type: "warning",
                  size: 'small',
                  onClick: () => pushToGraph(row)
                },
                {default: () => '查看'}
            )
          }
        },
        {
          title: '选课',
          render(row) {
            return h(
                NButton,
                {
                  strong: true,
                  secondary: true,
                  type: "info",
                  size: 'small',
                  onClick: () => selectSection(row)
                },
                {default: () => '选课'}
            )
          }
        }
      ];
    };

    let sectionCount = ref([]);
    let allSections = ref([]);
    let chosenSections = ref([]);

    $.ajax({
      url: "https://data.lxcode.xyz/api/student-section/query/section-now-count/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(re) {
        sectionCount.value = re;
        $.ajax({
          url: "https://data.lxcode.xyz/api/student-section/query/all-section-by-student-id/",
          type: "get",
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          data: {
            student_id: store.state.user.username,
          },
          success(response) {
              for (let i in response) {
                if (response[i] == null) continue;
                response[i].site = response[i].classroom_site + response[i].classroom_name;
                const times = response[i].section_time.split("/");
                let class_time = "";
                for (let i = 0; i < times.length - 1; i += 2) {
                  class_time += "「" + times[i] + times[i + 1] +  "」 ";
                }
                response[i].class_time = class_time;
                chosenSections.value.push(response[i]);
              }
            $.ajax({
              url: "https://data.lxcode.xyz/api/student-section/get-all-section/",
              type: "get",
              success(resp) {
                for (let i in resp) {
                  resp[i].site = resp[i].classroom_site + resp[i].classroom_name;
                  const times = resp[i].section_time.split("/");
                  let class_time = "";
                  for (let i = 0; i < times.length - 1; i += 2) {
                    class_time += "「" + times[i] + times[i + 1] +  "」 ";
                  }
                  resp[i].class_time = class_time;
                  for (const capa of sectionCount.value) {
                    if (Number(capa.section_id) === Number(resp[i].section_id)) {
                      resp[i].now_capacity = capa.now_capacity;
                      resp[i].total_capacity = capa.total_capacity;
                      break;
                    }
                  }
                  let ok = true;
                  for (const chosen of chosenSections.value) {
                    if (Number(chosen.section_id) === Number(resp[i].section_id)) {
                      ok = false;
                      break;
                    }
                  }
                  if (ok) allSections.value.push(resp[i]);
                }
              }
            });
          }
        });
      }
    });

    const paginationReactive = reactive({
      page: 1,
      pageSize: 9,
      showSizePicker: true,
      pageSizes: [5, 7, 9, 11, 13],
      onChange: (page) => {
        paginationReactive.page = page;
      },
      onUpdatePageSize: (pageSize) => {
        paginationReactive.pageSize = pageSize;
        paginationReactive.page = 1;
      }
    });

    let switchOK = ref(false);
    const handleSwitch = (value) => {
      switchOK.value = value === "true";
    }

    return {
      handleSorterChange (sorter) {
        allSections.value.forEach((column) => {
          /** column.sortOrder !== undefined means it is uncontrolled */
          if (column.sortOrder === undefined) return
          if (!sorter) {
            column.sortOrder = false
            return
          }
          if (column.key === sorter.columnKey) column.sortOrder = sorter.order
          else column.sortOrder = false
        })
      },
      chosenSections,
      switchOK,
      handleSwitch,
      paginationReactive,
      allSections,
      sectionCols: createSectionColumns({
        selectSection(row) {
          $.ajax({
            url: "https://data.lxcode.xyz/api/student-section/add/",
            type: "post",
            headers: {
              Authorization: "Bearer " + store.state.user.token,
            },
            data: {
              student_id: store.state.user.username,
              section_id: row.section_id,
            },
            success(resp) {
              if (resp.error_message === "success") {
                message.success("选课成功！");
                const sections = allSections.value;
                allSections.value = [];
                for (const section of sections) {
                  if (Number(section.section_id) !== Number(row.section_id)) {
                    allSections.value.push(section);
                  }
                }
                chosenSections.value.push(row);
              } else {
                message.error(resp.error_message);
              }
            }
          });
        },
        pushToGraph(row) {
          window.open(`/student/select-course/graph/${row.course_id}`, '_blank');
        }
      }),
      chosenSectionCols: createChosenSectionColumns({
        deleteSection(row) {
          $.ajax({
            url: "https://data.lxcode.xyz/api/student-section/delete/",
            type: "post",
            headers: {
              Authorization: "Bearer " + store.state.user.token,
            },
            data: {
              student_id: store.state.user.username,
              section_id: row.section_id,
            },
            success(resp) {
              if (resp.error_message === "success") {
                message.success("退课成功！");
                const sections = chosenSections.value;
                chosenSections.value = [];
                for (const section of sections) {
                  if (Number(section.section_id) !== Number(row.section_id)) {
                    chosenSections.value.push(section);
                  }
                }
                allSections.value.push(row);
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
.all {
  min-height: 100vh;
  background-color: rgb(241,241,243);
}
</style>