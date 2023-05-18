<template>
  <div id="all">
    <NavBar/>
    <div class="container" v-if="$store.state.user.identity === 'student'">
      <div class="row">
        <div class="col"></div>

        <div class="col-2">
          <n-list hoverable clickable style="margin-top: 20px;">
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
            <n-list-item>
              <n-thing title="相见恨晚" content-style="margin-top: 10px;">
                奋勇呀然后休息呀<br>
                完成你伟大的人生
              </n-thing>
            </n-list-item>
          </n-list>
        </div>

        <div class="col-6">
            <n-card
                title="📖 如何成功"
                embedded
                :bordered="false"
                style="margin-top: 20px; margin-bottom: 20px;"
                hoverable
            >
              如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
              996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
            </n-card>
          <n-card
              title="⭐ 我的收藏"
              embedded
              :bordered="false"
              hoverable
          >
            如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
            996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
          </n-card>
          <div class="row">
            <div class="col">
              <n-card
                  title="📖 如何成功"
                  embedded
                  :bordered="false"
                  style="margin-top: 20px; margin-bottom: 20px;"
                  hoverable
              >
                如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
                996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
              </n-card>
            </div>
            <div class="col">
              <n-card
                  title="📖 如何成功"
                  embedded
                  :bordered="false"
                  style="margin-top: 20px; margin-bottom: 20px;"
                  hoverable
              >
                如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
                996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
              </n-card>
            </div>
          </div>
        </div>
        <div class="col"></div>
      </div>
    </div>

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
                            <div class="col-2">
                              <n-select placeholder="年" v-model:value="userToBeAdd.year" :options="yearOptions"
                                        @update:value="updateOptions(userToBeAdd.year, userToBeAdd.month, 0)" />
                            </div>
                            <div class="col-2">
                              <n-select placeholder="月" v-model:value="userToBeAdd.month" :options="monthOptions"
                                        @update:value="updateOptions(userToBeAdd.year, userToBeAdd.month, 1)" />
                            </div>

                            <div class="col-2">
                              <n-select placeholder="日" v-model:value="userToBeAdd.day" :options="dayOptions" />
                            </div>

                            <div class="col"></div>
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
              <n-tab-pane name="别的">

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
import {NCard, NList, NListItem, NThing,  NTabs, NTabPane, NButton, NSpace, NDivider, NModal, NSelect, NInput, useMessage } from 'naive-ui';
import AllUsers from "@/components/admin/AllUsers";
import $ from 'jquery'
import {useStore} from "vuex";
import { ref, reactive } from 'vue';


export default {
  components: {
    NavBar,
    NCard,
    NList,
    NListItem,
    NThing,
    NTabs,
    NTabPane,
    NSpace,
    NButton,
    AllUsers,
    NDivider,
    NModal,
    NSelect,
    NInput,
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
      year: null,
      month: null,
      day: null,
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
    })

    let yearOptions = ref([]);
    let monthOptions = ref([]);
    let dayOptions = ref([]);
    for (let i = 2000; i <= 2023; i++) yearOptions.value.push({
      label: i,
      value: i,
    });

    const updateOptions = (year, month, sig) => {
      // 获取当前日期
      const today = new Date();
      const currentYear = today.getFullYear();
      const currentMonth = today.getMonth() + 1;
      const currentDate = today.getDate();

      if (sig === 0) {
        userToBeAdd.month = null;
      } else userToBeAdd.day = null;

      // 如果选择年份大于当前年份，则只能选择1~12月
      if (year < currentYear) {
        monthOptions.value = [];
        for (let i = 1; i <= 12; i++) monthOptions.value.push({
          label: i,
          value: i,
        });
      } else {
        // 否则如果选择年份等于当前年份，则只能选择当前月份之前的月份
        monthOptions.value = [];
        for (let i = 1; i <= currentMonth; i++) monthOptions.value.push({
          label: i,
          value: i,
        });
      }

      // 如果选择的月份等于当前月份，则只能选择当前日期之前的日期
      if (year === currentYear && month === currentMonth) {
        dayOptions.value = [];
        for (let i = 1; i <= currentDate; i++) dayOptions.value.push({
          label: i,
          value: i,
        });
      } else {
        dayOptions.value = [];
        // 否则根据所选月份计算出可以选择的日期范围
        const daysInMonth = new Date(year, month, 0).getDate();
        for (let i = 1; i <= daysInMonth; i++) dayOptions.value.push({
          label: i,
          value: i,
        });
      }
    }

    const addUser = () => {
      let enter_date = ref("");

      if (userToBeAdd.identity === "student") {
        if (userToBeAdd.day === null || userToBeAdd.month === null || userToBeAdd.year === null) {
          message.error("请将入学日期完善！");
          return;
        }
        enter_date.value += String(userToBeAdd.year);
        if (userToBeAdd.month < 10) {
          enter_date.value += "0" + String(userToBeAdd.month);
        } else enter_date.value += "0" + String(userToBeAdd.month);
        if (userToBeAdd.day < 10) {
          enter_date.value += "0" + String(userToBeAdd.day);
        } else enter_date.value += "0" + String(userToBeAdd.day);
      }

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

    return {
      showAddUserModal,
      showAddUserModalBtn,
      userToBeAdd,
      identityOptions,
      genderOptions,
      yearOptions,
      monthOptions,
      dayOptions,
      updateOptions,
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
