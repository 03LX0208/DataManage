<template>
  <div class="all">
    <NavBar/>
    <div class="container">
      <div class="row">
        <div class="col"></div>
        <div class="col-8">
          <n-card
              title="⭐ 常用功能"
              embedded
              :bordered="false"
              style="margin-top: 20px; margin-bottom: 20px;"
              hoverable
          >
            <NSpace>
              <n-button tertiary round type="info" size="large" @click="showAddSectionModalBtn">
                开课
              </n-button>
              <n-modal v-model:show="showAddSectionModal">
                <n-card
                    style="width: 700px"
                    title="开课"
                    :bordered="false"
                    size="huge"
                    role="dialog"
                    aria-modal="true"
                >
                  <template #header-extra>
                    课程信息
                  </template>
                  <n-space vertical>
                    <div class="row">
                      <div class="col-4">
                        任课老师
                        <n-input disabled :placeholder="$store.state.user.name" />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-4">
                        课程
                        <n-select placeholder="选择要开课的课程" :options="courseOptions" v-model:value="sectionToAdd.course_id" />
                      </div>
                      <div class="col-2"></div>
                      <div class="col-4">
                        教室
                        <n-select placeholder="选择上课地点" :options="classroomOptions" v-model:value="sectionToAdd.classroom_id" />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-8">
                        上课时间
                        <n-cascader
                            v-model:value="sectionToAdd.section_time"
                            :options="timeOptions"
                            multiple
                            clearable
                            placeholder="选择每周的上课时间（多选）"
                        />
                      </div>
                    </div>
                  </n-space>
                  <template #footer>
                    <n-space justify="end">
                      <n-button @click="addSection" strong secondary round type="success">
                        确认开课
                      </n-button>
                    </n-space>
                  </template>
                </n-card>
              </n-modal>
            </NSpace>
          </n-card>
        </div>
        <div class="col"></div>
      </div>
      <div class="row">
<!--        <div class="col-2"></div>-->
        <div class="col-12">
          <n-card
            title="任教课程"
          >
            <n-data-table
                striped
                :columns="sectionCols"
                :data="myCourses"
                :pagination="paginationReactive"
                :bordered="false"
                :single-line="false"
                style="font-size: 15px;"
            />
          </n-card>
        </div>
<!--        <div class="col-2"></div>-->
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import {
  NCard,
  NSpace,
  NButton,
  NModal,
  NSelect,
  NCascader,
  useMessage,
  NInput,
  NDataTable,
  NPopconfirm
} from 'naive-ui';
import {h, reactive, ref} from "vue";
import {useStore} from "vuex";
import $ from 'jquery';

const days = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];
const hours = ["8:00-8:50", "9:00-9:50", "10:00-10:50", "11:00-11:50", "14:00-14:50", "15:00-15:50", "16:00-16:50", "17:00-17:50"];

function getOptions() {
  const options = [];

  let idx = 0;
  for (const day of days) {
    let child = [];
    for (const hour of hours) {
      child.push({
        value: idx,
        label: hour,
      });
      idx++;
    }
    options.push({
      label: day,
      value: idx+100,
      children: child,
    })
  }

  return options;
}

export default {
  components: {
    NInput,
    NavBar,
    NCard,
    NSpace,
    NButton,
    NModal,
    NSelect,
    NCascader,
    NDataTable,
  },
  setup() {
    const store = useStore();
    const message = useMessage();
    let courseOptions = ref([]);
    let classroomOptions = ref([]);

    $.ajax({
      url: "https://data.lxcode.xyz/api/classroom/get-all/",
      type: "get",
      success(resp) {
        for (const classroom of resp) {
          classroomOptions.value.push({
            label: classroom.classroomName,
            value: classroom.classroomId
          });
        }
      }
    });

    $.ajax({
      url: "https://data.lxcode.xyz/api/course/get-all/",
      type: "get",
      success(resp) {
        for (const course of resp) {
          courseOptions.value.push({
            label: course.courseName,
            value: course.courseId,
          })
        }
      }
    });

    let sectionToAdd = reactive({
      teacher_id: store.state.user.username,
      course_id: null,
      classroom_id: null,
      section_time: null,
    });

    let showAddSectionModal = ref(false);
    const showAddSectionModalBtn = () => {
      showAddSectionModal.value = true;
    }

    const addSection = () => {
      let time = "";
      for (const tm of sectionToAdd.section_time) {
        time += String(tm) + " ";
      }

      $.ajax({
        url: "https://data.lxcode.xyz/api/section/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          teacher_id: store.state.user.username,
          course_id: sectionToAdd.course_id,
          classroom_id: sectionToAdd.classroom_id,
          section_time: time,
        },
        success(resp) {
          if (resp.error_message === "success") {
            message.success("添加成功！");
            setTimeout(() => { location.reload(); }, 900);
          } else {
            message.error(resp.error_message);
          }
        }
      });
    }

    let myCourses = ref([]);
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
        myCourses.value = resp;
      }
    })

    const createSectionColumns = ({
                                      deleteSection
                                    }) => {
      return [
        {
          title: "课程编号",
          key: "course_id"
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
          title: "操作",
          render(row) {
            const buttons = [
              {
                text: "删除课程",
                color: "error",
                onClick: () => deleteSection(row)
              }
            ];

            const handlePositiveClick = (row) => {
              return () => {
                helper(row);
              };
            };

            const helper = (row) => {
              $.ajax({
                url: "https://data.lxcode.xyz/api/section/delete/",
                type: "post",
                headers: {
                  Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                  section_id: row.section_id,
                },
                success(resp) {
                  if (resp.error_message === "success") {
                    message.success("删除成功！");
                    setTimeout(() => { location.reload(); }, 1000)
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
                      buttons.map(({ text, color, onClick }, index) =>
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
                                          strong: true,
                                          type: color,
                                          size: "small",
                                          onClick
                                        },
                                        { default: () => text }
                                    ),
                                default: () =>
                                    h("span", {}, "您确定要删除该课程吗？")
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
      page: 2,
      pageSize: 5,
      showSizePicker: true,
      pageSizes: [3, 5, 7],
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
      myCourses,
      addSection,
      timeOptions: getOptions(),
      showAddSectionModal,
      showAddSectionModalBtn,
      sectionToAdd,
      courseOptions,
      classroomOptions,
      sectionCols: createSectionColumns({
        deleteSection() {}
      })
    }
  }
}
</script>

<style scoped>
.all {
  min-height: 100vh;
  min-width: 100vh;
  background-color: rgb(241,241,243);
}
</style>