<template>
  <div style="position: relative; width: 100%; height: 100%;">
    <div style="position: relative; width: 100%; height: 100%;">
      <div style="width: 100%;">
        <NavBar/>
      </div>
      <div style="width: 100%; text-align: center;">
        <div id="main" style="display: inline-block; width: 1200px; height: 700px"></div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import { onMounted, ref } from "vue";
import * as echarts from 'echarts/core';
import { TitleComponent, TooltipComponent } from 'echarts/components';
import { GraphChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';
import router from "@/router";
import $ from 'jquery';
import {useStore} from "vuex";

echarts.use([TitleComponent, TooltipComponent, GraphChart, CanvasRenderer]);

export default {
  components: {
    NavBar,
  },
  setup() {
    let echart = echarts;
    onMounted(() => {
      const id = router.currentRoute.value.params.id;
      let name = ref("");
      const store = useStore();

      $.ajax({
        url: "https://data.lxcode.xyz/api/student-section/query/student-score/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          student_id: store.state.user.username,
        },
        success(res) {
          $.ajax({
            url: "https://data.lxcode.xyz/api/course/graph/",
            type: "get",
            data: {
              course_id: id,
            },
            success(resp) {
              $.ajax({
                url: "https://data.lxcode.xyz/api/course/get-all/",
                type: "get",
                success(response) {
                  for (const course of response) {
                    if (Number(course.courseId) === Number(id)) {
                      name.value = course.courseName;
                      break;
                    }
                  }
                  name.value += " 先导课程图"

                  let data = resp.coordinate;
                  for (let node of data) {
                    let color = '';
                    let ok = false;
                    if (res[0] != null) {
                      for (let cr of res) {
                        if (cr.course_name === node.name) {
                          ok = true;
                          break;
                        }
                      }
                    }
                    if (ok) color = 'green';
                    else color = 'grey';
                    node.itemStyle = {
                      color: color,
                    }
                  }

                  let links = resp.link_;
                  let chart = echart.init(document.getElementById('main'));
                  chart.setOption({
                    title: {
                      text: name.value,
                    },
                    tooltip: {},
                    animationDurationUpdate: 1500,
                    animationEasingUpdate: 'quinticInOut',
                    series: [
                      {
                        type: 'graph',
                        layout: 'none',
                        symbolSize: 50,
                        roam: true,
                        label: {
                          show: true
                        },
                        edgeSymbol: ['circle', 'arrow'],
                        edgeSymbolSize: [4, 10],
                        edgeLabel: {
                          fontSize: 20
                        },
                        data: data,
                        links: links,
                        lineStyle: {
                          opacity: 1,
                          width: 2,
                          curveness: 0
                        }
                      }
                    ]
                  });
                }
              });
            },
            error() {
              router.push({name: '404'});
            }
          });
        }
      });
    });
  }
}
</script>

<style scoped>

</style>