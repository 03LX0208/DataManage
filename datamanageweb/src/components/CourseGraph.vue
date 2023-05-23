<template>
  <div style="position: relative; width: 100%; height: 100%;">
    <div style="position: relative; width: 100%; height: 100%;">
      <div style="width: 100%;">
        <NavBar/>
      </div>
      <div style="width: 100%; text-align: center;">
        <div id="main" style="display: inline-block; width: 600px; height: 600px"></div>
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
      })
      // let data = [
      //   {
      //     name: '离散数学',
      //     x: 300,
      //     y: 300
      //   },
      //   {
      //     name: 'C++',
      //     x: 800,
      //     y: 300
      //   },
      //   {
      //     name: '微积分AI',
      //     x: 550,
      //     y: 100
      //   },
      //   {
      //     name: '数据结构',
      //     x: 550,
      //     y: 500
      //   }
      // ];
      // let links = [
      //   {
      //     source: '微积分AI',
      //     target: '离散数学'
      //   },
      //   {
      //     source: '离散数学',
      //     target: '数据结构'
      //   },
      //   {
      //     source: 'C++',
      //     target: '数据结构'
      //   },
      // ];
    });
  }
}
</script>

<style scoped>

</style>