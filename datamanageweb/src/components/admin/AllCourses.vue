<template>
  <n-card>
    <n-data-table
        striped
        :columns="facultiesColumns"
        :data="allFaculties"
        :pagination="paginationReactive"
        :bordered="false"
        :single-line="false"
        style="font-size: 15px;"
    />
    <template #footer>
      <n-statistic label="一共有" tabular-nums class="">
        <n-number-animation  :from="0" :to="coursesCount" />
        <template #suffix>
          门课程。
        </template>
      </n-statistic>
    </template>
  </n-card>
</template>

<script>
import {NButton, NDataTable, useMessage, NSpace, NPopconfirm, NStatistic, NNumberAnimation, NCard} from "naive-ui";
import {h, reactive, ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";

export default {
  components: {
    NStatistic,
    NCard,
    NNumberAnimation,
    NDataTable,
  },
  setup() {
    const store = useStore();
    const message = useMessage();
    let allCourses = ref([]);
    let coursesCount = ref(null);

    $.ajax({
      url: "https://data.lxcode.xyz/api/faculty/get-all/",
      type: "get",
      success(response) {
        $.ajax({
          url: "https://data.lxcode.xyz/api/course/get-all/",
          type: "get",
          success(resp) {
            coursesCount.value = resp.length;
            for (const course of resp) {
              for (const faculty of response) {
                if (Number(faculty.facultyId) === Number(course.facultyId)) {
                  allCourses.value.push({
                    courseId: course.courseId,
                    courseName: course.courseName,
                    coursePeriod: course.coursePeriod,
                    courseCredit: course.courseCredit,
                    courseFaculty: faculty.facultyName,
                  });
                  break;
                }
              }
            }
          }
        });
      }
    });

    const createFacultyColumns = ({
                                    deleteCourse, showCourseGraph
                                  }) => {
      return [
        {
          title: "课程编号",
          key: "courseId"
        },
        {
          title: "课程名称",
          key: "courseName"
        },
        {
          title: "课程学时",
          key: "coursePeriod"
        },
        {
          title: "课程学分",
          key: "courseCredit"
        },
        {
          title: "开课学院",
          key: "courseFaculty",
        },
        {
          title: "先导课程",
          render(row) {
            return h(
                NButton,
                {
                  strong: true,
                  type: "info",
                  size: "small",
                  onClick: () => showCourseGraph(row)
                },
                { default: () => "查看" }
            );
          }
        },
        {
          title: "操作",
          render(row) {
            const buttons = [
              {
                text: "删除课程",
                color: "error",
                onClick: () => deleteCourse(row)
              }
            ];

            const handlePositiveClick = (row) => {
              return () => {
                helper(row);
              };
            };

            const helper = (row) => {
              $.ajax({
                url: "https://data.lxcode.xyz/api/course/delete/",
                type: "post",
                headers: {
                  Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                  course_id: row.courseId,
                },
                success(resp) {
                  if (resp.error_message === "success") {
                    message.success("删除成功！");
                    setTimeout(() => { location.reload(); }, 800)
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


    return {
      coursesCount,
      paginationReactive,
      allFaculties: allCourses,
      facultiesColumns: createFacultyColumns({
        deleteCourse() {
        },
        showCourseGraph(row) {
          window.open(`/course/graph/${row.courseId}`, '_blank');
        }
      })
    }
  }
}

</script>

<style scoped>

</style>