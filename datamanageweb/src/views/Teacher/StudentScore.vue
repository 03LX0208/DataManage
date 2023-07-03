<template>
  <div class="all">
    <NavBar/>
    <div class="container-fluid">
      <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
          <n-card title="学生成绩" hoverable style="margin-top: 20px">
            <template #header-extra>
              <n-switch size="large" checked-value="true" unchecked-value="false" @update:value="handleSwitch">
                <template #checked>
                  查看成绩分布直方图
                </template>
                <template #unchecked>
                  查看所有学生成绩
                </template>
              </n-switch>
            </template>
            <n-data-table
                striped
                :columns="sectionCols"
                :data="allStudents"
                :pagination="paginationReactive"
                :bordered="false"
                :single-line="false"
                style="font-size: 15px;"
                @update:sorter="handleSorterChange"
                v-if="!switchOK"
            />

            <div class="row" v-if="switchOK">
              <div class="col-4"></div>
              <div class="col-4">
                <GradeGraph :msg="id" />
              </div>
              <div class="col-4"></div>
            </div>


            <n-modal v-model:show="showModal">
              <n-card
                  title="成绩"
                  :bordered="false"
                  size="huge"
                  role="dialog"
                  aria-modal="true"
                  style="width: 200px;"
              >
                <n-input v-model:value="grade" placeholder="成绩~" />
                <template #footer>
                  <n-button type="success" @click="add">
                    确定
                  </n-button>
                </template>
              </n-card>
            </n-modal>
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
import {NDataTable, NCard, useMessage, NButton, NModal, NInput, NSwitch} from "naive-ui";
import {useStore} from "vuex";
import router from "@/router";
import GradeGraph from "@/components/student/GradeGraph";


export default {
  components: {
    NModal,
    NavBar,
    NDataTable,
    NCard,
    NButton,
    NInput,
    NSwitch,
    GradeGraph,
  },
  setup() {
    const store = useStore();
    const message = useMessage();
    let allStudents = ref([]);
    let showModal = ref(false);
    let switchOK = ref(false);
    const handleSwitch = (value) => {
      switchOK.value = value === "true";
    }

    const id = router.currentRoute.value.params.id;
    $.ajax({
      url: "https://data.lxcode.xyz/api/section/query/score/",
      type: "get",
      data: {
        section_id: id,
      },
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        allStudents.value = resp;
      }
    })

    const createSectionColumns = (
        {addScore}
    ) => {
      return [
        {
          title: "姓名",
          key: "student_name",
        },
        {
          title: "学号",
          key: "student_id",
          sorter(rowA, rowB) {
            return Number(rowA.student_id) - Number(rowB.student_id);
          }
        },
        {
          title: "学院",
          key: "faculty_name",
        },
        {
          title: "成绩",
          key: "grade",
          sorter(rowA, rowB) {
            return Number(rowA.grade) - Number(rowB.grade);
          }
        },
        {
          title: '操作',
          render(row) {
            if (row.grade >= 0) {
              return h("span");
            }
            return h(
                NButton,
                {
                  strong: true,
                  secondary: true,
                  type: "info",
                  size: 'large',
                  onClick: () => addScore(row)
                },
                {default: () => '给分'}
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

    let student_id = ref(null);
    let grade = ref(null);
    const add = () => {
      $.ajax({
        url: "https://data.lxcode.xyz/api/student-section/add-score/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          student_id: student_id.value,
          section_id: id,
          grade: grade.value,
        },
        success(resp) {
          if (resp.error_message === "success") {
            message.success("打分成功！");
          } else {
            message.error(resp.error_message);
          }
          for (let student of allStudents.value) {
            if (Number(student.student_id) === Number(student_id.value)) {
              student.grade = grade.value;
              break;
            }
          }
          showModal.value = false;
        }
      })
    }


    return {
      handleSorterChange (sorter) {
        allStudents.value.forEach((column) => {
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
      id,
      switchOK,
      handleSwitch,
      grade,
      add,
      showModal,
      allStudents,
      sectionCols: createSectionColumns({
        addScore(row) {
          student_id.value = row.student_id;
          showModal.value = true;
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