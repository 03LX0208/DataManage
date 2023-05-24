<template>
  <div class="all">
    <NavBar/>
    <div class="container">
      <div class="row">
        <div class="col-12">
          <n-card title="选课系统">
            <n-data-table
                striped
                :columns="sectionCols"
                :data="allSections"
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
import { NDataTable, NCard, NButton, useMessage } from "naive-ui";
import {useStore} from "vuex";


export default {
  components: {
    NavBar,
    NDataTable,
    NCard,
  },
  setup() {
    const store = useStore();
    const message = useMessage();

    const createSectionColumns = ({
                                    selectSection,
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
          title: "教室",
          key: "site"
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

    let allSections = ref([]);
    let chosenSections = ref([]);
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
          response[i].site = response[i].classroom_site + response[i].classroom_name;
          const times = response[i].section_time.split("/");
          let class_time = "";
          for (let i = 0; i < times.length - 1; i += 2) {
            class_time += "「" + times[i] + times[i + 1] +  "」 ";
          }
          response[i].class_time = class_time;
        }
        chosenSections.value = response;
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
              let ok = true;
              for (const chosen of response) {
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