<template>
  <div id="all">
    <NavBar/>
    <div class="container" v-if="$store.state.user.identity === 'admin'">
      <div class="row">
        <div class="col-12">
          <n-card content-style="" style="margin-top: 20px;">
            <n-tabs
                type="line"
                size="large"
                :tabs-padding="20"
                pane-style="padding: 20px;"
            >
              <n-tab-pane name="用户管理">
                <AllUsers />
              </n-tab-pane>
              <n-tab-pane name="学院管理">
                <n-space justify="end">
                  <n-button class="" round size="large" type="primary" @click="showAddFacultyModalBtn">
                    新建学院
                  </n-button>
                  <n-modal v-model:show="showAddFacultyModal">
                    <n-card
                        style="width: 700px"
                        title="新建学院"
                        :bordered="false"
                        size="huge"
                        role="dialog"
                        aria-modal="true"
                    >
                      <template #header-extra>
                        学院信息
                      </template>
                      <n-space vertical>
                        <div class="row">
                          <div class="col-8">
                            学院编号
                            <n-input placeholder="纯数字哦~" v-model:value="facultyToAdd.faculty_id" maxlength="20" show-count clearable />
                          </div>
                        </div>
                        <div class="row" style="margin-top: 6px;">
                          <div class="col-8">
                            学院名称
                            <n-input placeholder="" v-model:value="facultyToAdd.faculty_name" maxlength="20" show-count clearable />
                          </div>
                        </div>
                        <div class="row" style="margin-top: 6px;">
                          <div class="col-8">
                            学院地址
                            <n-input placeholder="" v-model:value="facultyToAdd.faculty_site" maxlength="20" show-count clearable />
                          </div>
                        </div>
                      </n-space>
                      <template #footer>
                        <n-space justify="end">
                          <n-button @click="addFaculty" strong secondary round type="primary">
                            确认创建
                          </n-button>
                        </n-space>
                      </template>
                    </n-card>
                  </n-modal>
                </n-space>
                <n-divider/>
                <AllFaculties/>
              </n-tab-pane>
              <n-tab-pane name="课程管理">
                <n-space justify="end">
                  <n-button class="" round size="large" secondary type="warning" @click="showAddCourseModalBtn">
                    开设课程
                  </n-button>
                  <n-modal v-model:show="showAddCourseModal">
                    <n-card
                        style="width: 700px"
                        title="开设课程"
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
                            课程编号
                            <n-input placeholder="只能由数字组成~" v-model:value="courseToAdd.course_id" clearable />
                          </div>
                          <div class="col-2"></div>
                          <div class="col-4">
                            课程名称
                            <n-input placeholder="" v-model:value="courseToAdd.course_name" clearable />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-4">
                            课程学时
                            <n-input placeholder="单位：小时" v-model:value="courseToAdd.course_period" clearable />
                          </div>
                          <div class="col-2"></div>
                          <div class="col-4">
                            课程学分
                            <n-input placeholder="只能由数字组成~" v-model:value="courseToAdd.course_credit" clearable />
                          </div>
                        </div>
                        <div style="margin-top: 6px;">所属学院</div>
                        <div class="row">
                          <div class="col-6">
                            <n-select placeholder="请选择所属学院~" v-model:value="courseToAdd.faculty_id" :options="facultyOptions" />
                          </div>
                        </div>
                        <div style="margin-top: 6px;">先导课程</div>
                        <div class="row">
                          <div class="col-6">
                            <n-select placeholder="请选择先导课程~ 不选默认没有" multiple v-model:value="courseToAdd.pilot_course_id" :options="courseOptions" />
                          </div>
                        </div>
                      </n-space>
                      <template #footer>
                        <n-space justify="end">
                          <n-button @click="addCourse" strong secondary round type="primary">
                            确认开设
                          </n-button>
                        </n-space>
                      </template>
                    </n-card>
                  </n-modal>
                </n-space>
                <n-divider/>
                <AllCourses/>
              </n-tab-pane>
              <n-tab-pane name="教室管理">
                <n-space justify="end">
                  <n-button class="" round size="large" secondary type="success" @click="showAddClassModalBtn">
                    开放教室
                  </n-button>
                  <n-modal v-model:show="showAddClassModal">
                    <n-card
                        style="width: 700px"
                        title="开放教室"
                        :bordered="false"
                        size="huge"
                        role="dialog"
                        aria-modal="true"
                    >
                      <template #header-extra>
                        教室信息
                      </template>
                      <n-space vertical>
                        <div class="row">
                          <div class="col-6">
                            教室编号
                            <n-input placeholder="只能由数字组成~" v-model:value="classToAdd.classroom_id" clearable />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-6">
                            教室
                            <n-input placeholder="" v-model:value="classToAdd.classroom_name" show-count maxlength="20" clearable />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-6">
                            教学楼
                            <n-input placeholder="" v-model:value="classToAdd.classroom_site" show-count maxlength="20" clearable />
                          </div>
                        </div>
                      </n-space>
                      <template #footer>
                        <n-space justify="end">
                          <n-button @click="addClassroom" strong secondary round type="success">
                            确认开放
                          </n-button>
                        </n-space>
                      </template>
                    </n-card>
                  </n-modal>
                </n-space>
                <n-divider/>
                <AllClassroom/>
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
import {NCard, NTabs, NTabPane, NButton, NSpace, NDivider, NModal, NSelect, NInput, useMessage } from 'naive-ui';
import AllUsers from "@/components/admin/AllUsers";
import AllFaculties from "@/components/admin/AllFaculties";
import AllCourses from "@/components/admin/AllCourses";
import AllClassroom from "@/components/admin/AllClassroom";
import $ from 'jquery'
import {useStore} from "vuex";
import { ref, reactive } from 'vue';

export default {
  components: {
    AllClassroom,
    NavBar,
    NCard,
    NTabs,
    NTabPane,
    NSpace,
    NButton,
    AllUsers,
    NDivider,
    NModal,
    NSelect,
    NInput,
    AllFaculties,
    AllCourses,
  },

  setup() {
    const store = useStore();
    const message = useMessage();

    let identityOptions = ref([
      {
        label: "学生",
        value: "student",
      },
      {
        label: "老师",
        value: "teacher",
      },
      {
        label: "管理员",
        value: "admin",
      },
    ]);
    let genderOptions = ref([
      {
        label: "男",
        value: "male",
      },
      {
        label: "女",
        value: "female",
      }
    ]);
    let facultyOptions = ref([]);

    $.ajax({
      url: "https://data.lxcode.xyz/api/faculty/get-all/",
      type: "get",
      success(resp) {
        for (const fac of resp) {
          facultyOptions.value.push({
            label: fac.facultyName,
            value: fac.facultyId
          })
        }
      }
    });

    // 添加学院
    let showAddFacultyModal = ref(false);
    const showAddFacultyModalBtn = () => {
      showAddFacultyModal.value = true;
    }

    let facultyToAdd = reactive({
      faculty_id: null,
      faculty_name: null,
      faculty_site: null
    });

    const addFaculty = () => {
      $.ajax({
        url: "https://data.lxcode.xyz/api/faculty/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          faculty_id: facultyToAdd.faculty_id,
          faculty_name: facultyToAdd.faculty_name,
          faculty_site: facultyToAdd.faculty_site
        },
        success(resp) {
          if (resp.error_message === "success") {
            message.success("创建成功！");
            setTimeout(() => { location.reload(); }, 800)
          } else {
            message.error(resp.error_message);
          }
        }
      });
    };

    // 添加课程
    let showAddCourseModal = ref(false);
    const showAddCourseModalBtn = () => {
      showAddCourseModal.value = true;
    }
    let courseToAdd = reactive({
      course_id: null,
      course_name: null,
      course_period: null,
      course_credit: null,
      faculty_id: null,
      pilot_course_id: []
    });

    let courseOptions = ref([]);
    $.ajax({
      url: "https://data.lxcode.xyz/api/course/get-all/",
      type: "get",
      success(resp) {
        for (const course of resp) {
          courseOptions.value.push({
            label: course.courseName,
            value: course.courseId
          });
        }
      }
    });

    const addCourse = () => {
      let pilot = "";
      for (const id of courseToAdd.pilot_course_id) {
        pilot += String(id) + " ";
      }
      $.ajax({
        url: "https://data.lxcode.xyz/api/course/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          course_id: courseToAdd.course_id,
          course_name: courseToAdd.course_name,
          course_period: courseToAdd.course_period,
          course_credit: courseToAdd.course_credit,
          faculty_id: courseToAdd.faculty_id,
          pilot_course_id: pilot,
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

    // 添加教室
    let showAddClassModal = ref(false);
    const showAddClassModalBtn = () => {
      showAddClassModal.value = true;
  }
    let classToAdd = reactive({
      classroom_id: null,
      classroom_name: null,
      classroom_site: null,
    });

    const addClassroom = () => {
      $.ajax({
        url: "https://data.lxcode.xyz/api/classroom/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          classroom_id: classToAdd.classroom_id,
          classroom_name: classToAdd.classroom_name,
          classroom_site: classToAdd.classroom_site,
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

    return {
      classToAdd,
      showAddClassModal,
      showAddClassModalBtn,
      addClassroom,
      addCourse,
      courseOptions,
      showAddCourseModal,
      showAddCourseModalBtn,
      courseToAdd,
      addFaculty,
      facultyToAdd,
      showAddFacultyModal,
      showAddFacultyModalBtn,
      identityOptions,
      genderOptions,
      facultyOptions,
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
