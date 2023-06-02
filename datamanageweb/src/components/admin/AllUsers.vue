<template>
  <n-space justify="space-between">
    <n-card >
      <n-space>
        <n-card title="性别" size="small" class="float-left">
          <n-checkbox v-model:checked="searcher.male">
            男
          </n-checkbox>
          <n-checkbox v-model:checked="searcher.female">
            女
          </n-checkbox>
        </n-card>
        <n-card title="身份" size="small" class="float-left">
          <n-checkbox v-model:checked="searcher.admin">
            管理员
          </n-checkbox>
          <n-checkbox v-model:checked="searcher.teacher">
            老师
          </n-checkbox>
          <n-checkbox v-model:checked="searcher.student">
            学生
          </n-checkbox>
        </n-card>
        <n-button tertiary round type="primary" @click="search">
          确定
        </n-button>
      </n-space>
    </n-card>

    <n-card title="搜索">
      <n-space justify="end">
        <n-input placeholder="搜索姓名/教学号" v-model:value="searcherTarget" />
        <n-button tertiary round type="warning" @click="searchByName">
          确定
        </n-button>
      </n-space>
    </n-card>

    <n-button style="" class="float-end" round size="large" type="info" @click="showAddUserModalBtn">
      添加用户
    </n-button>
  </n-space>
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

  <n-divider/>
  <n-card>
    <n-data-table
        striped
        :columns="usersColumns"
        :data="allUsers"
        :pagination="pagination"
        :bordered="false"
        :single-line="false"
        style="font-size: 15px;"
        @update:sorter="handleSorterChange"
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
  <n-modal v-model:show="showUpdateUserModal">
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
            <n-select placeholder="" disabled v-model:value="userToBeUpdate.identity" :options="identityOptions" />
          </div>
          <div class="col"></div>
        </div>
        <div v-if="userToBeUpdate.identity === 'student'">
          <div class="row">
            <div class="col-4">
              姓名
              <n-input placeholder="" v-model:value="userToBeUpdate.student_name" maxlength="20" show-count clearable />
            </div>
            <div class="col-1"></div>
            <div class="col-2">
              性别
              <n-select placeholder="" v-model:value="userToBeUpdate.student_gender" :options="genderOptions" />
            </div>
            <div class="col-2">
              年龄
              <n-input placeholder="" v-model:value="userToBeUpdate.student_age" maxlength="3" show-count clearable />
            </div>
            <div class="col"></div>
          </div>
          <div class="row" style="margin-top: 6px;">
            <div class="col-4">
              教学号
              <n-input placeholder="" disabled v-model:value="userToBeUpdate.student_id" maxlength="20" show-count clearable />
            </div>
            <div class="col-1"></div>
            <div class="col-4">
              联系方式
              <n-input placeholder="纯数字哦~" v-model:value="userToBeUpdate.student_telephone" maxlength="20" show-count clearable />
            </div>
            <div class="col"></div>
          </div>
          <div style="margin-top: 6px">入学时间</div>
          <div class="row">
            <div class="col-6">
              <n-date-picker v-model:value="userToBeUpdate.enter_date" @update:value="updateDate" />
            </div>
          </div>
          <div style="margin-top: 6px;">学院</div>
          <div class="row">
            <div class="col-6">
              <n-select placeholder="" v-model:value="userToBeUpdate.faculty_id" :options="facultyOptions" />
            </div>
          </div>
        </div>
        <div v-if="userToBeUpdate.identity === 'teacher'">
          <div class="row">
            <div class="col-4">
              姓名
              <n-input placeholder="" v-model:value="userToBeUpdate.teacher_name" maxlength="20" show-count clearable />
            </div>
            <div class="col-1"></div>
            <div class="col-2">
              性别
              <n-select placeholder="请选择" v-model:value="userToBeUpdate.teacher_gender" :options="genderOptions" />
            </div>
            <div class="col-2">
              年龄
              <n-input placeholder="" v-model:value="userToBeUpdate.teacher_age" maxlength="3" show-count clearable />
            </div>
            <div class="col"></div>
          </div>
          <div class="row" style="margin-top: 6px;">
            <div class="col-4">
              教学号
              <n-input disabled placeholder="纯数字哦~" v-model:value="userToBeUpdate.teacher_id" maxlength="20" show-count clearable />
            </div>
          </div>
          <div class="row" style="margin-top: 6px;">
            <div class="col-4">
              联系方式
              <n-input placeholder="纯数字哦~" v-model:value="userToBeUpdate.teacher_telephone" maxlength="20" show-count clearable />
            </div>
          </div>
        </div>
        <div v-if="userToBeUpdate.identity === 'admin'">
          <div class="row">
            <div class="col-4">
              姓名
              <n-input placeholder="" v-model:value="userToBeUpdate.admin_name" maxlength="20" show-count clearable />
            </div>
            <div class="col-1"></div>
            <div class="col-2">
              性别
              <n-select placeholder="请选择" v-model:value="userToBeUpdate.admin_gender" :options="genderOptions" />
            </div>
            <div class="col-2">
              年龄
              <n-input placeholder="" v-model:value="userToBeUpdate.admin_age" maxlength="3" show-count clearable />
            </div>
            <div class="col"></div>
          </div>
          <div class="row" style="margin-top: 6px;">
            <div class="col-4">
              教学号
              <n-input disabled placeholder="纯数字哦~" v-model:value="userToBeUpdate.admin_id" maxlength="20" show-count clearable />
            </div>
          </div>
          <div class="row" style="margin-top: 6px;">
            <div class="col-4">
              联系方式
              <n-input placeholder="纯数字哦~" v-model:value="userToBeUpdate.admin_telephone" maxlength="20" show-count clearable />
            </div>
          </div>
        </div>
      </n-space>
      <template #footer>
        <n-space justify="end">
          <n-button @click="updateUser" strong secondary round type="primary">
            确认变更
          </n-button>
        </n-space>
      </template>
    </n-card>
  </n-modal>
</template>

<script>
import {NButton, NDataTable, NSpace, useMessage, NModal, NCard, NSelect, NInput, NPopconfirm, NStatistic, NNumberAnimation, NDatePicker,
  NCheckbox,} from "naive-ui";
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
    NCheckbox,
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
      success(res) {
        allFaculties.value = res;
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

    // 更新用户
    let showUpdateUserModal = ref(false);
    let userToBeUpdate = reactive({
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



    const updateUser = () => {
      let enter_date = ref("");
      const date = new Date(userToBeUpdate.enter_date);
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
          student_id: userToBeUpdate.student_id,
          student_name: userToBeUpdate.student_name,
          identity: userToBeUpdate.identity,
          student_gender: userToBeUpdate.student_gender,
          student_enter_date: enter_date.value,
          student_age: userToBeUpdate.student_age,
          student_telephone: userToBeUpdate.student_telephone,
          faculty_id: userToBeUpdate.faculty_id,
          teacher_id: userToBeUpdate.teacher_id,
          teacher_name: userToBeUpdate.teacher_name,
          teacher_gender: userToBeUpdate.teacher_gender,
          teacher_age: userToBeUpdate.teacher_age,
          teacher_telephone: userToBeUpdate.teacher_telephone,
          admin_id: userToBeUpdate.admin_id,
          admin_name: userToBeUpdate.admin_name,
          admin_gender: userToBeUpdate.admin_gender,
          admin_age: userToBeUpdate.admin_age,
          admin_telephone: userToBeUpdate.admin_telephone,
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

    const showUpdateUserModalBtn = () => {
      showUpdateUserModal.value = true;
    }

    const showAddUserModalBtn = () => {
      showAddUserModal.value = true;
    }

    const createUsersColumns = ({
                                  updateUser, deleteUser
                                }) => {
      return [
        {
          title: "教学号",
          key: "id",
          sorter(rowA, rowB) {
            return Number(rowA.id) - Number(rowB.id);
          }
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
          sorter(rowA, rowB) {
            return Number(rowA.age) - Number(rowB.age);
          }
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
                secondary: "true",
                onClick: () => updateUser(row)
              },
              {
                text: "删除用户",
                color: "error",
                secondary: "true",
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
                      buttons.map(({ text, color, onClick, secondary }, index) =>
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
                                          secondary,
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
      if (userToBeUpdate.enter_date > NOW) userToBeUpdate.enter_date = NOW;
    }

    let searcher = reactive({
      male: false,
      female: false,
      admin: false,
      teacher: false,
      student: false
    })

    const search = () => {
      $.ajax({
        url: "https://data.lxcode.xyz/api/admin/search/condition/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          male: searcher.male,
          female: searcher.female,
          admin: searcher.admin,
          teacher: searcher.teacher,
          student: searcher.student
        },
        success(resp) {
          allUsers.value = []
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
      })
    }

    let searcherTarget = ref("");
    const searchByName = () => {
      searcher.male = false;
      searcher.female = false;
      searcher.admin = false;
      searcher.student = false;
      searcher.teacher = false;
      $.ajax({
        url: "https://data.lxcode.xyz/api/admin/search/name/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          target: searcherTarget.value,
        },
        success(resp) {
          allUsers.value = []
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
      })
    }

    return {
      searcherTarget,
      searchByName,
      userToBeAdd,
      addUser,
      showUpdateUserModalBtn,
      showUpdateUserModal,
      searcher,
      search,
      usersCount,
      updateDate,
      pagination: paginationReactive,
      facultyOptions,
      userToBeUpdate,
      showAddUserModalBtn,
      showAddUserModal,
      identityOptions,
      updateUser,
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
                userToBeUpdate.identity = "student";
                userToBeUpdate.student_id = resp.id;
                userToBeUpdate.student_name = resp.name;
                userToBeUpdate.student_gender = resp.gender;
                userToBeUpdate.student_enter_date = resp.enter_date;
                userToBeUpdate.student_age = resp.age;
                userToBeUpdate.student_telephone = resp.telephone;
                userToBeUpdate.faculty_id = parseInt(resp.faculty_id);
                userToBeUpdate.year = parseInt(resp.enter_date.slice(0, 4));
                userToBeUpdate.month = parseInt(resp.enter_date.slice(5, 7));
                userToBeUpdate.day = parseInt(resp.enter_date.slice(8, 10));
                const nowDate = new Date(userToBeUpdate.year, userToBeUpdate.month - 1, userToBeUpdate.day);
                userToBeUpdate.enter_date = nowDate.getTime();
              } else if (resp.identity === "老师") {
                userToBeUpdate.identity = "teacher";
                userToBeUpdate.teacher_id = resp.id;
                userToBeUpdate.teacher_name = resp.name;
                userToBeUpdate.teacher_gender = resp.gender;
                userToBeUpdate.teacher_age = resp.age;
                userToBeUpdate.teacher_telephone = resp.telephone;
              } else if (resp.identity === "管理员") {
                userToBeUpdate.identity = "admin";
                userToBeUpdate.admin_id = resp.id;
                userToBeUpdate.admin_age = resp.age;
                userToBeUpdate.admin_gender = resp.gender;
                userToBeUpdate.admin_name = resp.name;
                userToBeUpdate.admin_telephone = resp.telephone;
              }
              showUpdateUserModalBtn();
            }
          })
        },
        deleteUser() {
        },
      }),
      handleSorterChange (sorter) {
        allUsers.value.forEach((column) => {
          /** column.sortOrder !== undefined means it is uncontrolled */
          if (column.sortOrder === undefined) return
          if (!sorter) {
            column.sortOrder = false
            return
          }
          if (column.key === sorter.columnKey) column.sortOrder = sorter.order
          else column.sortOrder = false
        })
      }

    }
  }
}
</script>

<style scoped>

</style>