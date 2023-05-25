<template>
  <n-card>
    <n-data-table
        striped
        :columns="usersColumns"
        :data="allUsers"
        :pagination="pagination"
        :bordered="false"
        :single-line="false"
        style="font-size: 15px;"
    />
    <template #footer>
      <n-statistic label="一共有" tabular-nums class="">
        <n-number-animation  :from="0" :to="usersCount" />
        <template #suffix>
          位用户。
        </template>
      </n-statistic>
    </template>
  </n-card>
  <n-modal v-model:show="showAddUserModal">
    <n-card
        style="width: 1000px"
        title="变更用户信息"
        :bordered="false"
        size="huge"
        role="dialog"
        aria-modal="true"
    >
      <n-space vertical>
        身份
        <div class="row">
          <div class="col-4">
            <n-select placeholder="" disabled v-model:value="userToBeAdd.identity" :options="identityOptions" />
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
              <n-select placeholder="" v-model:value="userToBeAdd.student_gender" :options="genderOptions" />
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
              <n-input placeholder="" disabled v-model:value="userToBeAdd.student_id" maxlength="20" show-count clearable />
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
              <n-date-picker v-model:value="userToBeAdd.enter_date" @update:value="updateDate" />
            </div>
          </div>
          <div style="margin-top: 6px;">学院</div>
          <div class="row">
            <div class="col-6">
              <n-select placeholder="" v-model:value="userToBeAdd.faculty_id" :options="facultyOptions" />
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
              <n-input disabled placeholder="纯数字哦~" v-model:value="userToBeAdd.teacher_id" maxlength="20" show-count clearable />
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
              <n-input disabled placeholder="纯数字哦~" v-model:value="userToBeAdd.admin_id" maxlength="20" show-count clearable />
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
            确认变更
          </n-button>
        </n-space>
      </template>
    </n-card>
  </n-modal>
</template>

<script>
import {NButton, NDataTable, NSpace, useMessage, NModal, NCard, NSelect, NInput, NPopconfirm, NDatePicker, NStatistic, NNumberAnimation} from "naive-ui";
import {h, reactive, ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";

export default {
  components: {
    NDataTable,
    NModal,
    NCard,
    NSelect,
    NButton,
    NSpace,
    NInput,
    NDatePicker,
    NStatistic,
    NNumberAnimation,
  },
  setup() {
    const store = useStore();
    const message = useMessage();
    let allUsers = ref([]);
    let allFaculties = ref([]);
    let usersCount = ref(null);

    $.ajax({
      url: "https://data.lxcode.xyz/api/faculty/get-all/",
      type: "get",
      success(resp) {
        allFaculties.value = resp;
        // 获得管理员的管理的所有用户
        $.ajax({
          url: "https://data.lxcode.xyz/api/admin/get-all-user/",
          type: "get",
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success(resp) {
            usersCount.value = resp.length;
            for (const user of resp) {
              let Fac_id = user.faculty_id;
              let Fac = null;
              for (const faculty of allFaculties.value) {
                if (Number(faculty.facultyId) === Number(Fac_id)) {
                  Fac = faculty.facultyName;
                  break;
                }
              }

              allUsers.value.push({
                id: user.id,
                gender: user.gender === 'male' ? '男' : '女',
                age: user.age,
                name: user.name,
                telephone: user.telephone,
                enter_date: user.enter_date,
                faculty: Fac,
                identity: user.identity,
              });
            }
          }
        });
      }
    });

    // 更新用户
    let showAddUserModal = ref(false);
    let userToBeAdd = reactive({
      student_id: null,
      student_name: null,
      identity: null,
      student_gender: null,
      student_enter_date: null,
      student_age: null,
      student_telephone: null,
      enter_date: null,
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
            value: parseInt(fac.facultyId),
          })
        }
      }
    });



    const addUser = () => {
      let enter_date = ref("");
      const date = new Date(userToBeAdd.enter_date);
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);
      enter_date.value = `${year}-${month}-${day}`;

      $.ajax({
        url: "https://data.lxcode.xyz/api/admin/user/update/",
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
            message.success("信息变更成功！");
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

    const createUsersColumns = ({
                                  updateUser, deleteUser
                                }) => {
      return [
        {
          title: "教学号",
          key: "id"
        },
        {
          title: "身份",
          key: "identity",
        },
        {
          title: "姓名",
          key: "name",
        },
        {
          title: "性别",
          key: "gender",
        },
        {
          title: "年龄",
          key: "age",
        },
        {
          title: "联系方式",
          key: "telephone",
        },
        {
          title: "入学日期",
          key: "enter_date",
        },
        {
          title: "学院",
          key: "faculty",
        },
        {
          title: "操作",
          render(row) {
            const buttons = [
              {
                text: "变更信息",
                color: "info",
                onClick: () => updateUser(row)
              },
              {
                text: "删除用户",
                color: "error",
                onClick: () => deleteUser(row)
              }
            ];

            const handlePositiveClick = (row) => {
              return () => {
                helper(row);
              }
            }

            const handleNegativeClick = () => {}

            const helper = (row) => {
              $.ajax({
                url: "https://data.lxcode.xyz/api/admin/user/delete/",
                type: "post",
                headers: {
                  Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                  id: row.id,
                },
                success(resp) {
                  if (resp.error_message === "success") {
                    message.success("删除成功！");
                    let allUsersCopy = allUsers.value;
                    allUsers.value = []
                    for (const user of allUsersCopy) {
                      if (Number(user.id) !== Number(row.id)) {
                        allUsers.value.push(user);
                      }
                    }
                  } else {
                    message.error(resp.error_message);
                  }
                }
              })
            }

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
                                          onClick,
                                          key: index,
                                        },
                                        { default: () => text }
                                    ),
                                default: () =>
                                    h("span", {}, "您确定要删除该用户吗？")
                              }
                          )
                      )
                  ),
                ]
            );
          }
        },
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

    const updateDate = () => {
      const nowDate = new Date();
      const nowYear = nowDate.getFullYear();
      const nowMonth = nowDate.getMonth();
      const nowDay = nowDate.getDate();
      const NOW = new Date(nowYear, nowMonth, nowDay).getTime();
      if (userToBeAdd.enter_date > NOW) userToBeAdd.enter_date = NOW;
    }


    return {
      usersCount,
      updateDate,
      pagination: paginationReactive,
      facultyOptions,
      userToBeAdd,
      showAddUserModalBtn,
      showAddUserModal,
      identityOptions,
      addUser,
      genderOptions,
      allUsers,
      usersColumns: createUsersColumns({
        updateUser(row) {
          let identity;
          if (row.identity === "学生") identity = "student";
          else if (row.identity === "老师") identity = "teacher";
          else identity = "admin";

          $.ajax({
            url: "https://data.lxcode.xyz/api/admin/user/info/",
            type: "get",
            headers: {
              Authorization: "Bearer " + store.state.user.token,
            },
            data: {
              id: row.id,
              identity: identity,
            },
            success(resp) {
              if (resp.identity === "学生") {
                userToBeAdd.identity = "student";
                userToBeAdd.student_id = resp.id;
                userToBeAdd.student_name = resp.name;
                userToBeAdd.student_gender = resp.gender;
                userToBeAdd.student_enter_date = resp.enter_date;
                userToBeAdd.student_age = resp.age;
                userToBeAdd.student_telephone = resp.telephone;
                userToBeAdd.faculty_id = parseInt(resp.faculty_id);
                userToBeAdd.year = parseInt(resp.enter_date.slice(0, 4));
                userToBeAdd.month = parseInt(resp.enter_date.slice(5, 7));
                userToBeAdd.day = parseInt(resp.enter_date.slice(8, 10));
                const nowDate = new Date(userToBeAdd.year, userToBeAdd.month - 1, userToBeAdd.day);
                userToBeAdd.enter_date = nowDate.getTime();
              } else if (resp.identity === "老师") {
                userToBeAdd.identity = "teacher";
                userToBeAdd.teacher_id = resp.id;
                userToBeAdd.teacher_name = resp.name;
                userToBeAdd.teacher_gender = resp.gender;
                userToBeAdd.teacher_age = resp.age;
                userToBeAdd.teacher_telephone = resp.telephone;
              } else if (resp.identity === "管理员") {
                userToBeAdd.identity = "admin";
                userToBeAdd.admin_id = resp.id;
                userToBeAdd.admin_age = resp.age;
                userToBeAdd.admin_gender = resp.gender;
                userToBeAdd.admin_name = resp.name;
                userToBeAdd.admin_telephone = resp.telephone;
              }
              showAddUserModalBtn();
            }
          })
        },
        deleteUser() {
        },
      }),
    }
  }
}
</script>

<style scoped>

</style>