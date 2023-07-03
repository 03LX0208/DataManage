<template>
  <div class="all">
    <NavBar/>
    <div class="container-fluid">
      <div class="row">
        <div class="col"></div>
        <div class="col-5">
          <n-card
              title="⭐ 常用功能"
              embedded
              :bordered="false"
              style="margin-top: 20px; margin-bottom: 20px;"
              hoverable
          >
           <n-space>
             <n-button strong secondary round type="info" size="large" @click="pushToSelect">
               选课系统
             </n-button>
             <n-button strong secondary round type="primary" size="large" @click="pushToGrade">
               我的成绩
             </n-button>
           </n-space>
          </n-card>
        </div>
        <div class="col"></div>
      </div>
      <div class="row">
        <div class="col"></div>
        <div class="col-9" style="">
          <n-card title="课程表" hoverable style="box-shadow: 0 2px 4px rgba(0,0,0,0.15); padding: 5px">
            <table class="table table-bordered">
              <thead>
              <tr style="font-size: 22px;">
                <th scope="col" style="font-size: 22px">#</th>
                <th scope="col">星期一</th>
                <th scope="col">星期二</th>
                <th scope="col">星期三</th>
                <th scope="col">星期四</th>
                <th scope="col">星期五</th>
                <th scope="col">星期六</th>
                <th scope="col">星期日</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <th scope="row" style="font-size: 22px">8:00-8:50</th>
                <td v-for="course in t1" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">9:00-9:50</th>
                <td v-for="course in t2" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">10:00-10:50</th>
                <td v-for="course in t3" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">11:00-11:50</th>
                <td v-for="course in t4" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">14:00-14:50</th>
                <td v-for="course in t5" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">15:00-15:50</th>
                <td v-for="course in t6" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">16:00-16:50</th>
                <td v-for="course in t7" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              <tr>
                <th scope="row" style="font-size: 22px">17:00-18:50</th>
                <td v-for="course in t8" :key="course.course_name">
                  {{ (course.course_name == null ? "" : course.course_name) + " " + (course.teacher_name == null ? "" : course.teacher_name) }}
                  <br/>
                  {{ course.classroom == null ? "" :  course.classroom}}
                </td>
              </tr>
              </tbody>
            </table>
          </n-card>
        </div>
        <div class="col"></div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import {NCard, NSpace, NButton } from 'naive-ui';
import $ from "jquery";
import {useStore} from "vuex";
import { ref } from 'vue';

const pushToSelect = () => {
  window.open("/student/select-course/", '_blank');
}
const pushToGrade = () => {
  window.open("/student/grade/", '_blank');
}

export default {
  components: {
    NavBar,
    NCard,
    NSpace,
    NButton,
  },
  setup() {
    const store = useStore();
    let t1 = ref([]);
    let t2 = ref([]);
    let t3 = ref([]);
    let t4 = ref([]);
    let t5 = ref([]);
    let t6 = ref([]);
    let t7 = ref([]);
    let t8 = ref([]);

    $.ajax({
      url: "https://data.lxcode.xyz/api/student-section/query/time-table/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      data: {
        student_id: store.state.user.username,
      },
      success(resp) {
        // console.log(resp);
        t1.value = resp[0];
        t2.value = resp[1];
        t3.value = resp[2];
        t4.value = resp[3];
        t5.value = resp[4];
        t6.value = resp[5];
        t7.value = resp[6];
        t8.value = resp[7];
      }
    });

    return {
      t1,
      t2,
      t3,
      t4,
      t5,
      t6,
      t7,
      t8,
      pushToSelect,
      pushToGrade
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