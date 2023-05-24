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
                <n-space justify="end">
                  <n-button class="" round size="large" type="info" @click="showAddUserModalBtn">
                    添加用户
                  </n-button>
                  <n-modal v-model:show="showAddUserModal">
                    <n-card
                        style="width: 1000px"
                        title="添加用户"
                        :bordered="false"
                        size="huge"
                        role="dialog"
                        aria-modal="true"
                    >
                      <template #header-extra>
                        耐心填写信息~
                      </template>
                      <n-space vertical>
                        身份
                        <div class="row">
                          <div class="col-4">
                            <n-select placeholder="请选择对应身份" v-model:value="userToBeAdd.identity" :options="identityOptions" />
                          </div>
                          <div class="col"></div>
                        </div>
                        <div v-if="userToBeAdd.identity === 'student'">
                          <div class="row">
                            <div class="col-4">
                              姓名
                              <n-input placeholder="" v-model:value="userToBeAdd.student_name" maxlength="20" show-count clearable />
                            </div>
                            <div class="col-1"></div>
                            <div class="col-2">
                              性别
                              <n-select placeholder="请选择" v-model:value="userToBeAdd.student_gender" :options="genderOptions" />
                            </div>
                            <div class="col-2">
                              年龄
                              <n-input placeholder="" v-model:value="userToBeAdd.student_age" maxlength="3" show-count clearable />
                            </div>
                            <div class="col"></div>
                          </div>
                          <div class="row" style="margin-top: 6px;">
                            <div class="col-4">
                              教学号
                              <n-input placeholder="纯数字哦~" v-model:value="userToBeAdd.student_id" maxlength="20" show-count clearable />
                            </div>
                            <div class="col-1"></div>
                            <div class="col-4">
                              联系方式
                              <n-input placeholder="纯数字哦~" v-model:value="userToBeAdd.student_telephone" maxlength="20" show-count clearable />
                            </div>
                            <div class="col"></div>
                          </div>
                          <div style="margin-top: 6px">入学时间</div>
                          <div class="row">
                            <div class="col-6">
                              <n-date-picker v-model:value="userToBeAdd.student_enter_date" @update:value="updateDate" />
                            </div>
                          </div>
                          <div style="margin-top: 6px;">学院</div>
                          <div class="row">
                            <div class="col-6">
                              <n-select placeholder="请选择所属学院~" v-model:value="userToBeAdd.faculty_id" :options="facultyOptions" />
                            </div>
                          </div>
                        </div>
                        <div v-if="userToBeAdd.identity === 'teacher'">
                          <div class="row">
                            <div class="col-4">
                              姓名
                              <n-input placeholder="" v-model:value="userToBeAdd.teacher_name" maxlength="20" show-count clearable />
                            </div>
                            <div class="col-1"></div>
                            <div class="col-2">
                              性别
                              <n-select placeholder="请选择" v-model:value="userToBeAdd.teacher_gender" :options="genderOptions" />
                            </div>
                            <div class="col-2">
                              年龄
                              <n-input placeholder="" v-model:value="userToBeAdd.teacher_age" maxlength="3" show-count clearable />
                            </div>
                            <div class="col"></div>
                          </div>
                          <div class="row" style="margin-top: 6px;">
                            <div class="col-4">
                              教学号
                              <n-input placeholder="纯数字哦~" v-model:value="userToBeAdd.teacher_id" maxlength="20" show-count clearable />
                            </div>
                          </div>
                          <div class="row" style="margin-top: 6px;">
                            <div class="col-4">
                              联系方式
                              <n-input placeholder="纯数字哦~" v-model:value="userToBeAdd.teacher_telephone" maxlength="20" show-count clearable />
                            </div>
                          </div>
                        </div>
                        <div v-if="userToBeAdd.identity === 'admin'">
                          <div class="row">
                            <div class="col-4">
                              姓名
                              <n-input placeholder="" v-model:value="userToBeAdd.admin_name" maxlength="20" show-count clearable />
                            </div>
                            <div class="col-1"></div>
                            <div class="col-2">
                              性别
                              <n-select placeholder="请选择" v-model:value="userToBeAdd.admin_gender" :options="genderOptions" />
                            </div>
                            <div class="col-2">
                              年龄
                              <n-input placeholder="" v-model:value="userToBeAdd.admin_age" maxlength="3" show-count clearable />
                            </div>
                            <div class="col"></div>
                          </div>
                          <div class="row" style="margin-top: 6px;">
                            <div class="col-4">
                              教学号
                              <n-input placeholder="纯数字哦~" v-model:value="userToBeAdd.admin_id" maxlength="20" show-count clearable />
                            </div>
                          </div>
                          <div class="row" style="margin-top: 6px;">
                            <div class="col-4">
                              联系方式
                              <n-input placeholder="纯数字哦~" v-model:value="userToBeAdd.admin_telephone" maxlength="20" show-count clearable />
                            </div>
                          </div>
                        </div>
                      </n-space>
                      <template #footer>
                        <n-space justify="end">
                          <n-button @click="addUser" strong secondary round type="primary">
                            确认添加
                          </n-button>
                        </n-space>
                      </template>
                    </n-card>
                  </n-modal>
                </n-space>
                <n-divider/>
                <AllUsers/>
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
import {NCard, NTabs, NTabPane, NButton, NSpace, NDivider, NModal, NSelect, NInput, useMessage, NDatePicker } from 'naive-ui';
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
    NDatePicker
  },

  setup() {
    const store = useStore();
    const message = useMessage();

    // 添加用户
    let showAddUserModal = ref(false);
    let userToBeAdd = reactive({
      student_id: null,
      student_name: null,
      identity: null,
      student_gender: null,
      student_enter_date: null,
      student_age: null,
      student_telephone: null,
      faculty_id: null,
      teacher_id: null,
      teacher_name: null,
      teacher_gender: null,
      teacher_age: null,
      teacher_telephone: null,
      admin_id: null,
      admin_name: null,
      admin_age: null,
      admin_gender: null,
      admin_telephone: null,
    });
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

    const addUser = () => {
      let enter_date = ref("");

      const date = new Date(userToBeAdd.student_enter_date);
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);
      enter_date.value = `${year}-${month}-${day}`;

      $.ajax({
        url: "https://data.lxcode.xyz/api/admin/user/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          student_id: userToBeAdd.student_id,
          student_name: userToBeAdd.student_name,
          identity: userToBeAdd.identity,
          student_gender: userToBeAdd.student_gender,
          student_enter_date: enter_date.value,
          student_age: userToBeAdd.student_age,
          student_telephone: userToBeAdd.student_telephone,
          faculty_id: userToBeAdd.faculty_id,
          teacher_id: userToBeAdd.teacher_id,
          teacher_name: userToBeAdd.teacher_name,
          teacher_gender: userToBeAdd.teacher_gender,
          teacher_age: userToBeAdd.teacher_age,
          teacher_telephone: userToBeAdd.teacher_telephone,
          admin_id: userToBeAdd.admin_id,
          admin_name: userToBeAdd.admin_name,
          admin_gender: userToBeAdd.admin_gender,
          admin_age: userToBeAdd.admin_age,
          admin_telephone: userToBeAdd.admin_telephone,
        },
        success(resp) {
          if (resp.error_message === "success") {
            message.success("添加用户成功！");
            setTimeout(() => { location.reload(); }, 800);
          } else {
            message.error(resp.error_message);
          }
        }
      })
    };

    const showAddUserModalBtn = () => {
      showAddUserModal.value = true;
    }

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

    const updateDate = () => {
      const nowDate = new Date();
      const nowYear = nowDate.getFullYear();
      const nowMonth = nowDate.getMonth();
      const nowDay = nowDate.getDate();
      const NOW = new Date(nowYear, nowMonth, nowDay).getTime();
      if (userToBeAdd.student_enter_date > NOW) userToBeAdd.student_enter_date = NOW;
    }

    return {
      updateDate,
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
      showAddUserModal,
      showAddUserModalBtn,
      userToBeAdd,
      identityOptions,
      genderOptions,
      addUser,
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
