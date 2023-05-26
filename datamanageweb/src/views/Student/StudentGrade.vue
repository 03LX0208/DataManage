<template>
  <div class="all">
    <NavBar/>
    <div class="container-fluid">
      <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
          <n-card title="我的成绩">
            <n-data-table
                striped
                :columns="gradeCols"
                :data="allGrades"
                :pagination="paginationReactive"
                :bordered="false"
                :single-line="false"
                style="font-size: 15px;"
            />
            <n-modal style="width: 600px; height: 400px; background-color: white" v-model:show="showGradeGraph">
              <GradeGraph :msg="section_id" />
            </n-modal>
            <template #footer>
              <n-statistic label="您已经学习了" tabular-nums class="">
                <n-number-animation  :from="0" :to="gradeCount" />
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
import {reactive, ref, h} from "vue";
import { NDataTable, NCard, NButton, NStatistic, NNumberAnimation, NModal} from "naive-ui";
import {useStore} from "vuex";
import GradeGraph from "@/components/student/GradeGraph";

export default {
  components: {
    NavBar,
    NDataTable,
    NCard,
    NStatistic, NNumberAnimation,
    NModal,
    GradeGraph,
  },
  setup() {
    let allGrades = ref([]);
    let gradeCount = ref(null);
    let section_id = ref(null);
    const store = useStore();
    let showGradeGraph = ref(false);

    $.ajax({
      url: "https://data.lxcode.xyz/api/student-section/query/student-score/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      data: {
        student_id: store.state.user.username,
      },
      success(resp) {
        allGrades.value = resp;
        gradeCount.value = allGrades.value.length;
      }
    })

    const createGradeColumns = ({
                                    showGrade,
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
          title: "学院",
          key: "faculty_name"
        },
        {
          title: "成绩",
          key: "grade"
        },
        {
          title: '详情',
          render(row) {
            return h(
                NButton,
                {
                  strong: true,
                  secondary: true,
                  type: "info",
                  size: 'small',
                  onClick: () => showGrade(row)
                },
                {default: () => '成绩分布'}
            )
          }
        }
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
      section_id,
      gradeCount,
      paginationReactive,
      allGrades,
      gradeCols: createGradeColumns({
        showGrade(row) {
          section_id.value = row.section_id;
          console.log(section_id.value);
          showGradeGraph.value = true;
        }
      }),
      showGradeGraph,
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