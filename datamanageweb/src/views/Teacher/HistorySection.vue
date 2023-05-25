<template>
  <div class="all">
    <NavBar/>
    <div class="container-fluid">
      <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
          <n-card title="历史课程">
            <n-data-table
                striped
                :columns="sectionCols"
                :data="myCourses"
                :pagination="paginationReactive"
                :bordered="false"
                :single-line="false"
                style="font-size: 15px;"
            />
            <template #footer>
              <n-statistic label="您曾经执教过" tabular-nums class="">
                <n-number-animation  :from="0" :to="historyCount" />
                <template #suffix>
                  门课程。
                </template>
              </n-statistic>
            </template>
          </n-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import $ from "jquery";
import {h, reactive, ref} from "vue";
import {NDataTable, NCard, NButton, NStatistic, NNumberAnimation,} from "naive-ui";
import {useStore} from "vuex";


export default {
  components: {
    NavBar,
    NDataTable,
    NCard,
    NStatistic,
    NNumberAnimation,
  },
  setup() {
    const store = useStore();
    let historyCount = ref(null);

    let myCourses = ref([]);
    $.ajax({
      url: "https://data.lxcode.xyz/api/student-section/query/section-now-count/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(response) {
        $.ajax({
          url: "https://data.lxcode.xyz/api/section/query/by-teacher-id/",
          type: "get",
          data: {
            teacher_id: store.state.user.username,
          },
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success(resp) {
            for (let course of resp) {
              if (course.is_completed === "0") continue;
              for (const capacity of response) {
                if (Number(capacity.section_id) === Number(course.section_id)) {
                  course.now_capacity = capacity.now_capacity;
                  course.total_capacity = capacity.total_capacity;
                  break;
                }
              }
              myCourses.value.push(course)
            }
            historyCount.value = myCourses.value.length;
          }
        });
      }
    });


    const createSectionColumns = ({ pushToScore }) => {
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
          title: "上课时间",
          key: "section_time"
        },
        {
          title: "教室地址",
          key: "classroom_name"
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
          title: "学院",
          key: "faculty_name"
        },
        {
          title: "已选人数",
          key: "now_capacity"
        },
        {
          title: "课程容量",
          key: "total_capacity"
        },
        {
          title: '操作',
          render(row) {
            return h(
                NButton,
                {
                  strong: true,
                  secondary: true,
                  type: "primary",
                  size: 'small',
                  onClick: () => pushToScore(row)
                },
                {default: () => '查看成绩'}
            )
          }
        },
      ];
    };


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

    return {
      historyCount,
      myCourses,
      sectionCols: createSectionColumns({
        pushToScore(row) {
          window.open(`/teacher/history-section/score/${row.section_id}`);
        }
      }),
      paginationReactive,
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